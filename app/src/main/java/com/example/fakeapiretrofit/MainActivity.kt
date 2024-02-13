package com.example.fakeapiretrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fakeapiretrofit.databinding.ActivityMainBinding
import com.example.fakeapiretrofit.network.ViewState
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojoItem
import com.example.fakeapiretrofit.viewmodel.ProductPojoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val productPojoViewModel: ProductPojoViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    val TAG = "FakeApiRetrofit"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addButtonClickListener()
        observePostData()
        observeGetAllData()
        observeDataFromRoom()
    }

    private fun addButtonClickListener() {
        binding.getallButton.setOnClickListener {
            productPojoViewModel.getAllProducts()
        }
        binding.postButton.setOnClickListener {
            productPojoViewModel.postProduct(ProductPojoItem())
        }
        binding.getRoomButton.setOnClickListener {
            productPojoViewModel.getAllProjectItemsFromRoom()
        }
    }

    private fun observeGetAllData() {
        productPojoViewModel.getAppProductItemsServer.observe(this) { response ->
            when (response) {
                is ViewState.Success -> {
                    val responseData = response.value?.body()
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Get All Product Items Response  \n \n \n ${responseData}"
                    responseData?.forEach {
                        productPojoViewModel.insertProductPojoItemToRoom(it)
                    }
                    Log.d(TAG, "onCreate success response : ${responseData}")
                }

                is ViewState.Error -> {
                    val responseData = response.value?.body()
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Get All Product Items Response  \n \n \n ${responseData}"
                    Log.d(TAG, "onCreate Error response : $response")
                }

                is ViewState.Loading -> {
                    Log.d(TAG, "onCreate Loading response : $response")
                    binding.progressbar.visibility = View.VISIBLE
                    binding.responseText.visibility = View.INVISIBLE
                }

                else ->
                    Log.d(TAG, "onCreate unknown")
            }
        }
    }

    private fun observePostData() {
        productPojoViewModel.getPostedProductItemsFromServer.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    val responseData = it.value?.body()
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Post Product Items Response \n \n \n ${responseData}"
                    Log.d(TAG, "onCreate success response : ${it.value?.body()}")
                }

                is ViewState.Error -> {
                    val responseData = it.value?.body()
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Post Product Items Response \n \n \n ${responseData}"
                    Log.d(TAG, "onCreate Error response : $it")
                }

                is ViewState.Loading -> {
                    Log.d(TAG, "onCreate Loading response : $it")
                    binding.progressbar.visibility = View.VISIBLE
                    binding.responseText.visibility = View.INVISIBLE
                }

                else ->
                    Log.d(TAG, "onCreate unknown")
            }
        }
    }

    private fun observeDataFromRoom() {
        productPojoViewModel.getAppProductItemsFromRoom.observe(this) {
            if (it == null)
                return@observe
            binding.progressbar.visibility = View.INVISIBLE
            binding.responseText.visibility = View.VISIBLE
            binding.responseText.text =
                "Post Product Items From Room DataBase \n size = ${it.size} \n ${it}"
        }
    }
}
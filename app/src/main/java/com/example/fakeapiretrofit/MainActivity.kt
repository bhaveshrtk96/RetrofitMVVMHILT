package com.example.fakeapiretrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fakeapiretrofit.databinding.ActivityMainBinding
import com.example.fakeapiretrofit.modelentities.ProductPojoItem
import com.example.fakeapiretrofit.network.ViewState
import com.example.fakeapiretrofit.viewmodel.FakeApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val fakeApiViewModel: FakeApiViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    val TAG = "FakeApiRetrofit"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getallButton.setOnClickListener {
            fakeApiViewModel.getAllProducts()
        }
        binding.postButton.setOnClickListener {
            fakeApiViewModel.postProduct(ProductPojoItem())
        }
        observePostData()
        observeGetAllData()
    }

    fun observeGetAllData() {
        fakeApiViewModel.getAppProductItems.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Get All Product Items Response  \n" +
                                " \n" +
                                " \n ${it.value?.body().toString()}"
                    Log.d(TAG, "onCreate success response : ${it.value?.body()}")
                }

                is ViewState.Error -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Get All Product Items Response  \n" +
                                " \n" +
                                " \n ${it.value?.body().toString()}"
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

    fun observePostData() {
        fakeApiViewModel.getPostedProductItems.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Post Product Items Response \n \n \n ${it.value?.body().toString()}"
                    Log.d(TAG, "onCreate success response : ${it.value?.body()}")
                }

                is ViewState.Error -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.responseText.visibility = View.VISIBLE
                    binding.responseText.text =
                        "Post Product Items Response  \n" +
                                " \n" +
                                " \n  ${it.value?.body().toString()}"
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
}
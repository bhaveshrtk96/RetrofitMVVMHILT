package com.example.fakeapiretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeapiretrofit.network.ViewState
import com.example.fakeapiretrofit.repositories.ProductPojoRepository
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojo
import com.example.fakeapiretrofit.sharedmodelentities.ProductPojoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductPojoViewModel @Inject constructor(val productPojoRepository: ProductPojoRepository) :
    ViewModel() {

    private var _getAppProductItemsFromServer: MutableLiveData<ViewState<Response<ProductPojo>>> =
        MutableLiveData()
    val getAppProductItemsServer: LiveData<ViewState<Response<ProductPojo>>> =
        _getAppProductItemsFromServer

    private var _getPostedProductItemsFromServer: MutableLiveData<ViewState<Response<ProductPojoItem>>> =
        MutableLiveData()
    val getPostedProductItemsFromServer: LiveData<ViewState<Response<ProductPojoItem>>> =
        _getPostedProductItemsFromServer

    private var _getAppProductItemsFromRoom: MutableLiveData<MutableList<ProductPojoItem>> =
        MutableLiveData()
    val getAppProductItemsFromRoom: LiveData<MutableList<ProductPojoItem>> =
        _getAppProductItemsFromRoom

    fun getAllProducts() {
        _getAppProductItemsFromServer.postValue(ViewState.Loading())
        viewModelScope.launch {
            try {
                val tempResponse = productPojoRepository.getAllProducts()
                if (tempResponse.isSuccessful)
                    _getAppProductItemsFromServer.postValue(ViewState.Success(tempResponse))
                else
                    _getAppProductItemsFromServer.postValue(
                        ViewState.Error(
                            tempResponse.errorBody().toString()
                        )
                    )
            } catch (e: Exception) {
                Log.i("FakeApiRetrofit", e.message.toString())
            }

        }
    }

    fun postProduct(productPojo: ProductPojoItem) {
        _getPostedProductItemsFromServer.postValue(ViewState.Loading())
        viewModelScope.launch {
            try {
                val tempResponse = productPojoRepository.postProduct(productPojo = productPojo)
                if (tempResponse.isSuccessful)
                    _getPostedProductItemsFromServer.postValue(ViewState.Success(tempResponse))
                else
                    _getPostedProductItemsFromServer.postValue(
                        ViewState.Error(
                            tempResponse.errorBody().toString()
                        )
                    )
            } catch (e: Exception) {
                Log.i("FakeApiRetrofit", e.message.toString())
            }
        }
    }

    fun insertProductPojoItemToRoom(productPojoItem: ProductPojoItem) {
        viewModelScope.launch {
            productPojoRepository.insertProductPojoItem(productPojoItem)
        }

    }

    fun getAllProjectItemsFromRoom() {
        viewModelScope.launch {
            val tempDataFromRoom = productPojoRepository.getAllProjectItems()
            _getAppProductItemsFromRoom.postValue(tempDataFromRoom)
        }
    }
}
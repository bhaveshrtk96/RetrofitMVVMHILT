package com.example.fakeapiretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeapiretrofit.modelentities.ProductPojo
import com.example.fakeapiretrofit.modelentities.ProductPojoItem
import com.example.fakeapiretrofit.network.ViewState
import com.example.fakeapiretrofit.repositories.FakeApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FakeApiViewModel @Inject constructor(val fakeApiRepository: FakeApiRepository) : ViewModel() {

    private var _getAppProductItems: MutableLiveData<ViewState<Response<ProductPojo>>> =
        MutableLiveData()
    val getAppProductItems: LiveData<ViewState<Response<ProductPojo>>> = _getAppProductItems

    private var _getPostedProductItems: MutableLiveData<ViewState<Response<ProductPojoItem>>> =
        MutableLiveData()
    val getPostedProductItems: LiveData<ViewState<Response<ProductPojoItem>>> =
        _getPostedProductItems

    fun getAllProducts() {
        _getAppProductItems.postValue(ViewState.Loading())
        viewModelScope.launch {
            try {
                val tempResponse = fakeApiRepository.getAllProducts()
                if (tempResponse.isSuccessful)
                    _getAppProductItems.postValue(ViewState.Success(tempResponse))
                else
                    _getAppProductItems.postValue(
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
        _getPostedProductItems.postValue(ViewState.Loading())
        viewModelScope.launch {
            try {
                val tempResponse = fakeApiRepository.postProduct(productPojo = productPojo)
                if (tempResponse.isSuccessful)
                    _getPostedProductItems.postValue(ViewState.Success(tempResponse))
                else
                    _getPostedProductItems.postValue(
                        ViewState.Error(
                            tempResponse.errorBody().toString()
                        )
                    )
            } catch (e: Exception) {
                Log.i("FakeApiRetrofit", e.message.toString())
            }
        }
    }
}
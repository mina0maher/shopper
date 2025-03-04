package com.mina.shopper.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mina.domain.model.Product
import com.mina.domain.network.ResultWrapper
import com.mina.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getProductUseCase: GetProductUseCase):ViewModel() {
    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }


    fun getProducts(){
        viewModelScope.launch {
            _uiState.value=HomeScreenUIEvents.Loading
            getProductUseCase.execute().let{result ->
                when(result){
                    is ResultWrapper.Success ->{
                        val data = (result as ResultWrapper.Success).value
                        _uiState.value = HomeScreenUIEvents.Success(data)
                    }
                    is ResultWrapper.Failure ->{
                        val message = (result as ResultWrapper.Failure).exception.message?:"Error Occurred"
                        _uiState.value = HomeScreenUIEvents.Error(message)
                    }

                }

            }
        }
    }
}
sealed class HomeScreenUIEvents{
    data object Loading:HomeScreenUIEvents()
    data class Success(val data:List<Product>):HomeScreenUIEvents()
    data class Error(val message:String):HomeScreenUIEvents()
}
package com.jsh.tenqube.presentation.ui.first

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsh.tenqube.domain.usecase.*
import com.jsh.tenqube.domain.util.Result
import com.jsh.tenqube.presentation.SingleLiveEvent
import com.jsh.tenqube.presentation.entity.PresenterShop
import com.jsh.tenqube.presentation.mapper.toPresenterShopList
import kotlinx.coroutines.*

class FirstViewModel  @ViewModelInject constructor(
    private val getShopsUseCase: GetShopsUseCase,
    private val deleteAllShopUseCase: DeleteAllShopUseCase
): ViewModel() {

    private val _shopAndLabelList = MutableLiveData<List<PresenterShop>>()
    val shopAndLabelList: LiveData<List<PresenterShop>> = _shopAndLabelList

    var addButtonClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    var openShopListClicked: SingleLiveEvent<PresenterShop> = SingleLiveEvent()
    var updated: Boolean = true

    init {
        initData(updated)
    }

    private fun initData(isUpdated: Boolean) = viewModelScope.launch {
        getShopsUseCase(isUpdated).let{
            if( it is Result.Success){
                _shopAndLabelList.value = it.data.toPresenterShopList()
            }
        }
        updated = false
    }

    fun reLoadButtonClick(){
        updated = true
        initData(updated)
    }

    fun allDeleteButtonClick() = viewModelScope.launch {
        deleteAllShopUseCase().let {
            if(it is Result.Success) _shopAndLabelList.value = emptyList()
        }
    }

    fun openShopDetails(shop: PresenterShop){
        openShopListClicked.value = shop
    }

    fun addButtonClicked(){
        addButtonClicked.call()
    }
}

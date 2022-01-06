package com.solidlucho.bitcoinwidget
import androidx.lifecycle.*


import com.solidlucho.bitcoinwidget.Model.Cripto
import com.solidlucho.bitcoinwidget.Repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


class MainViewModel(private val repository: Repository):ViewModel() {


    val myReponse = MutableLiveData<Cripto>()

    val _myResponse = flow<Cripto> {
        kotlin.runCatching {
            repository.getUsd()
        }.onSuccess { myReponse.value }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = repository.getUsd().toString()
    ) as StateFlow<*>


}

/*
    init{
        viewModelScope.launch {
            val myResponse = repository.getUsd().asLiveData()
           repository.getUsd().collect {
            myResponse.value
           }

        }

    }
}
*/

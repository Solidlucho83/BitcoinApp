package com.solidlucho.bitcoinwidget.Repository

import com.solidlucho.bitcoinwidget.Core.RetrofitInstance
import com.solidlucho.bitcoinwidget.Model.Cripto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class   Repository {
     fun getUsd() = flow{
     emit(RetrofitInstance.api.getUsd())

    }

}/*
  return RetrofitInstance.api.getUsd()
// Get initial data for the screen
override fun getData() = flow {
    // we could get data from persistence layer here
    emit(Outcome.loading())
    val response = api.getData()
    emit(Outcome.success(response.toSampleData()))
}
    .catch { emit(Outcome.failure(it)) }
    .flowOn(Dispatchers.IO)

// perform action at backend and return response
override fun sendUserAction() = flow {
    emit(Outcome.loading())
    val response = api.performAction().toActionResponse()
    emit(Outcome.success(response))
}
    .catch { emit(Outcome.failure<ActionResponse>(it)) }
    .flowOn(Dispatchers.IO)
}*/

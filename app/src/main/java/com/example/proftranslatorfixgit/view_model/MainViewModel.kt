package com.example.proftranslatorfixgit.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proftranslatorfixgit.model.ModelProvider
import com.example.utils.parseOnlineSearchResults
import kotlinx.coroutines.*

class MainViewModel(
    private val provider: ModelProvider
) : ViewModel() {

    private val liveDataForViewToObserve: MutableLiveData<com.example.model.AppState> = MutableLiveData()

    private val coroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private var coroutineJob: Job? = null

    private fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(com.example.model.AppState.Error(error))
    }

    fun getData(word: String) {
        liveDataForViewToObserve.value = com.example.model.AppState.Loading(null)
        coroutineJob?.cancel()
        coroutineJob = coroutineScope.launch {
            startProvider(word)
        }
    }

    private suspend fun startProvider(word: String) = withContext(Dispatchers.IO) {
        liveDataForViewToObserve.postValue(parseOnlineSearchResults(provider.getData(word)))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = com.example.model.AppState.Success(null)
        super.onCleared()
        coroutineScope.cancel()
    }

    fun subscribe(): LiveData<com.example.model.AppState> {
        return liveDataForViewToObserve
    }
}
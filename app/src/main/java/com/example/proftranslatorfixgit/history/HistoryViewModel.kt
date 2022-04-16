package com.example.proftranslatorfixgit.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.AppState
import com.example.utils.parseLocalSearchResults
import kotlinx.coroutines.*

class HistoryViewModel(
    private val provider: HistoryProvider
) : ViewModel() {

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()

    private val coroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private var coroutineJob: Job? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    private fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    private suspend fun startProvider(word: String) {
        liveDataForViewToObserve.postValue(parseLocalSearchResults(provider.getData(word)))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
        coroutineScope.cancel()
    }

    fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        coroutineJob?.cancel()
        coroutineJob = coroutineScope.launch {
            startProvider(word)
        }
    }
}
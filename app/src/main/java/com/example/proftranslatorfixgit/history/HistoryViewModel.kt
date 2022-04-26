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

    private val _liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val liveData: LiveData<AppState> = _liveDataForViewToObserve

    private val coroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private var coroutineJob: Job? = null

    fun subscribe(): LiveData<AppState> {
        return liveData
    }

    private fun handleError(error: Throwable) {
        _liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    private suspend fun startProvider(word: String) {
        _liveDataForViewToObserve.postValue(parseLocalSearchResults(provider.getData(word)))
    }

    fun getData(word: String) {
        _liveDataForViewToObserve.value = AppState.Loading(null)
        coroutineJob?.cancel()
        coroutineJob = coroutineScope.launch {
            startProvider(word)
        }
    }

    override fun onCleared() {
        _liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
        coroutineScope.cancel()
    }
}
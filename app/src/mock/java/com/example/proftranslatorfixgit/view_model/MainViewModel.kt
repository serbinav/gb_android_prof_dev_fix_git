package com.example.proftranslatorfixgit.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.ApiData
import com.example.model.AppState
import com.example.model.Meanings
import com.example.model.Translation
import com.example.proftranslatorfixgit.model.ModelProvider
import com.example.utils.parseOnlineSearchResults
import kotlinx.coroutines.*

class MainViewModel(
    private val provider: ModelProvider
) : ViewModel() {

    private val mockApiData: ApiData = ApiData(
        "September",
        arrayListOf(
            Meanings(
                Translation("\u0441\u0435\u043d\u0442\u044f\u0431\u0440\u044c"),
                "\\cdn-user77752.skyeng.ru\\resized-images\\640x480\\jpeg\\60\\3c0e381ab5d08df4b6cf9c9e2eb3577a.jpeg",
                "\\cdn-user77752.skyeng.ru\\resized-images\\640x480\\jpeg\\60\\3c0e381ab5d08df4b6cf9c9e2eb3577a.jpeg"
            )
        )
    )
    private val mockAppState: AppState = AppState.Success(listOf(mockApiData))

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()
    private fun getPromotions(): LiveData<AppState> = liveDataForViewToObserve

    private val coroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private var coroutineJob: Job? = null

    fun subscribe(): LiveData<AppState> {
        return getPromotions()
    }

    private fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    private suspend fun startProvider(word: String) = withContext(Dispatchers.IO) {
        liveDataForViewToObserve.postValue(parseOnlineSearchResults(provider.getData(word)))
    }

    fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        coroutineJob?.cancel()
        coroutineJob = coroutineScope.launch {
            startProvider(word)
        }
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
        coroutineScope.cancel()
    }
}
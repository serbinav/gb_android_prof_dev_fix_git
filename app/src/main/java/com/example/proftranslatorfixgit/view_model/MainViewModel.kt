package com.example.mytranslator.view_model

import androidx.lifecycle.LiveData
import com.example.mytranslator.model.RemoteModel
import com.example.mytranslator.retrofit.Data
import com.example.mytranslator.retrofit.Meanings
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: RemoteModel) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String){
        compositeDisposable.add(
            repository.getData(word)
                .map { AppState.Success(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableSingleObserver<AppState> {
        return object : DisposableSingleObserver<AppState>() {

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onSuccess(state: AppState) {
                appState = parseSearchResults(state)
                liveDataForViewToObserve.value = state
            }
        }
    }

    fun parseSearchResults(state: AppState): AppState {
        val newSearchResults = arrayListOf<Data>()
        when (state) {
            is AppState.Success -> {
                val searchResults = state.data
                if (!searchResults.isNullOrEmpty()) {
                    for (searchResult in searchResults) {
                        parseResult(searchResult, newSearchResults)
                    }
                }
            }
        }

        return AppState.Success(newSearchResults)
    }

    private fun parseResult(dataModel: Data, newDataModels: ArrayList<Data>) {
        if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
            val newMeanings = arrayListOf<Meanings>()
            for (meaning in dataModel.meanings) {
                if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                    newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
                }
            }
            if (newMeanings.isNotEmpty()) {
                newDataModels.add(Data(dataModel.text, newMeanings))
            }
        }
    }
}
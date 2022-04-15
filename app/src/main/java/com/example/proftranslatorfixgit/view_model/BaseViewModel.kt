package com.example.mytranslator.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
) : ViewModel() {

    abstract fun getData(word: String)

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
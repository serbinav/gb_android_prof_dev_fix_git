package com.example.mytranslator.presenter

import com.example.mytranslator.MainContract
import com.example.mytranslator.model.RemoteModel
import com.example.mytranslator.model.WordTranslate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter<V : MainContract.View> : MainContract.Presenter<V> {

    private var currentView: V? = null
    private val model = RemoteModel()

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        model.getData(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currentView?.showUsers(
                    it.map { data ->
                        WordTranslate(
                            data.text ?: "",
                            data.meanings?.get(0)?.translation?.translation ?: ""
                        )
                    }
                        .toList())
            }, {
                it.message?.let { err -> currentView?.showError(err) }
            })
    }
}
package com.example.mytranslator

import com.example.mytranslator.model.WordTranslate

interface MainContract {

    interface View {

        fun clickButton()

        fun showError(msg: String)

        fun showUsers(users: List<WordTranslate>)
    }

    interface Presenter<V : View> {

        fun attachView(view: V)

        fun detachView(view: V)

        fun getData(word: String)
    }
}
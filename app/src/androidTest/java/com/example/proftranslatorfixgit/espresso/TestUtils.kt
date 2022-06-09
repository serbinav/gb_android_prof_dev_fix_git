package com.example.proftranslatorfixgit.espresso

import android.content.Context
import android.view.View
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Root
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


object TestUtils {
    const val SEARCH_QUERY = "some query"
    const val TIMEOUT = 5000L

    fun getString(@StringRes stringRes: Int): String {
        try {
            return ApplicationProvider.getApplicationContext<Context>().getString(stringRes)
        } catch (ex: Throwable) {
            throw Exception("Не удалось получить строковый ресурс $stringRes\n${ex.message}")
        }
    }

    class ToastMatcher : TypeSafeMatcher<Root>() {
        override fun describeTo(description: Description) {
            description.appendText("is toast")
        }

        override fun matchesSafely(root: Root): Boolean {
            try {
                val type = root.windowLayoutParams.get().type
                if (type == WindowManager.LayoutParams.TYPE_TOAST) {
                    val windowToken = root.decorView.windowToken
                    val appToken = root.decorView.applicationWindowToken
                    if (windowToken === appToken) {
                        return true
                    }
                }
            } catch (ex: Exception) {
                throw Exception(ex.message)
            }
            return false
        }
    }

    fun delay(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }

    object EspressoTestsMatchers {
        fun withDrawable(resourceId: Int): DrawableMatcher {
            return DrawableMatcher(resourceId)
        }

        fun noDrawable(): DrawableMatcher {
            return DrawableMatcher(-1)
        }
    }
}


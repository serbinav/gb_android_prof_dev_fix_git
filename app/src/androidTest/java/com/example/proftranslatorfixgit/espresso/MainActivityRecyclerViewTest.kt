package com.example.proftranslatorfixgit.espresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.proftranslatorfixgit.R
import com.example.proftranslatorfixgit.view.MainActivity
import com.example.proftranslatorfixgit.view.MainAdapter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityRecyclerViewTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activitySearch_ScrollTo() {
        loadList()

        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.scrollTo<MainAdapter.MainViewHolder>(
                    hasDescendant(withText("mysticism"))
                )
            )
    }

    @Test
    fun activitySearch_PerformClickAtPosition() {
        loadList()

        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MainAdapter.MainViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun activitySearch_PerformClickOnItem() {
        loadList()

        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.scrollTo<MainAdapter.MainViewHolder>(
                    hasDescendant(withText("mysticism"))
                )
            )

        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.actionOnItem<MainAdapter.MainViewHolder>(
                    hasDescendant(withText("mystique")),
                    click()
                )
            )

        onView(withId(R.id.descr_header)).check(
            ViewAssertions.matches((isDisplayed()))
        )

        onView(withId(R.id.descr_header)).check(
            ViewAssertions.matches(
                withText("mystique")
            )
        )
    }

    @Test
    fun activitySearch_PerformCustomClick() {
        loadList()

        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MainAdapter.MainViewHolder>(
                    3,
                    click()
                )
            )

        onView(withId(R.id.descr_header)).check(
            ViewAssertions.matches((isDisplayed()))
        )

        onView(withId(R.id.descr_header)).check(
            ViewAssertions.matches(
                withText("myth")
            )
        )
    }

    private fun loadList() {
        onView(withId(R.id.search_text)).perform(click())
        onView(withId(R.id.search_text)).perform(replaceText("my"), closeSoftKeyboard())
        onView(withId(R.id.search_btn)).perform(click())
    }

    @After
    fun close() {
        scenario.close()
    }
}
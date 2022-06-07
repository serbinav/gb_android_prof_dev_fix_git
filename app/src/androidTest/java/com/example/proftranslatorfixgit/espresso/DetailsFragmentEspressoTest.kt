package com.example.proftranslatorfixgit.espresso

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.proftranslatorfixgit.R
import com.example.proftranslatorfixgit.view.DescriptionFragment
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DescriptionFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<DescriptionFragment>

    @Before
    fun setup() {

    }

    @Test
    fun fragment_testBundle() {
        //Можно передавать аргументы во Фрагмент, но это необязательно
        val fragmentArgs = bundleOf(
            "b1990e8e-045d-4420-b54a-4d87c144703c" to "September",
            "c6f5d263-1888-4c86-8141-c04115948a42" to "\u0441\u0435\u043d\u0442\u044f\u0431\u0440\u044c",
            "d6b0ab4d-d3e1-47b0-bd7a-86a6135a9935" to "\\cdn-user77752.skyeng.ru\\resized-images\\640x480\\jpeg\\60\\3c0e381ab5d08df4b6cf9c9e2eb3577a.jpeg"
        )
        //Запускаем Fragment с аргументами
        val scenario = launchFragmentInContainer<DescriptionFragment>(
            fragmentArgs,
            themeResId = R.style.Theme_ProfTranslatorFixGit
        )
        //Возможность менять стейт Фрагмента
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.descr_header)).check(matches(withText("September")))
        onView(withId(R.id.descr_text)).check(
            matches(withText("сентябрь"))
        )
        onView(withId(R.id.close)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.descr_img)).check(
            matches(
                TestUtils.EspressoTestsMatchers.withDrawable(R.drawable.no_img_200_200)
            )
        )

        onView(ViewMatchers.isRoot()).perform(TestUtils.delay())
        onView(withId(R.id.descr_img)).check(
            matches(
                Matchers.not(
                    TestUtils.EspressoTestsMatchers.withDrawable(R.drawable.no_img_200_200)
                )
            )
        )
    }

    @Test
    fun fragment_LoadPhotoError() {
        val fragmentArgs = bundleOf(
            "b1990e8e-045d-4420-b54a-4d87c144703c" to "",
            "c6f5d263-1888-4c86-8141-c04115948a42" to "",
            "d6b0ab4d-d3e1-47b0-bd7a-86a6135a9935" to "3c0e381ab5d08df4b6cf9c9e2eb3577a.jpeg"
        )

        val scenario = launchFragmentInContainer<DescriptionFragment>(
            fragmentArgs,
            themeResId = R.style.Theme_ProfTranslatorFixGit
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.descr_img)).check(
            matches(
                TestUtils.EspressoTestsMatchers.withDrawable(R.drawable.no_img_200_200)
            )
        )

        onView(ViewMatchers.isRoot()).perform(TestUtils.delay())
        onView(withId(R.id.descr_img)).check(
            matches(
                Matchers.not(
                    TestUtils.EspressoTestsMatchers.withDrawable(R.drawable.ic_baseline_error_32)
                )
            )
        )
    }

    @Test
    fun fragment_visible_elements() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer()

        onView(withId(R.id.descr_header)).check(
            matches(Matchers.not(ViewMatchers.isDisplayed()))
        )
        onView(withId(R.id.descr_text)).check(
            matches(Matchers.not(ViewMatchers.isDisplayed()))
        )
        onView(withId(R.id.btn_blur)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.close)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.descr_img)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.descr_img)).check(
            matches(
                TestUtils.EspressoTestsMatchers.withDrawable(R.drawable.no_img_200_200)
            )
        )
    }
}

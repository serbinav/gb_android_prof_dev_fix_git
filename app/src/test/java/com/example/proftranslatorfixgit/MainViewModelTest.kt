package com.example.proftranslatorfixgit

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.model.AppState
import com.example.proftranslatorfixgit.model.ModelProvider
import com.example.proftranslatorfixgit.view_model.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var searchViewModel: MainViewModel

    @Mock
    private lateinit var model: ModelProvider

//    @Mock
//    private lateinit var repository: FakeGitHubRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        searchViewModel = MainViewModel(model)
    }

    @Test
    fun coroutines_TestReturnValueIsNotNull() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = searchViewModel.subscribe()

//            `when`(repository.searchGithubAsync(SEARCH_QUERY)).thenReturn(
//                SearchResponse(1, listOf())
//            )

            try {
                liveData.observeForever(observer)
                searchViewModel.getData(SEARCH_QUERY)
                Assert.assertNotNull(liveData.value)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestReturnValueIsError() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = searchViewModel.subscribe()

//            `when`(repository.searchGithubAsync(SEARCH_QUERY)).thenReturn(
//                SearchResponse(null, listOf())
//            )

            try {
                liveData.observeForever(observer)
                searchViewModel.getData(SEARCH_QUERY)

                val value: AppState.Error = liveData.value as AppState.Error
                Assert.assertEquals(value.error.message, ERROR_TEXT)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestException() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = searchViewModel.subscribe()

            try {
                liveData.observeForever(observer)
                searchViewModel.getData(SEARCH_QUERY)

                val value: AppState.Error = liveData.value as AppState.Error
                Assert.assertEquals(value.error.message, EXCEPTION_TEXT)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    companion object {
        private const val SEARCH_QUERY = "some query"
        private const val ERROR_TEXT = "Search results or total count are null"
        private const val EXCEPTION_TEXT = "Response is null or unsuccessful"
    }
}
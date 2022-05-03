package com.example.proftranslatorfixgit.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.model.ApiData
import com.example.model.AppState
import com.example.proftranslatorfixgit.history.HistoryActivity
import com.example.proftranslatorfixgit.R
import com.example.proftranslatorfixgit.databinding.ActivityMainBinding
import com.example.proftranslatorfixgit.view_model.MainViewModel
import com.example.utils.convertMeaningsToSingleString
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

private const val DESCRIPTION_FRAGMENT_DIALOG_TAG = "ad19a4e1-7838-42c0-b0f7-742ec6973640"
private const val SLIDE_LEFT_DURATION = 3000L
private const val COUNTDOWN_DURATION = 1000L
private const val COUNTDOWN_INTERVAL = 500L

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val scope: Scope by lazy {
        getKoin().createScope(
            this.toString(),
            named("MainActivity")
        )
    }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: ApiData) {
                val searchDialogFragment =
                    DescriptionFragment.newInstance(
                        data.text,
                        convertMeaningsToSingleString(data.meanings),
                        data.meanings[0].imageUrl
                    )
                searchDialogFragment.show(supportFragmentManager, DESCRIPTION_FRAGMENT_DIALOG_TAG)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.mainToolbar)
        setDefaultSplashScreen()

        initViewModel()
        initViews()
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response))
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun initViewModel() {
        if (binding.recycler.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        model = scope.get()
        model.subscribe().observe(this@MainActivity) { renderData(it) }
    }

    private fun initViews() {
        binding.searchBtn.setOnClickListener {
            model.getData(binding.searchText.text.toString())
        }
        binding.recycler.adapter = adapter
    }

    private fun setDefaultSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreenHideAnimation()
        }

        setSplashScreenDuration()
    }

    private fun setSplashScreenDuration() {
        var isHideSplashScreen = false

        object : CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    @RequiresApi(31)
    private fun setSplashScreenHideAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = SLIDE_LEFT_DURATION

            slideUp.doOnEnd { splashScreenView.remove() }

            slideUp.start()
        }
    }

    private fun showErrorScreen(error: String?) {
        binding.errorText.text = error ?: getString(R.string.undefined_error)
        binding.reloadBtn.setOnClickListener {
            model.getData("test")
        }
    }
}
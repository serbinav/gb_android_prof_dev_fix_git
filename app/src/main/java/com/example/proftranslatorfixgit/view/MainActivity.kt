package com.example.proftranslatorfixgit.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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

class MainActivity : AppCompatActivity() {

// - Подготовьте своё приложение к Android 12 с учётом всех новых фич и
//     ограничений операционной системы (обратите пристальное внимание на изменения в
//     Android 10 — материалы прилагаются к уроку);
// - Добавьте новый Splash Screen к своему приложению с помощью Splash Screen API;
// - Прочитайте дополнительную методичку про нововведения в Android 10;
// - * Добавьте анимированную иконку на Splash screen.

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

    private fun showErrorScreen(error: String?) {
        binding.errorText.text = error ?: getString(R.string.undefined_error)
        binding.reloadBtn.setOnClickListener {
            model.getData("test")
        }
    }

    companion object {
        private const val DESCRIPTION_FRAGMENT_DIALOG_TAG =
            "ad19a4e1-7838-42c0-b0f7-742ec6973640"
    }
}
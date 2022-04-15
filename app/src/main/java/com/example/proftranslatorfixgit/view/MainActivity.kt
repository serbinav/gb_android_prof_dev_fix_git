package com.example.mytranslator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslator.R
import com.example.mytranslator.databinding.ActivityMainBinding
import com.example.mytranslator.view_model.AppState
import com.example.mytranslator.view_model.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var model: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = viewModelFactory.create(MainViewModel::class.java)
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })

        binding.searchBtn.setOnClickListener {
            model.getData(binding.searchText.text.toString())
        }

        binding.recycler.layoutManager = LinearLayoutManager(applicationContext)
        binding.recycler.adapter = adapter
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

    private fun showErrorScreen(error: String?) {
        binding.errorText.text = error ?: getString(R.string.undefined_error)
        binding.reloadBtn.setOnClickListener {
            model.getData("test")
        }
    }
}
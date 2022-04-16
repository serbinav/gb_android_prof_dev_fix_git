package com.example.proftranslatorfixgit.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.proftranslatorfixgit.history.HistoryActivity
import com.example.proftranslatorfixgit.R
import com.example.proftranslatorfixgit.databinding.ActivityMainBinding
import com.example.proftranslatorfixgit.view_model.MainViewModel
import com.example.utils.convertMeaningsToString
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: com.example.model.ApiData) {
                val searchDialogFragment =
                DescriptionFragment.newInstance(
                    data.text!!,
                    convertMeaningsToString(data.meanings!!),
                    data.meanings!![0].imageUrl
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

    fun renderData(appState: com.example.model.AppState) {
        when (appState) {
            is com.example.model.AppState.Success -> {
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response))
                } else {
                    adapter.setData(data)
                }
            }
            is com.example.model.AppState.Loading -> {

            }
            is com.example.model.AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun initViewModel() {
        if (binding.recycler.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
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
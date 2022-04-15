package com.example.mytranslator.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mytranslator.R
import com.example.mytranslator.databinding.ActivityHistoryBinding
import com.example.mytranslator.view_model.AppState
import com.example.mytranslator.view_model.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("")
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.undefined_error),
                        getString(R.string.empty_server_response)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                showAlertDialog(getString(R.string.undefined_error), appState.error.message)
            }
        }
    }

    private fun showAlertDialog(title: String?, message: String?) {
        this.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.positive_button) {
                        dialog, _ ->  dialog.cancel()
                }
            builder.create()
        }
    }

    private fun initViewModel() {
        if (binding.recycler.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity) { renderData(it) }
    }

    private fun initViews() {
        binding.recycler.adapter = adapter
    }
}
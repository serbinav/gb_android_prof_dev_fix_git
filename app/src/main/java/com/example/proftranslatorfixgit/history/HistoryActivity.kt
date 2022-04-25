package com.example.proftranslatorfixgit.history

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.model.AppState
import com.example.proftranslatorfixgit.R
import com.example.proftranslatorfixgit.databinding.ActivityHistoryBinding
import com.example.utils.FashionPref
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class HistoryActivity : AppCompatActivity(), KoinScopeComponent {

    private lateinit var binding: ActivityHistoryBinding
    private val model: HistoryViewModel by inject()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }
    override val scope: Scope by getOrCreateScope()
    private val sharedPref: SharedPreferences by lazy {
        getSharedPreferences(
            "HistoryActivity",
            MODE_PRIVATE
        )
    }

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

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
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
                    val pref = FashionPref(sharedPref)
                    pref.countWord = data.size
                    pref.lastWord = data.last().text ?: "empty"
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
                .setPositiveButton(R.string.positive_button) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        }
    }

    private fun initViewModel() {
        if (binding.recycler.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        model.subscribe().observe(this@HistoryActivity) { renderData(it) }
    }

    private fun initViews() {
        binding.recycler.adapter = adapter
    }
}
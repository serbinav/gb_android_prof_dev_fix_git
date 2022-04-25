package com.example.proftranslatorfixgit.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.ApiData
import com.example.proftranslatorfixgit.databinding.ActivityHistoryRecyclerviewItemBinding
import com.google.android.material.snackbar.Snackbar

class HistoryAdapter :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var data: List<ApiData> = arrayListOf()

    fun setData(data: List<ApiData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val binding =
            ActivityHistoryRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class HistoryViewHolder(private val binding: ActivityHistoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ApiData) {
            with(binding) {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    wordOriginal.text = data.text
                    itemView.setOnClickListener {
                        Snackbar.make(itemView, "on click: ${data.text}", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
}
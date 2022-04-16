package com.example.proftranslatorfixgit.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proftranslatorfixgit.databinding.ActivityMainRecyclerviewItemBinding
import com.example.model.ApiData

class MainAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var data: List<com.example.model.ApiData> = arrayListOf()

    fun setData(data: List<com.example.model.ApiData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val binding =
            ActivityMainRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MainViewHolder(private val binding: ActivityMainRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: com.example.model.ApiData) {
            with(binding) {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    wordOriginal.text = data.text.orEmpty()
                    wordTranslate.text = data.meanings?.get(0)?.translation?.translation.orEmpty()
                    itemView.setOnClickListener { openInNewWindow(data) }
                }
            }
        }
    }

    private fun openInNewWindow(listItemData: com.example.model.ApiData) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: com.example.model.ApiData)
    }
}
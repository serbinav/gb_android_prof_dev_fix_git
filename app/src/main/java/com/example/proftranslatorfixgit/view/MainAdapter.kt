package com.example.mytranslator.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytranslator.databinding.ActivityMainRecyclerviewItemBinding
import com.example.mytranslator.retrofit.ApiData

class MainAdapter:
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var data: List<ApiData> = arrayListOf()

    fun setData(data: List<ApiData>) {
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

        fun bind(data: ApiData) {
            with(binding) {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    wordOriginal.text = data.text.orEmpty()
                    wordTranslate.text = data.meanings?.get(0)?.translation?.translation.orEmpty()
                }
            }
        }
    }
}
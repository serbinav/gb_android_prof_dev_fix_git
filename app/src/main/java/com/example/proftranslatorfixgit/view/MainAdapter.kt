package com.example.mytranslator.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytranslator.databinding.ActivityMainRecyclerviewItemBinding
import com.example.mytranslator.model.WordTranslate

class MainAdapter(
    private var data: List<WordTranslate>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    fun setData(data: List<WordTranslate>) {
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

        fun bind(data: WordTranslate) {
            with(binding) {
                wordOriginal.text = data.original
                wordTranslate.text = data.translate
            }
        }
    }
}
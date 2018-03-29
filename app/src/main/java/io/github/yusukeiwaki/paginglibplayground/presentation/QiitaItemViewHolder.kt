package io.github.yusukeiwaki.paginglibplayground.presentation

import android.support.v7.widget.RecyclerView
import io.github.yusukeiwaki.paginglibplayground.databinding.ItemQiitaItemBinding
import io.github.yusukeiwaki.paginglibplayground.model.QiitaItem

class QiitaItemViewHolder(private val binding: ItemQiitaItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: QiitaItem) {
        binding.item = item
    }
}

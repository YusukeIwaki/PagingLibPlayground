package io.github.yusukeiwaki.paginglibplayground.presentation

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.yusukeiwaki.paginglibplayground.databinding.ItemQiitaItemBinding
import io.github.yusukeiwaki.paginglibplayground.model.QiitaItem

class QiitaItemListAdapter(context: Context) : PagedListAdapter<QiitaItem, QiitaItemViewHolder>(QiitaItemDiffUtilItemCallback) {
    private val inflater = LayoutInflater.from(context)

    var onItemClick: ((QiitaItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QiitaItemViewHolder {
        val binding = ItemQiitaItemBinding.inflate(inflater, parent, false)
        return QiitaItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QiitaItemViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}

package io.github.yusukeiwaki.paginglibplayground.presentation

import android.support.v7.util.DiffUtil
import io.github.yusukeiwaki.paginglibplayground.model.QiitaItem

object QiitaItemDiffUtilItemCallback : DiffUtil.ItemCallback<QiitaItem>() {
    override fun areItemsTheSame(oldItem: QiitaItem?, newItem: QiitaItem?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: QiitaItem?, newItem: QiitaItem?): Boolean {
        return oldItem?.hashCode() == newItem?.hashCode()
    }
}

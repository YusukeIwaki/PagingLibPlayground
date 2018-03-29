package io.github.yusukeiwaki.paginglibplayground.model

data class QiitaItem(
        val id: String,
        val title: String,
        val body: String,
        val user: QiitaUser,
        val url: String)


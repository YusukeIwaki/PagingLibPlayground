package io.github.yusukeiwaki.paginglibplayground.paging

class LoadingState private constructor(private val state: Int, private val error: Exception? = null) {
    companion object {
        private const val LOADING = 1
        private const val SUCCESS = 2
        private const val FAILURE = 3

        fun ofLoading() = LoadingState(LOADING)
        fun ofSuccess() = LoadingState(SUCCESS)
        fun ofFailure(e: Exception) = LoadingState(FAILURE, e)
    }

    val isLoading get() = state == LOADING
    fun onFailure(errorHandler: (Exception) -> Unit) {
        if (state == FAILURE) {
            error?.let { errorHandler(it) }
        }
    }
}

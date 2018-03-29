package io.github.yusukeiwaki.paginglibplayground.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.github.yusukeiwaki.paginglibplayground.R
import io.github.yusukeiwaki.paginglibplayground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        val adapter = QiitaItemListAdapter(this)
        adapter.onItemClick = { item ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)).also { intent ->
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        viewModel.qiitaItemPagedList().observe(this, Observer { pagedList ->
            adapter.submitList(pagedList)
        })
        binding.swipeRefreshLayout.setOnRefreshListener {
            // TODO: force fetch.
        }
        viewModel.qiitaItemLoadingStateLiveData().observe(this, Observer { loadingState ->
            loadingState?.let {
                binding.swipeRefreshLayout.isRefreshing = it.isLoading
            }
        })
    }
}

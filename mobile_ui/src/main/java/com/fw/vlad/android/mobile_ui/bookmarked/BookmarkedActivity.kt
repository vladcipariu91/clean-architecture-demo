package com.fw.vlad.android.mobile_ui.bookmarked

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fw.vlad.android.mobile_ui.R
import com.fw.vlad.android.mobile_ui.injection.ViewModelFactory
import com.fw.vlad.android.mobile_ui.mapper.ProjectViewMapper
import com.fw.vlad.android.presentation.BrowseBookmarkedProjectsViewModel
import com.fw.vlad.android.presentation.model.ProjectView
import com.fw.vlad.android.presentation.state.Resource
import com.fw.vlad.android.presentation.state.ResourceState
import dagger.android.AndroidInjection

import javax.inject.Inject

class BookmarkedActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: BookmarkedAdapter
    @Inject
    lateinit var mapper: ProjectViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var browseViewModel: BrowseBookmarkedProjectsViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var progressView: ProgressBar

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked)
        recyclerView = findViewById(R.id.recycler_projects)
        progressView = findViewById(R.id.progress)
        AndroidInjection.inject(this)
        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BrowseBookmarkedProjectsViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this,
                Observer<Resource<List<ProjectView>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchProjects()
    }

    private fun setupBrowseRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                progressView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                resource.data?.let {
                    adapter.projects = it.map { mapper.mapToView(it) }
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.LOADING -> {
                progressView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
    }
}
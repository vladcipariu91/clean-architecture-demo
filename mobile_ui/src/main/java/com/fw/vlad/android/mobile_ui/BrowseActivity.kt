package com.fw.vlad.android.mobile_ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_browse.*

class BrowseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        setupBrowseRecyclerView()
    }

    private fun setupBrowseRecyclerView() {
        recycler_projects.layoutManager = LinearLayoutManager(this)
    }
}
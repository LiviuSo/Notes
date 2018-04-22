package com.kotlin.lvicto.notes.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.lvicto.notes.R
import com.kotlin.lvicto.notes.adapters.NotesAdapter
import com.kotlin.lvicto.notes.lifecycle.NotesLifecycleManager
import com.kotlin.lvicto.notes.model.Note
import com.kotlin.lvicto.notes.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "MainActivity"
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        bindNotesLifecycleObserver()
    }

    fun initUI() {
        val recView = findViewById<RecyclerView>(R.id.rec_view)
        val dummyData = viewModel.notes
        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = NotesAdapter(this, dummyData)

        val addNoteBtn = findViewById<FloatingActionButton?>(R.id.add)
        addNoteBtn!!.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindNotesLifecycleObserver() {
        NotesLifecycleManager.bindLifecycle(this, viewModel, applicationContext)
    }
}
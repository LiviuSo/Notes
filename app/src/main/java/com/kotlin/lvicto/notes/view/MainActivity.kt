package com.kotlin.lvicto.notes.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.kotlin.lvicto.notes.R
import com.kotlin.lvicto.notes.adapters.NotesAdapter
import com.kotlin.lvicto.notes.db.AppDatabase
import com.kotlin.lvicto.notes.db.Note
import com.kotlin.lvicto.notes.lifecycle.NotesLifecycleManager
import com.kotlin.lvicto.notes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "MainActivity"
    private lateinit var viewModel: MainViewModel


    private lateinit var mDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        bindNotesLifecycleObserver()
    }

    private fun fetchData() {
        mDb.notesDao().findAllNotesSync().forEach {
            Log.d(LOG_TAG, it.toString())
        }
    }

    private fun populateDb() {
        viewModel.notes.forEach {
            mDb.notesDao().insertNote(Note(it.title, it.description))
        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
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

        // Room "hello world!"
        mDb = AppDatabase.getInMemoryDatabase(this)
        populateDb()
        fetchData()
    }

    private fun bindNotesLifecycleObserver() {
        NotesLifecycleManager.bindLifecycle(this, viewModel, applicationContext)
    }
}
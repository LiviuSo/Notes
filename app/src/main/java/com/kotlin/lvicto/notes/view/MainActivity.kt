package com.kotlin.lvicto.notes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.lvicto.notes.R
import com.kotlin.lvicto.notes.model.Note
import com.kotlin.lvicto.notes.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = "MainActivity"
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.saveData(this)

        val recView = findViewById<RecyclerView>(R.id.rec_view)
//        val dummyData = viewModel.notes
        val dummyData = viewModel.getData(this)
        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = NotesAdapter(this, dummyData)

        val addNoteBtn = findViewById<FloatingActionButton?>(R.id.add)
        addNoteBtn!!.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        Log.d(LOG_TAG, viewModel.toJson())

    }
}

class NotesAdapter(private val context: Context, val data: ArrayList<Note>) : RecyclerView.Adapter<NotesAdapter.NoteView>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NoteView, position: Int) {
        holder.bindDataToView(data[position], View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_NOTE_TITLE, data[position].title)
            context.startActivity(intent)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteView(view)
    }

    class NoteView(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindDataToView(data: Note, clickListener: View.OnClickListener) {
            view.setOnClickListener(clickListener)

            view.findViewById<TextView>(R.id.itemTitle)?.text = data.title
            view.findViewById<TextView>(R.id.itemType)?.text = data.type.getName().toUpperCase()
            view.findViewById<TextView>(R.id.itemDescription)?.text = data.description
            view.findViewById<TextView>(R.id.itemTags)?.text = data.tags[0].name
            view.findViewById<TextView>(R.id.itemRemainingTagsCount)?.text = "+ ${data.tags.size} more"
        }
    }
}

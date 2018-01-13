package com.kotlin.lvicto.notes

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recView = findViewById<RecyclerView>(R.id.rec_view)
        val dummies = arrayListOf("Note 1", "Note 2", "etc")
        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = NotesAdapter(this, dummies)

        val addNoteBtn = findViewById<FloatingActionButton>(R.id.add)
        addNoteBtn.setOnClickListener { Toast.makeText(this@MainActivity, "Add note", Toast.LENGTH_SHORT).show() }
    }
}

class NotesAdapter(private val context: Context, val data: ArrayList<String>) : RecyclerView.Adapter<NotesAdapter.NoteView>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NoteView, position: Int) {
        holder.bindDataToView(data[position], View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_NOTE_TITLE, data[position])
            context.startActivity(intent)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteView(view)
    }

    class NoteView(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindDataToView(data: String, clickListener: View.OnClickListener) {
            view.setOnClickListener(clickListener)
            val textView = view.findViewById<TextView>(R.id.description)
            textView?.text = data
        }
    }
}

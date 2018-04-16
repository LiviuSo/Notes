package com.kotlin.lvicto.notes.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlin.lvicto.notes.R
import com.kotlin.lvicto.notes.model.Note
import com.kotlin.lvicto.notes.view.DetailActivity


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

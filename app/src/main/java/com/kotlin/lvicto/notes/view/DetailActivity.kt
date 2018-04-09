package com.kotlin.lvicto.notes.view

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.kotlin.lvicto.notes.R

class DetailActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val noteTitle = intent.getStringExtra(EXTRA_NOTE_TITLE)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = noteTitle
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val text = findViewById<TextView>(R.id.text)
        text.text = noteTitle
    }

    companion object {
        const val EXTRA_NOTE_TITLE = "extra_note_title"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
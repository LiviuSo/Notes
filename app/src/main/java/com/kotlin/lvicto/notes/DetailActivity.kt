package com.kotlin.lvicto.notes

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val noteTitle = intent.getStringExtra(EXTRA_NOTE_TITLE)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = noteTitle
        setSupportActionBar(toolbar)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        val text = findViewById<TextView>(R.id.text)
        text.text = noteTitle
    }

    companion object {
        val EXTRA_NOTE_TITLE = "extra_note_title"
    }
}
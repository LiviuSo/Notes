package com.kotlin.lvicto.notes

import android.annotation.SuppressLint
import android.app.Application

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: NotesApplication
            private set
    }
}
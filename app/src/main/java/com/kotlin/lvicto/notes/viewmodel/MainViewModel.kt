package com.kotlin.lvicto.notes.viewmodel

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlin.lvicto.notes.model.Note
import com.kotlin.lvicto.notes.model.NoteTag
import com.kotlin.lvicto.notes.model.NoteType
import java.util.*

class MainViewModel {
    val jsonFile = "json"

    var notes: ArrayList<Note> = arrayListOf() // todo remove initialization
    val json = "[{\"dateCreated\":\"1/1/18\",\"description\":\"Some NICE stuff from an episode of Ancient Aliens related to PUMA-PUMKU\",\"tags\":[{\"name\":\"Ancient Aliens\"},{\"name\":\"Bolivia\"}],\"title\":\"Over the hills and far away\",\"type\":\"TEXT\"},{\"dateCreated\":\"1/1/18\",\"description\":\"Some interesting stuff from an episode of Ancient Aliens related to Titicaca\",\"tags\":[{\"name\":\"Ancient Aliens\"},{\"name\":\"Bolivia\"}],\"title\":\"Over the hills and far away\",\"type\":\"TEXT\"}]"

    private fun getData(): ArrayList<Note> {
        return arrayListOf(
                Note("Over the hills and far away",
                        null,
                        "Some interesting stuff from an episode of Ancient Aliens related to Titicaca",
                        arrayListOf(NoteTag("Ancient Aliens"),
                                NoteTag("Bolivia")),
                        "1/1/18",
                        null,
                        NoteType.TEXT),
                Note("Over the hills and far away",
                        null,
                        "Some interesting stuff from an episode of Ancient Aliens related to Titicaca",
                        arrayListOf(NoteTag("Ancient Aliens"),
                                NoteTag("Bolivia")),
                        "1/1/18",
                        null,
                        NoteType.TEXT))
    }

    private fun saveDataToFile(context: Context, json: String) {
        context.openFileOutput(jsonFile, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    private fun getDataFromFile(context: Context): String {
        val ba = arrayListOf<Byte>()
        context.openFileInput(jsonFile).use {
            do {
                val c = it.read()
                if(c == -1) {
                    break
                }
                ba.add(c.toByte())
            } while (true)
            val data = String(ba.toByteArray())
            return data
        }
    }

    fun saveData(context: Context) {
        saveDataToFile(context, Gson().toJson(getData()))
    }

    fun getData(context: Context): ArrayList<Note> {
        val json = getDataFromFile(context)
        return Gson().fromJson(json, object : TypeToken<ArrayList<Note>>() {}.type)
    }

    init {
//        notes = getData()
//        notes = getData(Context.app) // todo fix
    }

    fun toJson(): String {
        return Gson().toJson(notes)
    }
}
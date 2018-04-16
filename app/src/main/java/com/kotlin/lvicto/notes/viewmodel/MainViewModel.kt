package com.kotlin.lvicto.notes.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlin.lvicto.notes.datamodel.NotesDataModel
import com.kotlin.lvicto.notes.model.Note
import io.reactivex.Observable

class MainViewModel : ViewModel() {

    lateinit var notes: ArrayList<Note>
    private val dataModel = NotesDataModel()

    fun saveData(context: Context): Observable<Unit> {
        return dataModel.saveDataToFile(context, Gson().toJson(notes)) //(NotesApplication.instance.applicationContext)))
    }

    fun getData(context: Context): Observable<ArrayList<Note>> {
        return dataModel
                .getDataFromFile(context)
                .map {
                    Gson().fromJson<ArrayList<Note>>(it, object : TypeToken<ArrayList<Note>>() {}.type)
                }
    }
}
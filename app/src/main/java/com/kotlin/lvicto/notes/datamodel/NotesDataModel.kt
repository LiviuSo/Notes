package com.kotlin.lvicto.notes.datamodel

import android.content.Context
import android.util.Log
import io.reactivex.Observable

/**
 * Data source that provides/saves notes
 */
class NotesDataModel {

    val json = "[{\"dateCreated\":\"1/1/18\",\"description\":\"Some NICE stuff from an episode of Ancient Aliens related to PUMA-PUMKU\",\"tags\":[{\"name\":\"Ancient Aliens\"},{\"name\":\"Bolivia\"}],\"title\":\"Over the hills and far away\",\"type\":\"TEXT\"},{\"dateCreated\":\"1/1/18\",\"description\":\"Some interesting stuff from an episode of Ancient Aliens related to Titicaca\",\"tags\":[{\"name\":\"Ancient Aliens\"},{\"name\":\"Bolivia\"}],\"title\":\"Over the hills and far away\",\"type\":\"TEXT\"}]"

    fun saveDataToFile(context: Context, json: String): Observable<Unit> {
        Log.d(TAG, "Save to file")
        return Observable.fromCallable {
            context.openFileOutput(jsonFile, Context.MODE_PRIVATE).use {
                it.write(json.toByteArray())
            }
        }
    }

    fun getDataFromFile(context: Context): Observable<String> {
        return Observable.fromCallable {
            val ba = arrayListOf<Byte>()
            try {
                context.openFileInput(jsonFile).use {
                    Log.d(TAG, "Load from file")
                    do {
                        val c = it.read()
                        if (c == -1) {
                            break
                        }
                        ba.add(c.toByte())
                    } while (true)
                    String(ba.toByteArray())
                }
            } catch (e: Exception) {
                Log.d(TAG, "Load from json")
                 json  // todo remove then zero state implemented
            }
        }
    }

    companion object {
        private const val TAG = "NotesDataModel"
        private const val jsonFile = "json"
    }
}
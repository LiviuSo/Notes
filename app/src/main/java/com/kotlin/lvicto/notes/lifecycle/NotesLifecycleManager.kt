package com.kotlin.lvicto.notes.lifecycle

import android.arch.lifecycle.*
import android.content.Context
import com.kotlin.lvicto.notes.view.MainActivity
import com.kotlin.lvicto.notes.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  Created on 4/21/18.
 *  Copyright © 2016 Nike, Inc. All rights reserved.
 */

object NotesLifecycleManager {

    fun bindLifecycle(lcOwner: LifecycleOwner,
                      viewModel: MainViewModel,
                      context: Context): NotesLifecycleObserver = NotesLifecycleObserver(lcOwner, viewModel, context)

    class NotesLifecycleObserver : LifecycleObserver {

        private val lcOwner: LifecycleOwner
        private val viewModel: MainViewModel
        private val context: Context

        constructor(lcOwner: LifecycleOwner, viewModel: MainViewModel, context: Context) {
            this.lcOwner = lcOwner
            this.viewModel = viewModel
            this.context = context
            this.lcOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            viewModel.getData(context)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        viewModel.notes = it
                        (lcOwner as MainActivity).initUI()
                    }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            viewModel.saveData(context)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
    }
}

package com.kotlin.lvicto.notes.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *  Created on 5/1/18.
 *  Copyright © 2016 Nike, Inc. All rights reserved.
 */
@Entity
data class Note(var title: String,
                var descr: String?,
                @PrimaryKey(autoGenerate = true) var id: Int = 0) {

    override fun toString(): String = "$title + $descr"
}
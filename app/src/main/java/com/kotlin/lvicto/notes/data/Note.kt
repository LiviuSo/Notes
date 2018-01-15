package com.kotlin.lvicto.notes.data

data class Note(var title: String,
                var content: Any?,
                var description: String?,
                var tags: List<NoteTag>,
                var dateCreated: String,
                var dateModified: String?,
                var type: NoteType)

data class NoteTag(var name: String)

enum class NoteType(private val value: String) {
    TEXT("Text"),
    IMAGE("Image"),
    VIDEO("Video");

    fun getName() = value
}
package com.example.notekeeperapp.data

import android.content.ContentValues
import com.example.notekeeperapp.model.Note

class NoteDao(private val dbHelper: NoteDatabaseHelper) {

    fun addNote(note: Note): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(NoteDatabaseHelper.COLUMN_TITLE, note.title)
            put(NoteDatabaseHelper.COLUMN_CONTENT, note.content)
        }
        return db.insert(NoteDatabaseHelper.TABLE_NOTES, null, values)
    }

    fun getAllNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            NoteDatabaseHelper.TABLE_NOTES,
            null, null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(NoteDatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(NoteDatabaseHelper.COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(NoteDatabaseHelper.COLUMN_CONTENT))
                notes.add(Note(id, title, content))
            }
            close()
        }
        return notes
    }

    fun updateNote(note: Note): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(NoteDatabaseHelper.COLUMN_TITLE, note.title)
            put(NoteDatabaseHelper.COLUMN_CONTENT, note.content)
        }
        return db.update(
            NoteDatabaseHelper.TABLE_NOTES,
            values,
            "${NoteDatabaseHelper.COLUMN_ID}=?",
            arrayOf(note.id.toString())
        )
    }

    fun deleteNote(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete(
            NoteDatabaseHelper.TABLE_NOTES,
            "${NoteDatabaseHelper.COLUMN_ID}=?",
            arrayOf(id.toString())
        )
    }
}
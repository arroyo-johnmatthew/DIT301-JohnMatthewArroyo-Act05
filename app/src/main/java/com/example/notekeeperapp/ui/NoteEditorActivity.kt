package com.example.notekeeperapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeperapp.R
import com.example.notekeeperapp.data.NoteDao
import com.example.notekeeperapp.data.NoteDatabaseHelper
import com.example.notekeeperapp.model.Note

class NoteEditorActivity : AppCompatActivity() {

    private lateinit var dao: NoteDao
    private var noteId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        dao = NoteDao(NoteDatabaseHelper(this))

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Check if editing an existing note
        noteId = intent.getIntExtra("note_id", -1).takeIf { it != -1 }
        if (noteId != null) {
            etTitle.setText(intent.getStringExtra("note_title"))
            etContent.setText(intent.getStringExtra("note_content"))
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            if (noteId == null) {
                dao.addNote(Note(title = title, content = content))
            } else {
                dao.updateNote(Note(id = noteId!!, title = title, content = content))
            }
            finish() // return to MainActivity
        }
    }
}
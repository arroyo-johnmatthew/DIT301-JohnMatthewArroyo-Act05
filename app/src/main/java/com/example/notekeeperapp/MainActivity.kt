package com.example.notekeeperapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeperapp.data.NoteDao
import com.example.notekeeperapp.data.NoteDatabaseHelper
import com.example.notekeeperapp.model.Note
import com.example.notekeeperapp.ui.NoteAdapter
import com.example.notekeeperapp.ui.NoteEditorActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var dao: NoteDao
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = NoteDao(NoteDatabaseHelper(this))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = NoteAdapter(
            notes = mutableListOf(),
            onEdit = { note ->
                val intent = Intent(this, NoteEditorActivity::class.java).apply {
                    putExtra("note_id", note.id)
                    putExtra("note_title", note.title)
                    putExtra("note_content", note.content)
                }
                startActivity(intent)
            },
            onDeleteConfirmed = { note ->
                dao.deleteNote(note.id)
                loadNotes()
            }
        )
        recyclerView.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, NoteEditorActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes() {
        adapter.updateData(dao.getAllNotes())
    }
}
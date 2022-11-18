package com.example.notepad.domain.use_cases

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.repository.NoteRepository

class DeleteNote (private val noteRepository: NoteRepository) {

    //invoke allows to call this class as a function
    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}
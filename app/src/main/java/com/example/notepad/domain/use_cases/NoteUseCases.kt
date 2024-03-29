package com.example.notepad.domain.use_cases

//the viewModel accesses the use cases through this class
data class NoteUseCases (
    val getNotes: GetNotes,
    val deleteNotes: DeleteNotes,
    val addNote: AddNote,
    val getNote: GetNote
)
package com.example.notepad.presentation.home.state

import com.example.notepad.domain.model.Note
import com.example.notepad.domain.util.NoteOrder
import com.example.notepad.domain.util.OrderType

data class HomeState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending), //default order
    val isOrderDialogVisible: Boolean = false,
    val isDropdownMenuOpen: Boolean = false
)
package com.example.notepad.presentation.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.model.Note
import com.example.notepad.presentation.NotepadViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")//evita el error de padding
@Composable
fun HomeScreen(
    onClickAddNote: () -> Unit = {},
    onNoteClick: (String) -> Unit = {},
    notepadViewModel: NotepadViewModel
) {
    val state = notepadViewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = { HomeFloatingActionButton( onClickAdd = onClickAddNote ) },
        scaffoldState = scaffoldState
    ) {
        //EmptyNoteList()
        if (state.notes.isEmpty()) {
            EmptyNoteList()
        } else {
            NoteList(notes = notes, onNoteClick = onNoteClick)
        }

    }

}

@Composable
fun AppBar(
    updateShowMenu: () -> Unit,
    isMenuOpen: Boolean,
    updateShowDialog: () -> Unit
) {
    val context = LocalContext.current
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "navigation icon",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

        },
        title = {
            Text("Home Screen")
        },
        actions = {
            //Box {
            IconButton(onClick = updateShowMenu) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "settings icon",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

            }
            //}
            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = updateShowMenu,
                modifier = Modifier.padding(5.dp)
            ) {
                DropdownMenuItem(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Toast",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text(text = "Toast")
                }
                DropdownMenuItem(onClick = updateShowDialog) {
                    Text(text = "Sort by")
                }
            }
        }
    )
}


@Composable
fun HomeFloatingActionButton(onClickAdd: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = onClickAdd
    ) {
        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add Note")
    }
}

@Composable
fun EmptyNoteList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "Tap + to write a new note!",
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
fun NoteList(notes: State<List<Note>?>, onNoteClick: (String) -> Unit = {}) {
        LazyColumn {
            notes.value?.size?.let {
                items(it) {
                    Note(note = notes.value!![it], onNoteClick = onNoteClick)

                }
            }
        }
    }

@Composable
fun Note(note: Note, onNoteClick: (String) -> Unit) {
    Surface(modifier = Modifier.clickable { onNoteClick(note.id.toString()) }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = note.noteTitle, style = MaterialTheme.typography.subtitle2 )
            Text(text = note.noteContent, style = MaterialTheme.typography.body2)
            Divider()
        }
    }

}
/*
@Preview
@Composable
fun NoteListPreview() {
    NotepadTheme() {
        NoteList()
    }

}

 */


/*
@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    NotepadTheme {
        HomeScreen()
    }
}
 */


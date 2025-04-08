package dev.alejo.noticas.ui.notes

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alejo.noticas.R

@Composable
fun NotesScreen(superInnerPadding: PaddingValues = PaddingValues()) {
    Scaffold(
        modifier = Modifier.padding(superInnerPadding).fillMaxSize(),
        topBar = { NotesAppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = null)
            }
        }
    ) { innerPadding ->
        val condition = false
        if (condition) {
            NotesContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            EmptyNotesContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
fun EmptyNotesContent(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Image(
            painter = painterResource(R.drawable.empty_notes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        )
        Text(text = "No hay notas")
    }
}

@Composable
fun NotesContent(modifier: Modifier) {
    Column(modifier = modifier) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(TopAppBarDefaults.MediumAppBarCollapsedHeight)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        SmallFloatingActionButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        }
        SmallFloatingActionButton(
            modifier = Modifier.padding(start = 16.dp),
            onClick = { }) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = null)
        }
    }
}

// Night mode
@Preview
@Composable
fun NotesAppBarPreview() {
    NotesScreen()
}
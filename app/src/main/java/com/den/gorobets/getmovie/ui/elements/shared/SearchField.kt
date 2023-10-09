package com.den.gorobets.getmovie.ui.elements.shared

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.den.gorobets.getmovie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(modifier: Modifier = Modifier, searching: (String) -> Unit) {

    var text by rememberSaveable { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchString = stringResource(id = R.string.search_string)
    val searchItems = remember { mutableStateListOf<String>() }

    Box(
        modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
            .zIndex(1f)
    ) {

        SearchBar(
            modifier =
            Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            query = text,
            onQueryChange = {
                text = it
                searching(text.trim().lowercase())
            },
            active = active,
            onActiveChange = { active = it },
            onSearch = {
                searchItems.add(text)
                active = false
            },
            placeholder = { Text(text = searchString) },
            tonalElevation = 12.dp,
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) text = ""
                            else active = false
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_button)
                    )
                }
            }
        ) {
            searchItems.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .animateContentSize(spring())
                        .clickable { text = it }
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = stringResource(R.string.icon_history)
                    )
                    Text(text = it)
                }
            }
        }
    }
}

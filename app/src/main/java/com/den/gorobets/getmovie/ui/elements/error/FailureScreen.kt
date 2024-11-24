package com.den.gorobets.getmovie.ui.elements.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.den.gorobets.getmovie.R

@Composable
fun FailureScreen(
    modifier: Modifier = Modifier,
    retryCallback: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.broken),
            contentDescription = stringResource(R.string.broken_icon)
        )
        Text(text = stringResource(R.string.can_t_load_data))
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { retryCallback.invoke() }) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.refresh_icon)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = stringResource(R.string.retry))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
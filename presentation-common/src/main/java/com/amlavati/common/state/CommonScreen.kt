package com.amlavati.common.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T : Any> CommonScreen(
    uiState: UiState<T>,
    onLoading: @Composable (Boolean) -> Unit,
    onSuccess: @Composable (T) -> Unit,
) {
    // val loading = remember { mutableStateOf(false) }
    when (uiState) {
        is UiState.Loading -> {
            onLoading(true)
        }

        is UiState.Error -> {
            Error(errorMessage = uiState.message)
        }

        is UiState.Success -> {
            //  onLoading(false)
            onSuccess(uiState.data)
        }
    }
}


@Composable
fun Error(errorMessage: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = errorMessage)

        Snackbar {
            Text(text = errorMessage)
        }
    }
}

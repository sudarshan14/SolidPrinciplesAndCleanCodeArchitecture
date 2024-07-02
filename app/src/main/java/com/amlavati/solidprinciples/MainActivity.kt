package com.amlavati.solidprinciples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.amlavati.solidprinciples.domain.usecase.GetTopicsUseCase
import com.amlavati.solidprinciples.presentation.ListOfTopics
import com.amlavati.solidprinciples.ui.theme.SolidPrinciplesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolidPrinciplesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val ds = GetTopicsUseCase()
                    ListOfTopics(modifier = Modifier.padding(innerPadding),
                        api = ds)
                }
            }
        }
    }
}




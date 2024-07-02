package com.amlavati.solidprinciples.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amlavati.solidprinciples.domain.usecase.GetTopicsUseCase

@Composable
fun ListOfTopics(modifier: Modifier = Modifier, api: GetTopicsUseCase) {

    LaunchedEffect(key1 = Unit) {
        api.invoke()
    }
    //  ds.invoke()
    val data = api.options.collectAsState().value

    Column(
        verticalArrangement = Arrangement.Top
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            strokeWidth = 2.dp,
        )
//        Button(onClick = {
////            api.invoke()
//        }) {
//            Text(text = "Get Data")
//        }
        LazyColumn(
            modifier = Modifier
        ) {
            itemsIndexed(data) { index, option ->
                Log.d("testing", "data.size ${data.size} ${data[index].topic}")
                Text(
                    fontSize = 30.sp,
                    color = Color.Red,
                    text = option.topic,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = data[index].fullForm,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = data[index].description,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}

@Preview
@Composable
private fun List() {
    ListOfTopics(api = GetTopicsUseCase())
}
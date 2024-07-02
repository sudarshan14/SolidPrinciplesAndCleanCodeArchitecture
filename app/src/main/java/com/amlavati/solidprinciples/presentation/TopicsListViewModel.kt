package com.amlavati.solidprinciples.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amlavati.solidprinciples.data.TopicWithDescription
import com.amlavati.solidprinciples.domain.usecase.GetTopicsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TopicsListViewModel :ViewModel() {
    val ds = GetTopicsUseCase()
    private val _options = MutableStateFlow<List<TopicWithDescription>>(emptyList())
    val options = _options


    fun getList() {
        viewModelScope.launch {
          //  _options.value = GetTopicsUseCase()
        }
    }
}
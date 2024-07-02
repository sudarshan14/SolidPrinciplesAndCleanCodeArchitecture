package com.amlavati.solidprinciples.domain.usecase

import com.amlavati.solidprinciples.data.TopicWithDescription
import com.amlavati.solidprinciples.data.getTopicsListWIthDescription
import kotlinx.coroutines.flow.MutableStateFlow

class GetTopicsUseCase {
    // val ds = GetTopicsUseCase()
    private val _options = MutableStateFlow<List<TopicWithDescription>>(emptyList())
    val options = _options
    operator fun invoke() {
        _options.value = getTopicsListWIthDescription()
    }
}
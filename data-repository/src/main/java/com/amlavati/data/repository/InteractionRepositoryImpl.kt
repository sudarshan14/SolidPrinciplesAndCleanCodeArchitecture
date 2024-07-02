package com.amlavati.data.repository

import com.amlavati.data.data_source.local.LocalInteractionDataSource
import com.amlavati.domain.entity.Interaction
import com.amlavati.domain.repository.InteractionRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InteractionRepositoryImpl @Inject constructor(
    private val localInteractionDataSource: LocalInteractionDataSource,
) : InteractionRepository {
    override fun getInteraction(): Flow<Interaction> =
        localInteractionDataSource.getInteraction()


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun saveInteraction(interaction: Interaction): Flow<Interaction> = flow {
        localInteractionDataSource.saveInteraction(interaction)
        this.emit(Unit)
    }.flatMapLatest {
        getInteraction()
    }
}


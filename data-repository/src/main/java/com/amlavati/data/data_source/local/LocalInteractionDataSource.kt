package com.amlavati.data.data_source.local

import com.amlavati.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow

/**
 * Local interaction data source
 *
 * @constructor Create empty Local interaction data source
 */
interface LocalInteractionDataSource {
    /**
     * Get interaction
     *
     * @return
     */
    fun getInteraction(): Flow<Interaction>

    /**
     * Save interaction
     *
     * @param interaction
     */
    suspend fun saveInteraction(interaction: Interaction)
}
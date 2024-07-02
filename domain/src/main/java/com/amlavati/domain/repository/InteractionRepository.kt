package com.amlavati.domain.repository

import com.amlavati.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow

/**
 * Interaction repository
 *
 * @constructor Create empty Interaction repository
 */
interface InteractionRepository {

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
     * @return
     */
    fun saveInteraction(interaction: Interaction):Flow<Interaction>

}
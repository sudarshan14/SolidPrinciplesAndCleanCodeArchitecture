package com.amlavati.data.repository

import com.amlavati.data.data_source.local.LocalInteractionDataSource
import com.amlavati.domain.entity.Interaction
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class InteractionRepositoryImplTest {

    private val localInteractionDataSource = mockk<LocalInteractionDataSource>()
    private val interactionRepository = InteractionRepositoryImpl(localInteractionDataSource)


    @Test
    fun `getInteraction fetches from local`() = runTest {

        val interaction = Interaction(10)
        coEvery {
            localInteractionDataSource.getInteraction()
        } returns flowOf(interaction)

        val actual = interactionRepository.getInteraction().first()
        assertEquals(interaction, actual)
    }


    @Test
    fun `saveInteraction saves to local`() = runTest {
        val interaction = Interaction(10)

        coEvery {
            localInteractionDataSource.saveInteraction(interaction)
        } returns Unit
        coEvery {
            localInteractionDataSource.getInteraction()
        } returns flowOf(interaction)

        val actual = interactionRepository.saveInteraction(interaction).first()

        assertEquals(interaction, actual)

    }

}
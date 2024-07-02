package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Interaction
import com.amlavati.domain.repository.InteractionRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class UpdateInteractionUseCaseTest {

    private val interactionRepository = mockk<InteractionRepository>()
    private val useCase = UpdateInteractionUseCase(mockk(), interactionRepository)


    @Test
    fun `Save Interaction Returns Unit`() = runTest {

        val interaction = Interaction(10)
        coEvery {
            interactionRepository.saveInteraction(interaction)
        }returns flowOf(interaction)

        val result = useCase.process(UpdateInteractionUseCase.Request(interaction)).first()

      val expectedResult = UpdateInteractionUseCase.Response

        assertEquals(expectedResult, result)
    }
}
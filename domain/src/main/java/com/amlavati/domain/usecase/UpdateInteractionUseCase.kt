package com.amlavati.domain.usecase

import com.amlavati.domain.entity.Interaction
import com.amlavati.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Update interaction use case
 *
 * @property interactionRepository
 * @constructor
 *
 * @param configuration
 */
class UpdateInteractionUseCase @Inject constructor(
    configuration: Configuration,
    private val interactionRepository: InteractionRepository
) : UseCase<UpdateInteractionUseCase.Request, UpdateInteractionUseCase.Response>(configuration) {


    /**
     * Request
     *
     * @property interaction
     * @constructor Create empty Request
     */
    data class Request(val interaction: Interaction) : UseCase.Request
    object Response : UseCase.Response

    override fun process(request: Request): Flow<Response> =
        interactionRepository.saveInteraction(request.interaction)
            .map {
                Response
            }

}
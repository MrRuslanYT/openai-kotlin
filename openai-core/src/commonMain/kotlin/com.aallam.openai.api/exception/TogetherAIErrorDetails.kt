package com.aallam.openai.api.exception

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an error response from the TogetherAI API.
 *
 * @param message information about the error that occurred.
 */
@Serializable
public data class TogetherAIError(
    @SerialName("error") public val message: String?,
)
package com.aallam.openai.api.exception

public class TogetherAIException(
    message: String? = null,
    throwable: Throwable? = null
) : RuntimeException(message, throwable)
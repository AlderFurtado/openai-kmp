package com.github.alder.furtado.openaikmp.domain.repository

import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiResponse

internal interface OpenAiRepository {
    suspend fun sendContent(openAiInput: OpenAiInput, token: String): OpenAiResponse
}
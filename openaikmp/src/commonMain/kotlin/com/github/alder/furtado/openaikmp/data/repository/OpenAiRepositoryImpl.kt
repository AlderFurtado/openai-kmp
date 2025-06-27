package com.github.alder.furtado.openaikmp.data.repository

import com.github.alder.furtado.openaikmp.data.entity.OpenAiResponseData
import com.github.alder.furtado.openaikmp.data.mapper.OpenAiInputToOpenAiRequestData
import com.github.alder.furtado.openaikmp.data.mapper.OpenAiResponseDataToOpenAiResponse
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiResponse
import com.github.alder.furtado.openaikmp.domain.repository.OpenAiRepository
import com.github.alder.furtado.openaikmp.infra.Http
import kotlinx.serialization.json.Json

internal class OpenAiRepositoryImpl(private val http: Http) : OpenAiRepository {
    override suspend fun sendContent(openAiInput: OpenAiInput, token: String): OpenAiResponse{
        val headers = mutableMapOf(
            "Content-Type" to "application/json",
            "Authorization" to "Bearer $token"
        )
        http.addHeaders(headers)

        val response = http.post("https://api.openai.com/v1/responses",
            OpenAiInputToOpenAiRequestData.mapper(openAiInput))
        val openAIResponseData = Json.decodeFromString<OpenAiResponseData>(response)
        return OpenAiResponseDataToOpenAiResponse.mapper(openAIResponseData)
    }
}
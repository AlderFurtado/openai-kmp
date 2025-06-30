package com.github.alder.furtado.openaikmp.data.repository

import com.github.alder.furtado.openaikmp.data.entity.OpenAiRequestData
import com.github.alder.furtado.openaikmp.data.entity.OpenAiResponseData
import com.github.alder.furtado.openaikmp.data.mapper.OpenAiInputToOpenAiRequestDataMapper
import com.github.alder.furtado.openaikmp.data.mapper.OpenAiResponseDataToOpenAiResponseMapper
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiResponse
import com.github.alder.furtado.openaikmp.domain.repository.OpenAiRepository
import com.github.alder.furtado.openaikmp.infra.Http
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

internal class OpenAiRepositoryImpl(private val http: Http) : OpenAiRepository {
    override suspend fun sendContent(openAiInput: OpenAiInput, token: String): OpenAiResponse{
        val headers = mutableMapOf(
            "Content-Type" to "application/json",
            "Authorization" to "Bearer $token"
        )
        http.addHeaders(headers)

        val response = http.post<OpenAiRequestData,HttpResponse>("https://api.openai.com/v1/responses",
            OpenAiInputToOpenAiRequestDataMapper.mapper(openAiInput))
        val openAIResponseData = Json.decodeFromString<OpenAiResponseData>(response.bodyAsText())
        return OpenAiResponseDataToOpenAiResponseMapper.mapper(openAIResponseData)
    }
}
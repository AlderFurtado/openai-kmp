package com.github.alder.furtado.openaikmp.data.repository

import com.github.alder.furtado.openaikmp.data.entity.OpenAiRequestData
import com.github.alder.furtado.openaikmp.data.entity.OpenAiResponseData
import com.github.alder.furtado.openaikmp.data.mapper.OpenAiInputToOpenAiRequestDataMapper
import com.github.alder.furtado.openaikmp.data.mapper.OpenAiResponseDataToOpenAiResponseMapper
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorResourceNotFound
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorUnauthorizedToken
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiResponse
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorInternalServer
import com.github.alder.furtado.openaikmp.domain.repository.OpenAiRepository
import com.github.alder.furtado.openaikmp.infra.Http
import com.github.alder.furtado.openaikmp.infra.HttpResponseLocal
import kotlinx.serialization.json.Json

internal class OpenAiRepositoryImpl(private val http: Http) : OpenAiRepository {

    override suspend fun sendContent(openAiInput: OpenAiInput, token: String): OpenAiResponse{
        val headers = mutableMapOf(
            "Content-Type" to "application/json",
            "Authorization" to "Bearer $token"
        )
        http.addHeaders(headers)

        val response = http.post<OpenAiRequestData,HttpResponseLocal>(
            url = "https://api.openai.com/v1/responses",
            body = OpenAiInputToOpenAiRequestDataMapper.mapper(openAiInput)
        )

        if(response.statusCode.value == 401) throw ErrorUnauthorizedToken()

        if(response.statusCode.value == 404) throw ErrorResourceNotFound()

        if(response.statusCode.value == 500) throw ErrorInternalServer()

        val openAIResponseData  = Json.decodeFromString<OpenAiResponseData>(response.content.toString())
        return OpenAiResponseDataToOpenAiResponseMapper.mapper(openAIResponseData)
    }
}
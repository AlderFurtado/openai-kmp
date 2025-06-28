package com.github.alder.furtado.openaikmp.domain.entity

sealed class OpenAiResponse(val content: String?,val error: String?){
    class OpenAiResponseSucceed(private val response:  String) : OpenAiResponse(content = response, error = null)
    class OpenAiResponseError(private val response: String) : OpenAiResponse(content = null, error = response)
}
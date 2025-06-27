package com.github.alder.furtado.openaikmp.domain.entity

sealed class OpenAiResponse(val content: String?, error: String?){
    class OpenAiResponseSucceed(private val response:  String) : OpenAiResponse(content = response, error = null)
    class OpenAiResponseError(private val error:  String) : OpenAiResponse(content = null, error = error)
}
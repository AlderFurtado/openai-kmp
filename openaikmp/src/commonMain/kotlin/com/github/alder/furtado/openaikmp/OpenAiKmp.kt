package com.github.alder.furtado.openaikmp

import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput

class OpenAiKmp {
    suspend fun sendContent(openAiInput: OpenAiInput, token: String) = Factory.getSendContentUseCase().execute(openAiInput,token)
}
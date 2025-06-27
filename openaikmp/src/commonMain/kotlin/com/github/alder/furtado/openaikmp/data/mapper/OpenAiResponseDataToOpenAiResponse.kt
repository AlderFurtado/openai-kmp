package com.github.alder.furtado.openaikmp.data.mapper

import com.github.alder.furtado.openaikmp.data.entity.OpenAiResponseData
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiResponse

internal object OpenAiResponseDataToOpenAiResponse: Mapper<OpenAiResponseData,OpenAiResponse> {
    override fun mapper(input: OpenAiResponseData): OpenAiResponse {
        return OpenAiResponse.OpenAiResponseSucceed(input.output[0].content[0].text)
    }
}
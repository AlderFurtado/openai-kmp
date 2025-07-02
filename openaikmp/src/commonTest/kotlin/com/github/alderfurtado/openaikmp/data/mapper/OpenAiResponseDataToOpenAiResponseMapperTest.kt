package com.github.alderfurtado.openaikmp.data.mapper

import com.github.alder.furtado.openaikmp.data.mapper.OpenAiResponseDataToOpenAiResponseMapper
import com.github.alderfurtado.openaikmp.util.stub.OpenAiResponseDataStub
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenAiResponseDataToOpenAiResponseMapperTest {

    @Test
    fun `given OpenAiResponseData when mapper to OpenAiResponse then should OpenAiResponse`(){
        val openAiResponseDataStub = OpenAiResponseDataStub.stub()
        val openAiResponse = OpenAiResponseDataToOpenAiResponseMapper.mapper(openAiResponseDataStub)

        assertEquals(openAiResponseDataStub.output[0].content[0].text, openAiResponse.content)
    }
}
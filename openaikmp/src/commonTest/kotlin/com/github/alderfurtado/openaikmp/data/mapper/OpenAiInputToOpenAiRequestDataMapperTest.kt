package com.github.alderfurtado.openaikmp.data.mapper

import com.github.alder.furtado.openaikmp.data.mapper.OpenAiInputToOpenAiRequestDataMapper
import com.github.alderfurtado.openaikmp.util.stub.OpenAiInputStub
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenAiInputToOpenAiRequestDataMapperTest {

    @Test
    fun `given OpenAiInput when mapper to OpenAiRequestData then should OpenAiRequestData created`(){
        val openAiInputStub = OpenAiInputStub.stub()
        val openAiRequest = OpenAiInputToOpenAiRequestDataMapper.mapper(openAiInputStub)
        assertEquals(openAiRequest.input[0].content, openAiInputStub.content)
        assertEquals(openAiRequest.input[1].content, openAiInputStub.instruction)
        assertEquals(openAiRequest.model, openAiInputStub.openAiModel.code)
    }
}
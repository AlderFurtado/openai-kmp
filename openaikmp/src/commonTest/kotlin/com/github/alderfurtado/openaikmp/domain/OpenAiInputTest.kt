package com.github.alderfurtado.openaikmp.domain

import com.github.alder.furtado.openaikmp.domain.entity.ErrorContentLessThanTwoCharacters
import com.github.alder.furtado.openaikmp.domain.entity.ErrorInstructionLessThanTwoCharacters
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiModel
import kotlin.test.Asserter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OpenAiInputTest {

    @Test
    fun `given content with one character when OpenAiInput should throw ErrorContentLessThanTwoCharacters`(){
        val error = assertFailsWith<ErrorContentLessThanTwoCharacters> {
            OpenAiInput(content = "a", instruction = "test")
        }
        assertEquals(error.message, ErrorContentLessThanTwoCharacters().message)
    }

    @Test
    fun `given content with one character when OpenAiInput should throw ErrorInstructionLessThanTwoCharacters`(){
        val error = assertFailsWith<ErrorInstructionLessThanTwoCharacters> {
            OpenAiInput(content = "test", instruction = "a")
        }
        assertEquals(error.message, ErrorInstructionLessThanTwoCharacters().message)
    }

    @Test
    fun `given content valid and instruction valid when OpenAiInput should return OpenAiInput with GPT_4O model`(){
        val openAiInput = OpenAiInput(content = "test", instruction = "test")
        assertEquals(openAiInput.openAiModel, OpenAiModel.GPT_4O)
    }

    @Test
    fun `given content valid and instruction valid when OpenAiInput should return same properties`(){
        val content = "test"
        val instruction = "test"
        val openAiInput = OpenAiInput(content = content, instruction =  instruction)
        assertEquals(openAiInput.content, content)
        assertEquals(openAiInput.instruction, instruction)
    }
}
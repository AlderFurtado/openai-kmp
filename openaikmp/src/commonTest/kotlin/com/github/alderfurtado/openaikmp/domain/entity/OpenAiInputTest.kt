package com.github.alderfurtado.openaikmp.domain.entity

import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorContentLessThanTwoCharacters
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorInstructionLessThanTwoCharacters
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OpenAiInputTest {

    private val contentValid = "test"
    private val instructionValid = "test"
    private val contentInvalid = "a"
    private val instructionInvalid = "a"

    @Test
    fun `given content with one character when OpenAiInput should throw ErrorContentLessThanTwoCharacters`(){
        val error = assertFailsWith<ErrorContentLessThanTwoCharacters> {
            OpenAiInput(content = contentInvalid, instruction = instructionValid)
        }
        assertEquals(error.message, ErrorContentLessThanTwoCharacters().message)
    }

    @Test
    fun `given content with one character when OpenAiInput should throw ErrorInstructionLessThanTwoCharacters`(){
        val error = assertFailsWith<ErrorInstructionLessThanTwoCharacters> {
            OpenAiInput(content = contentValid, instruction = instructionInvalid)
        }
        assertEquals(error.message, ErrorInstructionLessThanTwoCharacters().message)
    }

    @Test
    fun `given content valid and instruction valid when OpenAiInput should return OpenAiInput with GPT_4O model`(){
        val openAiInput = OpenAiInput(content = contentValid, instruction = instructionValid)
        assertEquals(openAiInput.openAiModel, OpenAiModel.GPT_4O)
    }

    @Test
    fun `given content valid and instruction valid when OpenAiInput should return same properties`(){

        val openAiInput = OpenAiInput(content = contentValid, instruction =  instructionValid)
        assertEquals(openAiInput.content, contentValid)
        assertEquals(openAiInput.instruction, instructionValid)
    }
}
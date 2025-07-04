package com.github.alder.furtado.openaikmp.domain.entity

import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorContentLessThanTwoCharacters
import com.github.alder.furtado.openaikmp.domain.entity.error.ErrorInstructionLessThanTwoCharacters

class OpenAiInput(
    val openAiModel: OpenAiModel = OpenAiModel.GPT_4O,
    val content: String,
    val instruction: String? = null
){
    init {
        validContent()
        validInstruction()
    }

    private fun validContent(){
        if(content.length < 2) throw ErrorContentLessThanTwoCharacters()
    }

    private fun validInstruction(){
        instruction?.let {
            if(it.length < 2) throw ErrorInstructionLessThanTwoCharacters()
        }
    }

}
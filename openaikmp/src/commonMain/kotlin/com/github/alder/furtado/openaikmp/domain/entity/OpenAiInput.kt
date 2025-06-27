package com.github.alder.furtado.openaikmp.domain.entity

class OpenAiInput(
    val openAiModel: OpenAiModel = OpenAiModel.GPT_4O,
    val content: String,
    val instruction: String? = null
){

    private fun validContent(){
        if(content.length < 2) throw ErrorContentLessThanTwoCharacters()
    }

    private fun validInstruction(){
        instruction?.let {
            if(it.length < 2) throw ErrorInstructionLessThanTwoCharacters()
        }
    }

}
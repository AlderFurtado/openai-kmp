package com.github.alderfurtado.openaikmp.util.stub

import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiModel

object OpenAiInputStub {
    fun stub(): OpenAiInput {
        return OpenAiInput(
            openAiModel = OpenAiModel.GPT_4O,
            content = "Sample content for testing.",
            instruction = "Please respond briefly."
        )
    }
}
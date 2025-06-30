package com.github.alderfurtado.openaikmp.data.mapper.stub

import com.github.alder.furtado.openaikmp.data.entity.Content
import com.github.alder.furtado.openaikmp.data.entity.Format
import com.github.alder.furtado.openaikmp.data.entity.InputTokensDetails
import com.github.alder.furtado.openaikmp.data.entity.OpenAiResponseData
import com.github.alder.furtado.openaikmp.data.entity.Output
import com.github.alder.furtado.openaikmp.data.entity.OutputTokensDetails
import com.github.alder.furtado.openaikmp.data.entity.Reasoning
import com.github.alder.furtado.openaikmp.data.entity.TextWrapper
import com.github.alder.furtado.openaikmp.data.entity.Usage

object OpenAiResponseDataStub {
    internal fun stub(): OpenAiResponseData {
        return OpenAiResponseData(
            id = "",
            objectType = "",
            createdAt = 0,
            status = "",
            background = false,
            error = null,
            incompleteDetails = null,
            instructions = null,
            maxOutputTokens = 0,
            maxToolCalls = 0,
            model = "",
            output = listOf(
                Output(
                    id = "",
                    type = "",
                    status = "",
                    content = listOf(
                        Content(
                            type = "",
                            annotations = listOf(),
                            logprobs = listOf(),
                            text = ""
                        )
                    ),
                    role = ""
                )
            ),
            parallelToolCalls = false,
            previousResponseId = null,
            reasoning = Reasoning(
                effort = null,
                summary = null
            ),
            serviceTier = "",
            store = false,
            temperature = 0.0,
            text = TextWrapper(
                format = Format(type = "")
            ),
            toolChoice = "",
            tools = listOf(),
            topLogprobs = 0,
            topP = 0.0,
            truncation = "",
            usage = Usage(
                inputTokens = 0,
                inputTokensDetails = InputTokensDetails(
                    cachedTokens = 0
                ),
                outputTokens = 0,
                outputTokensDetails = OutputTokensDetails(
                    reasoningTokens = 0
                ),
                totalTokens = 0
            ),
            user = null,
            metadata = emptyMap()
        )
    }
}
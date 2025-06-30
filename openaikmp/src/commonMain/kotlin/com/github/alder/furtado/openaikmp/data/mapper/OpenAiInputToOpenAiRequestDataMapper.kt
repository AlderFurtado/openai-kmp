package com.github.alder.furtado.openaikmp.data.mapper

import com.github.alder.furtado.openaikmp.data.entity.OpenAiInputData
import com.github.alder.furtado.openaikmp.data.entity.OpenAiRequestData
import com.github.alder.furtado.openaikmp.data.entity.OpenAiRoleData
import com.github.alder.furtado.openaikmp.domain.entity.OpenAiInput

internal object OpenAiInputToOpenAiRequestDataMapper: Mapper<OpenAiInput,OpenAiRequestData> {
    override fun mapper(input: OpenAiInput): OpenAiRequestData {
        val listOpenAiInputData = mutableListOf<OpenAiInputData>()
        listOpenAiInputData.add(OpenAiInputData(role = OpenAiRoleData.USER.value, content = input.content))
        input.instruction?.let {
            listOpenAiInputData.add(OpenAiInputData(role = OpenAiRoleData.DEVELOPER.value, content = input.instruction))
        }
        return  OpenAiRequestData(model = input.openAiModel.code,listOpenAiInputData)
    }
}
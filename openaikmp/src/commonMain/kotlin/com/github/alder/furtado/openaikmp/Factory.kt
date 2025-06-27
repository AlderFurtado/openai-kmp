package com.github.alder.furtado.openaikmp

import com.github.alder.furtado.openaikmp.data.repository.OpenAiRepositoryImpl
import com.github.alder.furtado.openaikmp.domain.usecase.SendContentUseCase
import com.github.alder.furtado.openaikmp.infra.KtorAdapter

internal object Factory {
    private fun getHttp() = KtorAdapter()
    private fun getOpenAiRepository() = OpenAiRepositoryImpl(getHttp())
    fun getSendContentUseCase() = SendContentUseCase(getOpenAiRepository())
}
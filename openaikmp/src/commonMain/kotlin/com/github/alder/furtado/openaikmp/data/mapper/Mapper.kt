package com.github.alder.furtado.openaikmp.data.mapper

internal interface Mapper<I,O> {
    fun mapper(input:I):O
}
package com.github.alder.furtado.openaikmp.domain.entity

class ErrorInstructionLessThanTwoCharacters(override val message: String? = "Instruction needs has more one characters") : Throwable()
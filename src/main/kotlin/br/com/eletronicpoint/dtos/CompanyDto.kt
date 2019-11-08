package br.com.eletronicpoint.dtos

data class CompanyDto(
        val socialReason: String,
        val cnpj: String,
        val id: String? = null
)
package br.com.eletronicpoint.dtos

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.br.CNPJ
import org.hibernate.validator.constraints.br.CPF
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegisterPFDto(
        @get:NotBlank(message = "The name field is not allowed to be null.")
        @get:Length(min = 3, max = 200, message = "The name field must contain min 3 characters and max 200 characters")
        val name: String,

        @get:NotBlank(message = "The email field is not allowed to be null.")
        @get:Length(min = 8, message = "The name field must contain min 8")
        @get:Email
        val email: String,

        @get:NotBlank(message = "The pass field is not allowed to be null.")
        val pass: String,

        @get:NotBlank(message = "The cpf field is not allowed to be null.")
        @get:CPF(message = "CPF is invalid.")
        val cpf: String,

        @get:NotBlank(message = "The cnpj field is not allowed to be null.")
        @get:CNPJ(message = "CNPJ is invalid.")
        val cnpj: String,

        val companyId: String = "",

        val hourValue: String? = "",
        val hoursWorkedTheDay: String? = "",
        val hoursLunch: String? = "",
        val id: String? = null
)
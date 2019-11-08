package br.com.eletronicpoint.dtos

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class CollaboratorDto(
        @get:NotBlank(message = "The name field is not allowed to be null.")
        @get:Length(min = 3, max = 200, message = "The name field must contain min 3 characters and max 200 characters")
        val name: String,

        @get:NotBlank(message = "The email field is not allowed to be null.")
        @get:Length(min = 8, message = "The name field must contain min 8")
        @get:Email
        val email: String,
        
        val pass: String?,
        val hourValue: String?,
        val hoursWorkedTheDay: String?,
        val hoursLunch: String?,
        val id: String? = null
)
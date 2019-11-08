package br.com.eletronicpoint.dtos

import br.com.eletronicpoint.enums.TypePoint
import java.util.*
import javax.validation.constraints.NotBlank

data class PointDto(
        @get:NotBlank(message = "The dateMark field is not allowed to be null")
        val dateMark: String,
        @get:NotBlank(message = "The typePoint field is not allowed to be null")
        val typePoint: String,

        val collaboratorId: String = "",
        val description: String? = "",
        val location: String? = "",
        val id: String? = null
)
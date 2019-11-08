package br.com.eletronicpoint.documents

import br.com.eletronicpoint.enums.TypePoint
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Point(
        val dateMark: Date,
        val typePoint: TypePoint,
        val collaboratorId: String,
        val description: String? = "",
        val location: String? = "",
        @Id
        val id: String? = null
)
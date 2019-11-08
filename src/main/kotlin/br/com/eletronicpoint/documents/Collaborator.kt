package br.com.eletronicpoint.documents

import br.com.eletronicpoint.enums.Profile
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Collaborator(
        val name: String,
        val email: String,
        val pass: String,
        val cpf: String,
        val profile: Profile,
        val companyId: String,
        val hourValue: Double? = 0.0,
        val hoursWorkedTheDay: Float? = 0.0f,
        val hoursLunch: Float? = 0.0f,
        @Id
        val id: String? = null
)
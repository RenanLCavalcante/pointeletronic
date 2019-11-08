package br.com.eletronicpoint.services

import br.com.eletronicpoint.documents.Collaborator
import java.util.*

interface CollaboratorService {

    fun save(collaborator: Collaborator) : Collaborator

    fun findByCpf(cpf: String) : Collaborator?

    fun findByEmail(email: String) : Collaborator?

    fun findById(id: String) : Collaborator?
}
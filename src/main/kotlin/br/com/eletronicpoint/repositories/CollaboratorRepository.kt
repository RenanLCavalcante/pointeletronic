package br.com.eletronicpoint.repositories

import br.com.eletronicpoint.documents.Collaborator
import org.springframework.data.mongodb.repository.MongoRepository

interface CollaboratorRepository : MongoRepository<Collaborator, String> {

    fun findByEmail(email: String) : Collaborator

    fun findByCpf(cpf: String) : Collaborator

}
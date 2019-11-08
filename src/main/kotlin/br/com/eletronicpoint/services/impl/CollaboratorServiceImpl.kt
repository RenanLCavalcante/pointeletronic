package br.com.eletronicpoint.services.impl

import br.com.eletronicpoint.documents.Collaborator
import br.com.eletronicpoint.repositories.CollaboratorRepository
import br.com.eletronicpoint.services.CollaboratorService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CollaboratorServiceImpl(
        private val collaboratorRepository: CollaboratorRepository
) : CollaboratorService {

    override fun save(collaborator: Collaborator) = collaboratorRepository.save(collaborator)

    override fun findByCpf(cpf: String) = collaboratorRepository.findByCpf(cpf)

    override fun findByEmail(email: String) = collaboratorRepository.findByEmail(email)

    override fun findById(id: String) = collaboratorRepository.findByIdOrNull(id)
}
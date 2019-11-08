package br.com.eletronicpoint.repositories

import br.com.eletronicpoint.documents.Point
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean

interface PointRepository : MongoRepository<Point, String> {
    fun findByCollaboratorId(collaboratorId: String, pageable: Pageable) : Page<Point>
}
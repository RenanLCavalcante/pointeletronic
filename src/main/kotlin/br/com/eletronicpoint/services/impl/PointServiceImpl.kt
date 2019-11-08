package br.com.eletronicpoint.services.impl

import br.com.eletronicpoint.documents.Point
import br.com.eletronicpoint.repositories.PointRepository
import br.com.eletronicpoint.services.PointService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PointServiceImpl(
        private val pointRepository: PointRepository
) : PointService {

    override fun save(point: Point): Point = pointRepository.save(point)

    override fun remove(id: String) = pointRepository.deleteById(id)

    override fun findByCollaboratorId(
            collaboratorId: String,
            pageRequest: PageRequest
    ) = pointRepository.findByCollaboratorId(collaboratorId, pageRequest)

    override fun findById(id: String) = pointRepository.findByIdOrNull(id)
}
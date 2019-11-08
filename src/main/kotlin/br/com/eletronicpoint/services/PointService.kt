package br.com.eletronicpoint.services

import br.com.eletronicpoint.documents.Point
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface PointService {

    fun findByCollaboratorId(collaboratorId: String, pageRequest: PageRequest) : Page<Point>

    fun findById(id: String) : Point?

    fun save(point: Point) : Point

    fun remove(id: String)
}
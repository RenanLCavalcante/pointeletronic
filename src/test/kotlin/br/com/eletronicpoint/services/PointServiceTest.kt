package br.com.eletronicpoint.services

import br.com.eletronicpoint.documents.Point
import br.com.eletronicpoint.enums.TypePoint
import br.com.eletronicpoint.repositories.PointRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest
class PointServiceTest {

    @MockBean
    private lateinit var pointRepository: PointRepository

    @Autowired
    private lateinit var pointService: PointService

    @BeforeEach
    @Throws(Exception::class)
    fun setUp(){
        BDDMockito.given<Page<Point>>(
                pointRepository.findByCollaboratorId("1", PageRequest.of(0, 10))
        ).willReturn(PageImpl(ArrayList<Point>()))

        BDDMockito.given(
                pointRepository.save(Mockito.any(Point::class.java))
        ).willReturn(buildPoint())

        BDDMockito.given(
            pointRepository.findByCollaboratorId("1", PageRequest.of(0, 10))
        ).willReturn(PageImpl(ArrayList<Point>()))

        BDDMockito.given(
                pointRepository.findById("1")
        ).willReturn(Optional.of(buildPoint()))
    }

    @Test
    fun `find point by collaborator id`() {
        val point = pointService.findByCollaboratorId("1", PageRequest.of(0, 10))
        Assertions.assertNotNull(point)
    }

    @Test
    fun `point should not null when saving`() {
        val point = pointService.save(buildPoint())
        Assertions.assertNotNull(point)
    }

    @Test
    fun `list of point should not null`() {
        val points = pointService.findByCollaboratorId("1", PageRequest.of(0,10))
        Assertions.assertNotNull(points)
    }

    @Test
    fun `point should not null when findById`() {
        val point = pointService.findById("1")
        Assertions.assertNotNull(point)
    }

    fun buildPoint(): Point = Point(
            Date(),
            TypePoint.START_WORK,
            "1",
            "Any description",
            "São Paulo",
            "1"
    )
}
package br.com.eletronicpoint.controllers

import br.com.eletronicpoint.documents.Point
import br.com.eletronicpoint.dtos.PointDto
import br.com.eletronicpoint.enums.TypePoint
import br.com.eletronicpoint.response.Response
import br.com.eletronicpoint.services.CollaboratorService
import br.com.eletronicpoint.services.CompanyService
import br.com.eletronicpoint.services.PointService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import javax.validation.Valid

@RestController
@RequestMapping("/v1/points")
class PointController(
        private val companyService: CompanyService,
        private val collaboratorService: CollaboratorService,
        private val pointService: PointService
) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Value("\${pagination.quantity_per_page}")
    private val quantityPerPage: Int = 5

    @PostMapping
    fun add(
            @Valid
            @RequestBody
            pointDto: PointDto,
            result: BindingResult
    ): ResponseEntity<Response<PointDto>> {
        val response = Response<PointDto>()
        validateCollaborator(pointDto, result)

        if (result.hasErrors()) {
            result.allErrors.map { response.errors.add(it.defaultMessage!!) }
            return ResponseEntity.badRequest().body(response)
        }

        val point = convertPointDtoToPoint(pointDto, result)
        response.data = convertPointToPointDto(pointService.save(point))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Response<PointDto>> {
        val response = Response<PointDto>()
        val point = pointService.findById(id)

        if (point == null) {
            response.errors.add("Point not found. ID: $id")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = convertPointToPointDto(point)
        return ResponseEntity.ok(response)
    }

    private fun convertPointToPointDto(point: Point) = PointDto(
            dateFormat.format(point.dateMark),
            point.typePoint.toString(),
            point.collaboratorId,
            point.location,
            point.description,
            point.id
    )

    private fun validateCollaborator(pointDto: PointDto, result: BindingResult) {
        if (pointDto.collaboratorId.isNullOrBlank()) {
            result.addError(ObjectError("collaborator", "Uninformed Collaborator"))
            return
        }

        collaboratorService.findById(pointDto.collaboratorId)
                ?: result.addError(ObjectError("collaborator",
                        "Collaborator ID not found."))
    }

    private fun convertPointDtoToPoint(pointDto: PointDto, result: BindingResult): Point {
        pointDto.id ?: result.addError(ObjectError("point",
                "Point ID is null."))
        pointService.findById(pointDto.id!!) ?: result.addError(ObjectError("point",
                "Point not find."))

        return Point(
                dateFormat.parse(pointDto.dateMark),
                TypePoint.valueOf(pointDto.typePoint),
                pointDto.collaboratorId,
                pointDto.description,
                pointDto.location,
                pointDto.id
        )
    }
}
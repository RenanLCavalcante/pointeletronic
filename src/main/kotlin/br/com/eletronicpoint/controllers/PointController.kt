package br.com.eletronicpoint.controllers

import br.com.eletronicpoint.converters.ConvertPointDtoToPoint
import br.com.eletronicpoint.converters.ConvertPointToPointDto
import br.com.eletronicpoint.documents.Point
import br.com.eletronicpoint.dtos.PointDto
import br.com.eletronicpoint.enums.TypePoint
import br.com.eletronicpoint.response.Response
import br.com.eletronicpoint.services.CollaboratorService
import br.com.eletronicpoint.services.CompanyService
import br.com.eletronicpoint.services.PointService
import br.com.eletronicpoint.validators.ValidatorCollaborator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import javax.validation.Valid

@RestController
@RequestMapping("/v1/points")
class PointController(
        private val pointService: PointService,
        private val convertPointToPointDto: ConvertPointToPointDto,
        private val convertPointDtoToPoint: ConvertPointDtoToPoint,
        private val validatorCollaborator: ValidatorCollaborator
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun add(@Valid @RequestBody pointDto: PointDto, result: BindingResult): ResponseEntity<Response<PointDto>> {
        val response = Response<PointDto>()
        validatorCollaborator.validate(pointDto, result)

        if (result.hasErrors()) {
            logger.info("result has errors")
            result.allErrors.map { response.errors.add(it.defaultMessage!!) }
            return ResponseEntity.badRequest().body(response)
        }

        val point = convertPointDtoToPoint.convert(pointDto, result)
        response.data = convertPointToPointDto.convert(pointService.save(point))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Response<PointDto>> {
        val response = Response<PointDto>()

        val point = pointService.findById(id) ?:
            response.errors.add("Point not found. ID: $id").let {
                return ResponseEntity.badRequest().body(response)
            }

        response.data = convertPointToPointDto.convert(point)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/collaborator/{id}")
    fun findAllPointsByCollaboratorId(
            @PathVariable("id") id: String,
            @RequestParam(value = "pagination", defaultValue = "0") pagination: Int,
            @RequestParam(value = "quantity", defaultValue = "20") quantityPerPage: Int,
            @RequestParam(value = "direction", defaultValue = "DESC") direction: String,
            @RequestParam(value = "ordination", defaultValue = "id") ordination: String
    ): ResponseEntity<Response<Page<PointDto>>> {
        val response = Response<Page<PointDto>>()
        val pageResponse = PageRequest.of(
                pagination, quantityPerPage, Sort.Direction.valueOf(direction), ordination
        )
        val points = pointService.findByCollaboratorId(id, pageResponse)

        val pointsDto = points.map { convertPointToPointDto.convert(it) }

        response.data = pointsDto

        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun update(
            @PathVariable("id") id: String,
            @RequestBody pointDto: PointDto, result: BindingResult
    ): ResponseEntity<Response<PointDto>> {
        val response = Response<PointDto>()
        validatorCollaborator.validate(pointDto, result)
        val pointDtoIntern = PointDto(
                pointDto.dateMark,
                pointDto.typePoint,
                pointDto.collaboratorId,
                pointDto.description,
                pointDto.location,
                id
        )

        val point = convertPointDtoToPoint.convert(pointDtoIntern, result)

        if (result.hasErrors()) {
            result.allErrors.map { response.errors.add(it.defaultMessage!!) }
            return ResponseEntity.badRequest().body(response)
        }

        response.data = convertPointToPointDto.convert(pointService.save(point))
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Response<String>> {
        val response = Response<String>()

        logger.info("action=FindingPoint, id $id")
        pointService.findById(id) ?: response.errors.add("Id $id not found.").let {
            return ResponseEntity.badRequest().body(response)
        }

        try {
            logger.info("action=deletingPoint, id $id")
            pointService.remove(id)
        } catch (ex: Exception){
            logger.info(ex.message)
            response.errors.add("error while trying remove point with id $id")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = "$id removed with off success"
        return ResponseEntity.ok(response)
    }
}
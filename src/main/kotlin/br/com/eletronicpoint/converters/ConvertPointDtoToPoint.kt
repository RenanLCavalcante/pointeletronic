package br.com.eletronicpoint.converters

import br.com.eletronicpoint.documents.Point
import br.com.eletronicpoint.dtos.PointDto
import br.com.eletronicpoint.enums.TypePoint
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import java.text.SimpleDateFormat

@Component
class ConvertPointDtoToPoint {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun convert(pointDto: PointDto, result: BindingResult): Point {
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
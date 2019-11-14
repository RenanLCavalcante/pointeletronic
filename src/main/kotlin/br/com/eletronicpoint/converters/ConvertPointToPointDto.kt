package br.com.eletronicpoint.converters

import br.com.eletronicpoint.documents.Point
import br.com.eletronicpoint.dtos.PointDto
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class ConvertPointToPointDto {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun convert(point: Point) = PointDto(
            dateFormat.format(point.dateMark),
            point.typePoint.toString(),
            point.collaboratorId,
            point.description,
            point.location,
            point.id
    )
}
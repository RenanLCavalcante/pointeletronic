package br.com.eletronicpoint.validators

import br.com.eletronicpoint.dtos.PointDto
import br.com.eletronicpoint.services.CollaboratorService
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError

@Component
class ValidatorCollaborator(
        private val collaboratorService: CollaboratorService
) {

    fun validate(pointDto: PointDto, result: BindingResult) {
        if (pointDto.collaboratorId.isNullOrBlank()) {
            result.addError(ObjectError("collaborator", "Uninformed Collaborator"))
            return
        }

        collaboratorService.findById(pointDto.collaboratorId)
                ?: result.addError(ObjectError("collaborator",
                        "Collaborator ID not found."))
    }
}
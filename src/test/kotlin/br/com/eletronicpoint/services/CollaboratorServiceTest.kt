package br.com.eletronicpoint.services

import br.com.eletronicpoint.documents.Collaborator
import br.com.eletronicpoint.enums.Profile
import br.com.eletronicpoint.repositories.CollaboratorRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@SpringBootTest
class CollaboratorServiceTest {

    @MockBean
    private lateinit var collaboratorRepository: CollaboratorRepository

    @Autowired
    private lateinit var collaboratorService: CollaboratorService

    @BeforeEach
    @Throws(Exception::class)
    fun setUp(){

        BDDMockito.given(
                collaboratorRepository.save(Mockito.any(Collaborator::class.java))
        ).willReturn(buildCollaborator())

        BDDMockito.given(
                collaboratorRepository.findByCpf("12345678901")
        ).willReturn(buildCollaborator())

        BDDMockito.given(
                collaboratorRepository.findByEmail("renanluquete@gmail.com")
        ).willReturn(buildCollaborator())

//        BDDMockito.given(
//                collaboratorRepository.findById("1")
//        ).willReturn(Optional<Collaborator>)
    }

    @Test
    fun `should return collaborator after save on database`(){
        val collaborator = collaboratorService.save(buildCollaborator())
        Assertions.assertNotNull(collaborator)
    }

    @Test
    fun `should return collaborator after search by cpf`(){
        val collaborator = collaboratorService.findByCpf("12345678901")
        Assertions.assertNotNull(collaborator)
    }

    @Test
    fun `should return collaborator after search by email`(){
        val collaborator = collaboratorService.findByEmail("renanluquete@gmail.com")
        Assertions.assertNotNull(collaborator)
    }

    fun buildCollaborator() = Collaborator(
            "Renan",
            "renanluquete@gmail.com",
            " 123",
            "12345678901",
            Profile.ROLE_ADMIN,
            "1",
            100.0,
            100.0f,
            8f,
            "1"
    )
}
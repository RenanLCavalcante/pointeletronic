package br.com.eletronicpoint.services

import br.com.eletronicpoint.documents.Company
import br.com.eletronicpoint.repositories.CompanyRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private lateinit var companyService: CompanyService

    @MockBean
    private lateinit var companyRepository: CompanyRepository

    @BeforeEach
    @Throws(Exception::class)
    fun setUp(){
        BDDMockito.given(companyRepository.findByCnpj("0123456789")).willReturn(buildCompany())
        BDDMockito.given(companyRepository.save(buildCompany())).willReturn(buildCompany())
    }

    @Test
    fun `should return company`(){
        Assertions.assertNotNull(companyService.findByCnpj("0123456789"))
    }

    @Test
    fun `must save company and return`(){
        Assertions.assertNotNull(companyService.save(buildCompany()))
    }

    fun buildCompany() = Company("Dell", "0123456789", "1845")
}
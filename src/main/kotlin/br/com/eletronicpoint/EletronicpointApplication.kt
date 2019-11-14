package br.com.eletronicpoint

import br.com.eletronicpoint.documents.Collaborator
import br.com.eletronicpoint.documents.Company
import br.com.eletronicpoint.enums.Profile
import br.com.eletronicpoint.repositories.CollaboratorRepository
import br.com.eletronicpoint.repositories.CompanyRepository
import br.com.eletronicpoint.repositories.PointRepository
import br.com.eletronicpoint.utils.PassUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class EletronicpointApplication

fun main(args: Array<String>) {
	runApplication<EletronicpointApplication>(*args)
}

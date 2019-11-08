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
class EletronicpointApplication(
		val collaboratorRepository: CollaboratorRepository,
		val companyRepository: CompanyRepository,
		val pointRepository: PointRepository
): CommandLineRunner {

	override fun run(vararg args: String?) {
		companyRepository.deleteAll()
		collaboratorRepository.deleteAll()
		pointRepository.deleteAll()

		val company = companyRepository.save(buildCompany("Dell", "11654460865"))
		val user = collaboratorRepository.save(buildCollaborator(
				name = "Renan",
				email = "renan@gmail.com",
				pass = "123",
				profile = Profile.ROLE_USER,
				companyId = company.id!!,
				cpf = "42058457744"
		))
		val admin = collaboratorRepository.save(buildCollaborator(
				name = "Admin",
				email = "admin@gmail.com",
				pass = "123",
				profile = Profile.ROLE_ADMIN,
				companyId = company.id!!,
				cpf = "1111111"
		))

		println("Company -> ${company.id}")
		println("User -> ${user.id}")
		println("Admin -> ${admin.id}")
	}

}

private fun buildCollaborator(
		name: String,
		email: String,
		pass: String,
		cpf: String,
		profile: Profile,
		companyId: String
) = Collaborator(
		name, email, pass, cpf, profile, companyId
)

private fun buildCompany(socialReason: String, cnpj: String) = Company(socialReason, cnpj)

fun main(args: Array<String>) {
	runApplication<EletronicpointApplication>(*args)
}

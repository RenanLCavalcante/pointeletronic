package br.com.eletronicpoint.services.impl

import br.com.eletronicpoint.documents.Company
import br.com.eletronicpoint.repositories.CompanyRepository
import br.com.eletronicpoint.services.CompanyService
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
        private val companyRepository: CompanyRepository
) : CompanyService {

    override fun findByCnpj(cnpj: String) = companyRepository.findByCnpj(cnpj)

    override fun save(company: Company) = companyRepository.save(company)
}
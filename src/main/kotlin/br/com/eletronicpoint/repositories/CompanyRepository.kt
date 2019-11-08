package br.com.eletronicpoint.repositories

import br.com.eletronicpoint.documents.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<Company, String> {
    fun findByCnpj(cnpj: String) : Company?
}
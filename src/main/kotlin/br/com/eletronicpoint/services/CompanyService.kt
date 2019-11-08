package br.com.eletronicpoint.services

import br.com.eletronicpoint.documents.Company

interface CompanyService {

    fun findByCnpj(cnpj: String) : Company?

    fun save(company: Company) : Company
}
package br.com.eletronicpoint.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class PassUtils {
    companion object{
        fun generateBCrypt(pass: String) = BCryptPasswordEncoder().encode(pass)
    }
}
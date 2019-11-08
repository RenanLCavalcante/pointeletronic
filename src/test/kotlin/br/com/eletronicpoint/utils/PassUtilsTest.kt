package br.com.eletronicpoint.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


class PassUtilsTest {

    @Test
    fun `should return pass encoding`(){
        Assertions.assertTrue(
                BCryptPasswordEncoder().matches("12345", PassUtils.generateBCrypt("12345")))
    }
}
package com.backdoor.walcartandroidtest

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UnitTestExTest {

    @Test
    fun validInput() {
        val number = 100
        val text = "text Text"
        val result = UnitTestEx.dateTestValidation(number, text)
        assertThat(result).isEqualTo(true)
    }

}
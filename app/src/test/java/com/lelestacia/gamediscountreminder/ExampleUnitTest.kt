package com.lelestacia.gamediscountreminder

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        Assertions.assertEquals(4, 2 + 2)
    }

    @Test
    fun fooTest() {
        Assertions.assertTrue(true)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, true, false])
    fun testFoo(param: Boolean) {
        Assertions.assertTrue(param)
    }
}
package org.github.jnorthrup.kursiv


import org.junit.Test

import org.junit.Assert.*
import std.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.text.Charsets.UTF_8

class catTest {
    @Test
    fun bytes() {
        val k = cat("Hello".toByteArray().asSequence(), "World".toByteArray().asSequence())
        val string = bstr(k)
        assertNotEquals("HelloWorld1", string)
        assertEquals("HelloWorld", string)
    }

    @Test
    fun chars() {
        val k = cat("Hello".asSequence(), "World".asSequence())
        val string = cstr(k)
        assertNotEquals("HelloWorld1", string)
        assertEquals("HelloWorld", string)
    }
    @Test
    fun str() {
        run<catTest, Unit> {
            val k = cat("Hello".toByteArray().asSequence(), "World".toByteArray().asSequence())
            val string = bstr(k)
            assertNotEquals("HelloWorld1", string)
            assertEquals("HelloWorld", string)
        }
        run<catTest, Unit> {
            val k = cat("Hello".toCharArray().asSequence(), "World" .toCharArray().asSequence())
            val string = cstr(k)
            assertNotEquals("HelloWorld1", string)
            assertEquals("HelloWorld", string)
        }
    }

}

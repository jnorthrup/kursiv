package org.github.jnorthrup.kursiv


import org.junit.Test

import org.junit.Assert.*
import std.cat

class catTest {
    @Test
    fun x() {
        val k =  cat ("Hello".toByteArray().asSequence() , "World".toByteArray().asSequence() )
        val string =  String(k.toList().toByteArray(), Charsets.UTF_8)
        assertNotEquals  ("HelloWorld1", string)
        assertEquals("HelloWorld",  string)
    }
}
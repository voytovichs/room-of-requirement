package com.ror.io

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class SniffingInputStreamTest {
    @Test
    fun data_from_host_stream_is_not_lost_test() {
        val expected = data.toByteArray()
        val input = ByteArrayInputStream(expected)

        val sniffingInput = SniffingInputStream(input)
        val actual = ByteArray(expected.size)

        val halfSize = actual.size / 2
        sniffingInput.read(actual, 0, halfSize)
        sniffingInput.read(actual, halfSize, actual.size - halfSize)

        assertArrayEquals(expected, actual)
    }

    @Test
    fun sniffed_data_is_equal_to_actual_data_test() {
        val expected = data.toByteArray()
        val input = ByteArrayInputStream(expected)

        val sniffingInput = SniffingInputStream(input)
        val actual = ByteArray(expected.size)

        val halfSize = actual.size / 2
        sniffingInput.read(actual, 0, halfSize)
        sniffingInput.read(actual, halfSize, actual.size - halfSize)

        assertArrayEquals(expected, sniffingInput.content)
    }

    private val data = buildString {
        appendln("The data this is")
        appendln("This is a second line")
        appendln("I will not lose my sniffing streams no more")
        appendln("One less distraction obscures the skyline")
    }
}
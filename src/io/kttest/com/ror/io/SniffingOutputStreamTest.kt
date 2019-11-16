package com.ror.io

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class SniffingOutputStreamTest {
    @Test
    fun data_reaches_host_stream_test() {
        val bytes = data.toByteArray()
        val half = bytes.size / 2

        val original = ByteArrayOutputStream()
        val sniffingOutput = SniffingOutputStream(original)

        sniffingOutput.write(bytes, 0, half)
        sniffingOutput.write(bytes, half, bytes.size - half)

        assertArrayEquals(bytes, original.toByteArray())
    }

    @Test
    fun sniffed_data_is_equal_to_actual_data_test() {
        val bytes = data.toByteArray()
        val half = bytes.size / 2

        val original = OutputStreamMock()
        val sniffingOutput = SniffingOutputStream(original)

        sniffingOutput.write(bytes, 0, half)
        sniffingOutput.write(bytes, half, bytes.size - half)

        assertArrayEquals(bytes, sniffingOutput.content)
    }

    private val data = buildString {
        appendln("The data this is")
        appendln("This is a second line")
        appendln("I will not lose my sniffing streams no more")
        appendln("One less distraction obscures the skyline")
    }

    private class OutputStreamMock : OutputStream() {
        override fun write(b: Int) = Unit
    }
}
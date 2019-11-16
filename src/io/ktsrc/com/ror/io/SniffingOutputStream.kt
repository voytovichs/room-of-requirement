package com.ror.io

import java.io.ByteArrayOutputStream
import java.io.OutputStream

class SniffingOutputStream(private val host: OutputStream) : OutputStream() {

    private val sniffer = ByteArrayOutputStream()

    val content: ByteArray
        get() = sniffer.toByteArray()

    override fun write(buf: ByteArray, off: Int, len: Int) {
        sniffer.write(buf, off, len)
        host.write(buf, off, len)
    }

    override fun write(b: Int) {
        write(byteArrayOf(b.toByte()), 0, 1)
    }

    override fun flush() {
        sniffer.flush()
        host.flush()
    }

    override fun close() {
        sniffer.close()
        host.close()
    }
}
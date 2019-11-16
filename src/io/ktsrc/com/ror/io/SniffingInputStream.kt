package com.ror.io

import java.io.ByteArrayOutputStream
import java.io.InputStream

class SniffingInputStream(private val host: InputStream) : InputStream() {

    private val sniffer = ByteArrayOutputStream()

    val content: ByteArray
        get() = sniffer.toByteArray()

    override fun read(): Int {
        val read = host.read()
        if (read >= 0) {
            sniffer.write(read)
            sniffer.flush()
        }
        return read
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        val read = host.read(b, off, len)
        if (read >= 0) {
            sniffer.write(b, off, read)
            sniffer.flush()
        }
        return read
    }

    override fun available(): Int = host.available()

    override fun close() {
        host.close()
        sniffer.flush()
    }
}
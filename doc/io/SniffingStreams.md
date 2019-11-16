# Sniffing Streams

## Overview
`InputStream` and `OutputStream` are abstractions to read bytes from and to write bytes to correspondingly.
For debugging or other purposes one might want to take a look at content that has been read from `InputStream` so far, or content that has been written to `OutputStream`.
Sniffing Streams are extended wrappers for native JVM streams aiming for re-reading the content that put through host original streams.

## Related classes
- `SniffingInputStream` .kt
- `SniffingOutputStream` .kt
- `SniffingInputStreamTest` .kt
- `SniffingOutputStreamTest` .kt  
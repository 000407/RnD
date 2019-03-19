package com.kaze2.rnd;

import java.nio.ByteBuffer;

public class Long {
    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(java.lang.Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }
}

package com.kaze2.rnd.hashes;

import com.kaze2.rnd.Long;

import java.util.Arrays;

public class MD5 {
    private static final byte[] A = {0, 1, 2, 3, 4, 5, 6, 7};
    private static final byte[] B = {8, 9, 10, 11, 12, 13, 14, 15};
    private static final byte[] C = {15, 14, 13, 12, 11, 10, 9, 8};
    private static final byte[] D = {7, 6, 5, 4, 3, 2, 1, 0};

    public static void generate(String string){
        byte[] stringBytes = string.getBytes();

        long stringLength = stringBytes.length * 8;
        int padLength = (int) stringLength % 512;
        padLength = 448 - padLength;
        if(padLength < 0)
            padLength += 512;

        byte[] padBytes = new byte[padLength / 8];
        padBytes[0] = -128;

        byte[] paddedStringBytes = join(stringBytes, padBytes);
        paddedStringBytes = join(paddedStringBytes, Long.longToBytes(stringLength));

        byte[] X = new byte[16];

        for(int i = 0; i < paddedStringBytes.length / 16; i++){
            for(int j = 0; j < X.length; j++)
                X[j] = paddedStringBytes[i * 16 + j];

            System.out.println(Arrays.toString(X));
        }
    }

    private static int F(int x, int y, int z){
        return (x & y) | (~x & z);
    }

    private static int G(int x, int y, int z){
        return (x & y) | (y & ~z);
    }

    private static int H(int x, int y, int z){
        return x ^ y ^ z;
    }

    private static int I(int x, int y, int z){
        return y ^ (x | ~z);
    }

    private static byte[] join(byte[] a, byte[] b){
        byte[] res = new byte[a.length + b.length];

        byte[] longer = a;

        if(a.length < b.length){
            longer = b;
        }

        for(int i = 0; i < longer.length; i++){
            if(i < a.length){
                res[i] = a[i];
            }
            if(i < b.length){
                res[a.length + i] = b[i];
            }
        }

        return res;
    }
}

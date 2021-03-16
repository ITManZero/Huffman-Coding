package ite.man.huffmanCoding.util;

public class Math {

    public static final int byteToInteger(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF) << 0);
    }

    public static final String byteToIBinary(byte[] bytes, int n) {
        StringBuffer codeBuffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            StringBuffer binary = new StringBuffer(Integer.toBinaryString(bytes[i]));
            for (int j = 0; j < Constant.BITS_IN_BYTE - binary.length(); j++)
                codeBuffer.append('0');
            codeBuffer.append(binary);
        }
        return codeBuffer.toString();
    }

    public static final String lastByteToBinary(byte byte_, int lastByteSize) {
        StringBuffer codeBuffer = new StringBuffer();
        StringBuffer binary = new StringBuffer(Integer.toBinaryString(byte_));
        for (int j = 0; j < lastByteSize - binary.length(); j++)
            codeBuffer.append('0');
        codeBuffer.append(binary);
        return codeBuffer.toString();
    }


}

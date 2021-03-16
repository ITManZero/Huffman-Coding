package ite.man.huffmanCoding.encoder;

import ite.man.huffmanCoding.util.Constant;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class StringEncoder {


    private StringBuffer codeBuffer = new StringBuffer();

    private byte extra;

    private byte filled;


    public void add(String code) {
        codeBuffer.append(code);
    }

    public void flush(BufferedOutputStream bos) throws IOException {
        bos.write(Byte.parseByte(codeBuffer.toString(), 2));
        extra = (byte) codeBuffer.length();
        filled = (byte) (Constant.BITS_IN_BYTE - codeBuffer.length());
        codeBuffer = new StringBuffer();
    }

    public boolean write(BufferedOutputStream bos) {
        try {
            bos.write(Byte.parseByte(codeBuffer.substring(0, Constant.BITS_IN_BYTE), 2));
            codeBuffer = new StringBuffer(codeBuffer.substring(Constant.BITS_IN_BYTE));
            return true;
        } catch (IOException e) {
            System.out.println("The file not written successfully");
            return false;
        }

    }

    public boolean isWritable() {
        return codeBuffer.length() >= Constant.BITS_IN_BYTE;
    }

    public boolean isEmpty() {
        return codeBuffer.length() == 0;
    }

    public byte getExtra() {
        return extra;
    }

    public byte getFilled() {
        return filled;
    }
}

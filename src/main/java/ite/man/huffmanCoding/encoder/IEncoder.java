package ite.man.huffmanCoding.encoder;

import java.io.File;
import java.io.IOException;

public interface IEncoder {

    File encode(File file, String compressedName) throws IOException;

    File encode(String string, String compressedName) throws IOException;

}

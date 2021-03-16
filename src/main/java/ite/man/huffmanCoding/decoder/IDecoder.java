package ite.man.huffmanCoding.decoder;

import java.io.File;
import java.io.IOException;

public interface IDecoder {

    File decode(File file, String outputFileName) throws IOException;

    File decode(String string, String outputFileName) throws IOException;
}

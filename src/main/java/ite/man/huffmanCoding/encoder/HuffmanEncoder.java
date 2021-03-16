package ite.man.huffmanCoding.encoder;


import ite.man.huffmanCoding.debugger.DebugOff;
import ite.man.huffmanCoding.debugger.DebugOn;
import ite.man.huffmanCoding.debugger.IDebugger;
import ite.man.huffmanCoding.huffmanTree.CharacterNode;
import ite.man.huffmanCoding.huffmanTree.HuffmanTree;
import ite.man.huffmanCoding.util.CharacterMap;
import ite.man.huffmanCoding.util.Constant;
import ite.man.huffmanCoding.util.Units;

import java.io.*;

public class HuffmanEncoder implements IEncoder {

    private final CharacterMap characterMap;

    private final StringEncoder stringEncoder;

    private final HuffmanTree huffmanTree;

    private final IDebugger debugger;


    public HuffmanEncoder(boolean debugOn) {
        this.debugger = debugOn ? new DebugOn() : new DebugOff();
        huffmanTree = new HuffmanTree(debugger.treeDebugger());
        stringEncoder = new StringEncoder();
        characterMap = new CharacterMap();
    }

    public HuffmanEncoder() {
        this(false);
    }

    @Override
    public File encode(File file, String compressedName) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        File compressedFile = new File(compressedName);
        FileOutputStream fos = new FileOutputStream(compressedFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        char[] subtext = new char[Constant.BUFFER_SIZE];
        int n;
        while ((n = br.read(subtext)) != -1)
            fillCharacterMap(subtext, n);
        br.close();
        fr.close();
        huffmanTree.build(characterMap.getCharacterNodes());
        debugger.printCharactersCode(characterMap.getCharacterNodes());
        huffmanTree.writeTree(bos);
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        while ((n = br.read(subtext)) != -1)
            encodeMessage(subtext, bos, n);
        if (!stringEncoder.isEmpty())
            stringEncoder.flush(bos);
        debugger.printLastByteSize(stringEncoder.getExtra());
        bos.write(stringEncoder.getExtra());
        System.out.println("Encoding file done successfully.");
        br.close();
        fr.close();
        bos.close();
        fos.close();
        return compressedFile;
    }

    @Override
    public File encode(String string, String compressedName) throws IOException {
        File compressedFile = new File(compressedName);
        FileOutputStream fos = new FileOutputStream(compressedFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        fillCharacterMap(string.toCharArray(), string.length());
        huffmanTree.build(characterMap.getCharacterNodes());
        debugger.printCharactersCode(characterMap.getCharacterNodes());
        huffmanTree.writeTree(bos);
        encodeMessage(string.toCharArray(), bos, string.length());
        if (!stringEncoder.isEmpty())
            stringEncoder.flush(bos);
        debugger.printLastByteSize(stringEncoder.getExtra());
        bos.write(stringEncoder.getExtra());
        System.out.println("Encoding file done successfully.");
        bos.close();
        fos.close();

        return compressedFile;
    }


    public double messageSize(Units units) {
        double charCounter = 0;
        for (CharacterNode characterNode : characterMap.getCharacterNodes())
            charCounter += characterNode.getRepRate();
        return charCounter / units.getRatio();
    }

    public double treeSize(Units units) {
        return huffmanTree.size();
    }

    /**
     * encoded message 0000111 / 7 = 1B
     */
    public double encodedSize(Units units) {
        double encodedSize = 0;
        for (CharacterNode characterNode : characterMap.getCharacterNodes())
            encodedSize += characterNode.getCode().length() * characterNode.getRepRate();
        return (encodedSize + stringEncoder.getFilled()) / (Constant.BITS_IN_BYTE * units.getRatio());

    }

    public double compressionRatio() {
        double compressionRatio = 100 * (1 - ((encodedSize(Units.B) + treeSize(Units.B)) / messageSize(Units.B)));
        return compressionRatio > 0 ? compressionRatio : 0;
    }


    private void fillCharacterMap(char[] subMessage, int length) {
        for (int i = 0; i < length; i++) {
            if (characterMap.notContain(subMessage[i]))
                characterMap.initNode(subMessage[i]);
            characterMap.increaseCounter(subMessage[i]);
        }
    }

    private void encodeMessage(char[] subMessage, BufferedOutputStream bos, int n) {
        for (int i = 0; i < n; i++) {
            String code = characterMap.getNode(subMessage[i]).getCode();
            stringEncoder.add(code);
            while (stringEncoder.isWritable())
                stringEncoder.write(bos);
        }
    }
}
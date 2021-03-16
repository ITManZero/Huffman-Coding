package ite.man.huffmanCoding.decoder;


import ite.man.huffmanCoding.debugger.DebugOff;
import ite.man.huffmanCoding.debugger.DebugOn;
import ite.man.huffmanCoding.debugger.IDebugger;
import ite.man.huffmanCoding.huffmanTree.CharacterNode;
import ite.man.huffmanCoding.huffmanTree.HuffmanTree;
import ite.man.huffmanCoding.util.CharacterMap;
import ite.man.huffmanCoding.util.Constant;
import ite.man.huffmanCoding.util.Math;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder implements IDecoder {


    private HuffmanTree huffmanTree;

    private StringDecoder stringDecoder;

    private CharacterMap characterMap;

    private IDebugger debugger;


    public HuffmanDecoder(boolean debugOn) {
        this.debugger = debugOn ? new DebugOn() : new DebugOff();
        huffmanTree = new HuffmanTree(debugger.treeDebugger());
        stringDecoder = new StringDecoder();
        characterMap = new CharacterMap();
    }

    public HuffmanDecoder() {
        this(false);
    }

    @Override
    public File decode(File file, String outputFileName) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        List<CharacterNode> characterNodes = new ArrayList<>();
        huffmanTree.readTree(bis, characterNodes);
        for (CharacterNode characterNode : characterNodes)
            characterMap.initNode(characterNode.getCharacter(), characterNode);
        debugger.printCharactersCode(characterMap.getCharacterNodes());
        stringDecoder.setRoot(huffmanTree.getRoot());
        FileWriter fw = new FileWriter(outputFileName);
        BufferedWriter bw = new BufferedWriter(fw);
        byte[] bytes = new byte[Constant.BUFFER_SIZE];
        int n;
        while ((n = bis.read(bytes)) != -1)
            decodeMessage(bytes, bw, n);
        System.out.println("Decoding file done successfully.");
        bis.close();
        fis.close();
        bw.close();
        fw.close();
        return new File(outputFileName);
    }

    @Override
    public File decode(String string, String outputFileName) throws IOException {
        return null;
    }

    private void decodeMessage(byte[] bytes, BufferedWriter bw, int n) {
        if (n < bytes.length) {
            String code = Math.byteToIBinary(bytes, n - 2);
            stringDecoder.add(code);
            stringDecoder.add(Math.lastByteToBinary(bytes[n - 2], bytes[n - 1]));
            debugger.printLastByteSize(bytes[n - 1]);
        } else {
            String code = Math.byteToIBinary(bytes, n);
            stringDecoder.add(code);
        }
        while (stringDecoder.isWritable())
            stringDecoder.write(bw);
    }
}
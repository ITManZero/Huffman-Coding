package ite.man.huffmanCoding.decoder;



import ite.man.huffmanCoding.huffmanTree.TreeNode;
import ite.man.huffmanCoding.util.TreeUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class StringDecoder {

    private StringBuffer codeBuffer;

    private TreeNode root;

    public StringDecoder() {
        this.codeBuffer = new StringBuffer();
    }

    public StringDecoder(TreeNode root) {
        this.codeBuffer = new StringBuffer();
        this.root = root;
    }

    public void add(String code) {
        codeBuffer.append(code);
    }

    public boolean write(BufferedWriter bw) {
        List<Character> characters = TreeUtil.scanTree(root, codeBuffer);
        try {
            for (Character character : characters)
                bw.write(character);
            return true;
        } catch (IOException e) {
            System.out.println("The file not written successfully");
            return false;
        }
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public boolean isWritable() {
        return codeBuffer.length() > 0;
    }

    public boolean isEmpty() {
        return codeBuffer.length() == 0;
    }
}
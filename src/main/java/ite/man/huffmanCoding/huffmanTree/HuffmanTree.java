package ite.man.huffmanCoding.huffmanTree;

import ite.man.huffmanCoding.debugger.ITreeDebugger;
import ite.man.huffmanCoding.util.TreeUtil;
import ite.man.huffmanCoding.util.Math;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class HuffmanTree {

    private TreeNode root;

    private StringBuffer encodedTree;

    private int size;

    private ITreeDebugger debugger;

    public HuffmanTree(ITreeDebugger debugger) {
        this.debugger = debugger;
        this.root = null;
        this.size = -1;
        this.encodedTree = new StringBuffer();
    }

    public void build(List<CharacterNode> characterNodes) {
        this.root = TreeUtil.buildNodes(characterNodes);
        TreeUtil.generateCode(root, "");
        TreeUtil.encodeTree(root, encodedTree);
    }


    public boolean writeTree(BufferedOutputStream bos) {
        byte[] treeBytes = this.encodedTree.toString().getBytes();
        byte[] sizeBytes = ByteBuffer.allocate(Integer.BYTES).putInt(treeBytes.length).array();
        this.size = treeBytes.length;
        try {
            bos.write(sizeBytes);
            bos.write(treeBytes);
            debugger.printTreeDetails(size, encodedTree);
            System.out.println("Tree written successfully.\n");
            return true;
        } catch (IOException e) {
            System.out.println("The tree didn't written successfully.");
            e.printStackTrace();
            return false;
        }
    }


    public int readTree(BufferedInputStream bis, List<CharacterNode> characterNodes) {
        try {
            byte[] sizeBytes = new byte[Integer.BYTES];
            bis.read(sizeBytes);
            size = Math.byteToInteger(sizeBytes);
            byte[] treeBytes = new byte[size];
            bis.read(treeBytes);
            this.encodedTree = new StringBuffer(new String(treeBytes));
            this.root = TreeUtil.parseTree(this.encodedTree.toString(), new int[]{0}, "", characterNodes);
            debugger.printTreeDetails(size, encodedTree);
            System.out.println("Tree read successfully.");
            return size;
        } catch (IOException e) {
            System.out.println("The tree didn't written successfully.");
            e.printStackTrace();
            return -1;
        }
    }

//    public void printTree() {
//        traversalPrint(root, "");
//    }
//
//    private void traversalPrint(TreeNode root, String code) {
//        if (root == null) return;
//        if (root.getClass() == CharacterNode.class)
//            System.out.println(((CharacterNode) root).getCharacter() + "  " + code);
//        else {
//            traversalPrint(root.getLeft(), code + '0');
//            traversalPrint(root.getRight(), code + '1');
//        }
//    }
//
//    private void printWrittenTree() {
//        System.out.println("\nTree Details[\n" + "size: " + size + "B\n" + "tree: \"" + encodedTree + "\"]");
//    }
//
//    private void printReadTree() {
//        System.out.println("\nTree Details[\n" + "size: " + size + "B\n" + "tree: \"" + encodedTree + "\"]");
//    }
//

    public long size() {
        return size > 0 ? size + Integer.BYTES : -1;
    }

    public TreeNode getRoot() {
        return root;
    }

}

package ite.man.huffmanCoding.util;

import ite.man.huffmanCoding.huffmanTree.BuilderNode;
import ite.man.huffmanCoding.huffmanTree.CharacterNode;
import ite.man.huffmanCoding.huffmanTree.TreeNode;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public final class TreeUtil {

    public static TreeNode buildNodes(List<CharacterNode> characterNodes) {
        PriorityQueue<TreeNode> priorityQueue = new PriorityQueue<>((o1, o2) -> (int) (o1.getRepRate() - o2.getRepRate()));
        for (TreeNode treeNode : characterNodes)
            priorityQueue.add(treeNode);
        while (priorityQueue.size() > 1) {
            TreeNode left = priorityQueue.poll();
            TreeNode right = priorityQueue.poll();
            TreeNode parent = new BuilderNode(left, right);
            priorityQueue.add(parent);
        }
        return priorityQueue.peek();
    }

    public static void generateCode(TreeNode root, String code) {
        if (root.getClass() == CharacterNode.class) {
            ((CharacterNode) root).setCode(code);
            return;
        }
        generateCode(root.getLeft(), code + "0");
        generateCode(root.getRight(), code + "1");
    }

    public static void encodeTree(TreeNode root, StringBuffer tree) {
        if (root.getClass() == CharacterNode.class) {
            tree.append("1");
            tree.append(((CharacterNode) root).getCharacter());
        } else {
            tree.append("0");
            encodeTree(root.getLeft(), tree);
            encodeTree(root.getRight(), tree);
        }
    }

    public static TreeNode parseTree(String tree, int[] index, String code, List<CharacterNode> characterNodes) {
        if (tree.charAt(index[0]) == '1') {
            CharacterNode characterNode = new CharacterNode(tree.charAt(++index[0]), code);
            characterNodes.add(characterNode);
            return characterNode;
        } else {
            index[0]++;
            TreeNode leftChild = parseTree(tree, index, code + "0", characterNodes);
            index[0]++;
            TreeNode rightChild = parseTree(tree, index, code + "1", characterNodes);
            return new BuilderNode(leftChild, rightChild);
        }
    }

    static TreeNode parseTree(FileReader fr, String code) throws IOException {
        if (fr.read() == '1') {
            char[] character = new char[1];
            fr.read(character);
            return new CharacterNode(character[0], code);
        } else {
            TreeNode leftChild = parseTree(fr, code + "0");
            TreeNode rightChild = parseTree(fr, code + "1");
            return new BuilderNode(leftChild, rightChild);
        }
    }

    public static List<Character> scanTree(TreeNode root, StringBuffer code) {
        List<Character> characters = new ArrayList<>();
        int index = 0;
        int lastStart = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty() && code.length() > index) {
            TreeNode treeNode = stack.pop();
            TreeNode characterNode;
            if (code.charAt(index) == '0')
                characterNode = treeNode.getLeft();
            else
                characterNode = treeNode.getRight();
            if (characterNode.getClass() == CharacterNode.class) {
                characters.add(((CharacterNode) characterNode).getCharacter());
                stack.add(root);
                lastStart = index + 1;
            } else stack.add(characterNode);
            index++;
        }
        code.setLength(0);
        if (lastStart < code.length()) {
            String extra = code.substring(lastStart);
            code.append(extra);
        }
        return characters;
    }
}

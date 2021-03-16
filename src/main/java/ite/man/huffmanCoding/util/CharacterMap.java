package ite.man.huffmanCoding.util;



import ite.man.huffmanCoding.huffmanTree.CharacterNode;

import java.util.ArrayList;

public class CharacterMap {

    private final CharacterNode[] characterMap = new CharacterNode[150_000];

    private final ArrayList<CharacterNode> characterNodes = new ArrayList<>();

    public void initNode(char ascii) {
        characterMap[ascii] = new CharacterNode(ascii, 0);
        characterNodes.add(characterMap[ascii]);
    }

    public void initNode(char ascii, CharacterNode characterNode) {
        characterMap[ascii] = characterNode;
        characterNodes.add(characterMap[ascii]);
    }

    public void increaseCounter(char ascii) {
        if (characterMap[ascii] == null) throw new NullPointerException();
        characterMap[ascii].increaseRep();
    }

    public CharacterNode[] getCharacterMap() {
        return characterMap;
    }

    public CharacterNode getNode(char ascii) {
        return characterMap[ascii];
    }

    public boolean notContain(char ascii) {
        return characterMap[ascii] == null;
    }

    public ArrayList<CharacterNode> getCharacterNodes() {
        return characterNodes;
    }
}

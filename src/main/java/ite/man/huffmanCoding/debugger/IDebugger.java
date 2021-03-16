package ite.man.huffmanCoding.debugger;


import ite.man.huffmanCoding.huffmanTree.CharacterNode;

import java.util.List;

public interface IDebugger {

    void printLastByteSize(byte size);

    void printCharactersCode(List<CharacterNode> characterNodes);

    ITreeDebugger treeDebugger();
}

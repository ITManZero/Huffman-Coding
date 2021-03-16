package ite.man.huffmanCoding.debugger;


import ite.man.huffmanCoding.huffmanTree.CharacterNode;

import java.util.List;

public class DebugOff implements IDebugger {

    @Override
    public void printLastByteSize(byte size) {
        /**Do nothing*/
    }

    @Override
    public void printCharactersCode(List<CharacterNode> characterNodes) {
        /**Do nothing*/
    }

    @Override
    public ITreeDebugger treeDebugger() {
        return new TreeDebuggerOff();
    }
}

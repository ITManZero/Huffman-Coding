package ite.man.huffmanCoding.debugger;



import ite.man.huffmanCoding.huffmanTree.CharacterNode;

import java.util.List;

public class DebugOn implements IDebugger {


    @Override
    public void printLastByteSize(byte size) {
        System.out.print("Last byte size :[ ");
        printer(size, false);
        System.out.println(" ]");
    }

    @Override
    public void printCharactersCode(List<CharacterNode> characterNodes) {
        System.out.println("[Characters_Code]");
        for (CharacterNode characterNode : characterNodes)
            printer(characterNode.getCharacter() + "  " + characterNode.getCode(), true);
    }

    @Override
    public ITreeDebugger treeDebugger() {
        return new TreeDebuggerOn();
    }

    private void printer(Object o, boolean newLine) {
        if (newLine) System.out.println(o);
        else System.out.print(o);
    }
}

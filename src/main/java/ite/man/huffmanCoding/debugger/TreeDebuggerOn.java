package ite.man.huffmanCoding.debugger;

public class TreeDebuggerOn implements ITreeDebugger {

    @Override
    public void printTreeDetails(int size, StringBuffer encodedTree) {
        System.out.println("\nTree Details[\n" + "size: " + size + "B\n" + "tree: \"" + encodedTree + "\"]");
    }
}

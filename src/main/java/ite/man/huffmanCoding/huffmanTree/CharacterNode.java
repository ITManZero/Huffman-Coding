package ite.man.huffmanCoding.huffmanTree;

public class CharacterNode extends TreeNode {

    private final char character;
    private String code;

    public CharacterNode(char character, long repRate) {
        super(repRate);
        this.character = character;
    }

    public CharacterNode(char character, String code) {
        super(0);
        this.character = character;
        this.code = code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public TreeNode getLeft() {
        return null;
    }

    @Override
    public TreeNode getRight() {
        return null;
    }

}

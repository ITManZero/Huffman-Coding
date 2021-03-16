package ite.man.huffmanCoding.huffmanTree;

public abstract class TreeNode {

    private long repRate;

    public TreeNode(long repRate) {
        this.repRate = repRate;

    }

    public void increaseRep() {
        repRate++;
    }

    public long getRepRate() {
        return repRate;
    }

    public abstract TreeNode getLeft();

    public abstract TreeNode getRight();

    public abstract String getCode();
}

package ite.man.huffmanCoding.huffmanTree;

public class BuilderNode extends TreeNode {

    private TreeNode left;
    private TreeNode right;


    public BuilderNode(TreeNode left, TreeNode right) {
        super(left.getRepRate()+ right.getRepRate());
        this.left = left;
        this.right = right;
    }


    @Override
    public TreeNode getLeft() {
        return left;
    }

    @Override
    public TreeNode getRight() {
        return right;
    }

    @Override
    public String getCode() {
        return null;
    }
}

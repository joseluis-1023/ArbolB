import java.util.ArrayList;
import java.util.List;

public class BTreeNode {
    private final List<Integer> keys = new ArrayList<>();
    private final List<BTreeNode> children = new ArrayList<>();
    private final boolean leaf;

    public BTreeNode(boolean leaf) {
        this.leaf = leaf;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public List<BTreeNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return leaf;
    }
}

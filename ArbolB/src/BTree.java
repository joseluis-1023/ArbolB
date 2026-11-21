import java.util.ArrayList;
import java.util.List;

public class BTree {
    private final int t;
    private BTreeNode root;

    public BTree(int t) {
        if (t < 2) {
            throw new IllegalArgumentException("t debe ser >= 2");
        }
        this.t = t;
        this.root = new BTreeNode(true);
    }

    public int getT() {
        return t;
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(BTreeNode node, int key) {
        int i = 0;
        while (i < node.getKeys().size() && key > node.getKeys().get(i)) {
            i++;
        }
        if (i < node.getKeys().size() && key == node.getKeys().get(i)) {
            return true;
        }
        if (node.isLeaf()) {
            return false;
        }
        return search(node.getChildren().get(i), key);
    }

    public boolean insert(int key) {
        if (search(key)) {
            return false;
        }
        if (root.getKeys().size() == 2 * t - 1) {
            BTreeNode newRoot = new BTreeNode(false);
            newRoot.getChildren().add(root);
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, key);
        return true;
    }

    private void insertNonFull(BTreeNode node, int key) {
        int i = node.getKeys().size() - 1;
        while (i >= 0 && key < node.getKeys().get(i)) {
            i--;
        }
        if (node.isLeaf()) {
            node.getKeys().add(i + 1, key);
            return;
        }
        i++;
        if (node.getChildren().get(i).getKeys().size() == 2 * t - 1) {
            splitChild(node, i);
            if (key > node.getKeys().get(i)) {
                i++;
            }
        }
        insertNonFull(node.getChildren().get(i), key);
    }

    private void splitChild(BTreeNode parent, int index) {
        BTreeNode full = parent.getChildren().get(index);
        BTreeNode right = new BTreeNode(full.isLeaf());
        int median = full.getKeys().get(t - 1);

        right.getKeys().addAll(full.getKeys().subList(t, full.getKeys().size()));
        full.getKeys().subList(t - 1, full.getKeys().size()).clear();

        if (!full.isLeaf()) {
            right.getChildren().addAll(full.getChildren().subList(t, full.getChildren().size()));
            full.getChildren().subList(t, full.getChildren().size()).clear();
        }

        parent.getKeys().add(index, median);
        parent.getChildren().add(index + 1, right);
    }

    public int height() {
        int h = 1;
        BTreeNode node = root;
        while (!node.isLeaf()) {
            node = node.getChildren().get(0);
            h++;
        }
        return h;
    }

    public List<List<List<Integer>>> getLevels() {
        List<List<List<Integer>>> levels = new ArrayList<>();
        List<BTreeNode> current = new ArrayList<>();
        current.add(root);
        while (!current.isEmpty()) {
            List<List<Integer>> keysOfLevel = new ArrayList<>();
            List<BTreeNode> next = new ArrayList<>();
            for (BTreeNode node : current) {
                keysOfLevel.add(new ArrayList<>(node.getKeys()));
                next.addAll(node.getChildren());
            }
            levels.add(keysOfLevel);
            current = next;
        }
        return levels;
    }
}

public class Main {
    public static void main(String[] args) {
        BTree model = new BTree(2);
        BTreeView view = new BTreeView();
        BTreeController controller = new BTreeController(model, view);

        int[] demoKeys = {10, 20, 5, 6, 12, 30, 7, 17};
        controller.runDemo(demoKeys);
        controller.runMenu();
    }
}

public class BTreeController {
    private final BTree model;
    private final BTreeView view;

    public BTreeController(BTree model, BTreeView view) {
        this.model = model;
        this.view = view;
    }

    public void runDemo(int[] keys) {
        view.showMessage("Arbol B con t = " + model.getT()
                + " (maximo " + (2 * model.getT() - 1) + " claves por nodo)");
        for (int key : keys) {
            insertKey(key);
        }
    }

    public void runMenu() {
        boolean running = true;
        while (running) {
            view.showMenu();
            String option = view.readLine();
            switch (option) {
                case "1":
                    Integer toInsert = view.readInt("Clave: ");
                    if (toInsert == null) {
                        view.showMessage("Valor invalido");
                    } else {
                        insertKey(toInsert);
                    }
                    break;
                case "2":
                    Integer toSearch = view.readInt("Clave: ");
                    if (toSearch == null) {
                        view.showMessage("Valor invalido");
                    } else {
                        searchKey(toSearch);
                    }
                    break;
                case "3":
                    showTree();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    view.showMessage("Opcion invalida");
            }
        }
    }

    private void insertKey(int key) {
        if (model.insert(key)) {
            view.showMessage("Insertar " + key + " (altura " + model.height() + ")");
            view.showTree(model.getLevels());
        } else {
            view.showMessage("La clave " + key + " ya existe");
        }
    }

    private void searchKey(int key) {
        if (model.search(key)) {
            view.showMessage("La clave " + key + " SI esta en el arbol");
        } else {
            view.showMessage("La clave " + key + " NO esta en el arbol");
        }
    }

    private void showTree() {
        view.showMessage("Altura: " + model.height());
        view.showTree(model.getLevels());
    }
}

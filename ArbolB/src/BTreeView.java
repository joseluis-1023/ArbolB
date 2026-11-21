import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BTreeView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTree(List<List<List<Integer>>> levels) {
        for (int i = 0; i < levels.size(); i++) {
            String line = levels.get(i).stream()
                    .map(node -> "[" + node.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(" ")) + "]")
                    .collect(Collectors.joining("  "));
            System.out.println("  Nivel " + i + ": " + line);
        }
    }

    public void showMenu() {
        System.out.println();
        System.out.println("1. Insertar clave");
        System.out.println("2. Buscar clave");
        System.out.println("3. Mostrar arbol");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }

    public String readLine() {
        if (!scanner.hasNextLine()) {
            return "0";
        }
        return scanner.nextLine().trim();
    }

    public Integer readInt(String prompt) {
        System.out.print(prompt);
        String line = readLine();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

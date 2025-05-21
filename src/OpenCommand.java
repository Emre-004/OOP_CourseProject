import java.util.Scanner;
/**
 * Команда за отваряне на файл с продукти.
 * Зарежда съдържанието на склад от файл чрез .
 */

public class OpenCommand implements Command{
    private WarehouseService service;
    private FileManager fileManager;
    private Scanner scanner;

    public OpenCommand(WarehouseService service, FileManager fileManager, Scanner scanner) {
        this.service = service;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Въведи име на файл за отваряне: ");
        String filename = scanner.nextLine();
        service.setProducts(fileManager.loadFromFile(filename));
        service.setCurrentFileName(filename);
    }

}

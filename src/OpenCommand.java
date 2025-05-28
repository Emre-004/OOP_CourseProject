

import java.util.Scanner;

/**
 * Команда за отваряне на файл със списък от продукти.
 */
public class OpenCommand implements Command {
    private WarehouseService service;
    private FileManager fileManager;
    private Scanner scanner;

    /**
     * Създава инстанция на OpenCommand с достъп до склада, файловия мениджър и вход от потребителя.
     */
    public OpenCommand(WarehouseService service, FileManager fileManager, Scanner scanner) {
        this.service = service;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    /**
     * Изпълнява отварянето на файл.
     * Зарежда списъка с продукти от подадения от потребителя файл.
     */
    @Override
    public void execute(String[] args) {
        System.out.print("Въведи име на файл за отваряне: ");
        String filename = scanner.nextLine();
        service.setProducts(fileManager.loadFromFile(filename));
        service.setCurrentFileName(filename);
    }
}

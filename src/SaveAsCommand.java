
import java.util.Scanner;

/**
 * Команда за запис на продуктите в нов файл.
 */
public class SaveAsCommand implements Command {
    private WarehouseService service;
    private FileManager fileManager;
    private Scanner scanner;

    /**
     * Създава инстанция на SaveAsCommand с достъп до склада, файловия мениджър и вход от потребителя.
     */
    public SaveAsCommand(WarehouseService service, FileManager fileManager, Scanner scanner) {
        this.service = service;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    /**
     * Изпълнява командата за запис в нов файл.
     * Задава новото име на файла като текущо.
     */
    @Override
    public void execute() {
        System.out.print("Въведи име на файл за запис: ");
        String filename = scanner.nextLine();
        fileManager.saveToFile(service.getProducts(), filename);
        service.setCurrentFileName(filename);
    }
}

import java.util.Scanner;

public class SaveAsCommand implements Command{
    private WarehouseService service;
    private FileManager fileManager;
    private Scanner scanner;

    public SaveAsCommand(WarehouseService service, FileManager fileManager, Scanner scanner) {
        this.service = service;
        this.fileManager = fileManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Въведи име на файл за запис: ");
        String filename = scanner.nextLine();
        fileManager.saveToFile(service.getProducts(), filename);
        service.setCurrentFileName(filename);
    }
}

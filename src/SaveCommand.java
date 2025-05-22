
/**
 * Команда за запис на текущите данни в отворения файл.
 */
public class SaveCommand implements Command {
    private WarehouseService service;
    private FileManager fileManager;

    /**
     * Създава инстанция на SaveCommand с достъп до склада и файловия мениджър.
     */
    public SaveCommand(WarehouseService service, FileManager fileManager) {
        this.service = service;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява запис в текущия файл.
     * Ако няма отворен файл, изисква използване на командата saveas.
     */
    @Override
    public void execute() {
        if (service.getCurrentFileName() == null) {
            System.out.println("Няма отворен файл. Използвайте saveas.");
            return;
        }
        fileManager.saveToFile(service.getProducts(), service.getCurrentFileName());
    }
}

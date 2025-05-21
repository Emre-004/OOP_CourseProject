public class SaveCommand implements Command{
    private WarehouseService service;
    private FileManager fileManager;

    public SaveCommand(WarehouseService service, FileManager fileManager) {
        this.service = service;
        this.fileManager = fileManager;
    }

    @Override
    public void execute() {
        if (service.getCurrentFileName() == null) {
            System.out.println("Няма отворен файл. Използвайте saveas.");
            return;
        }
        fileManager.saveToFile(service.getProducts(), service.getCurrentFileName());
    }
}

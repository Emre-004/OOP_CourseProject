public class CloseCommand implements Command {
    private WarehouseService service;

    public CloseCommand(WarehouseService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        service.setProducts(new java.util.ArrayList<>());
        service.setCurrentFileName(null);
        System.out.println("Файлът е затворен. Данните са изчистени от паметта.");
    }
}

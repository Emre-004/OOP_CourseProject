/**
 * Команда за отпечатване на всички продукти в склада.
 */
public class PrintCommand implements Command{
    private WarehouseService service;

    public PrintCommand(WarehouseService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        service.printProducts();
    }
}

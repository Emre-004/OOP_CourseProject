
/**
 * Команда за извеждане на всички продукти в склада.
 */
public class PrintCommand implements Command {
    private WarehouseService service;

    /**
     * Създава инстанция на PrintCommand с достъп до склада.
     */
    public PrintCommand(WarehouseService service) {
        this.service = service;
    }

    /**
     * Изпълнява командата за извеждане на списъка с продукти.
     */
    @Override
    public void execute() {
        service.printProducts();
    }
}

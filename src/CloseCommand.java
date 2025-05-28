

/**
 * Команда за затваряне на текущо заредения файл.
 * Изчиства всички продукти от паметта.
 */
public class CloseCommand implements Command {
    private WarehouseService service;

    /**
     * Създава инстанция на CloseCommand с достъп до склада.
     */
    public CloseCommand(WarehouseService service) {
        this.service = service;
    }

    /**
     * Изпълнява затварянето на файла.
     * Изтрива всички продукти от паметта и нулира името на текущия файл.
     */
    @Override
    public void execute(String[] args) {
        service.setProducts(new java.util.ArrayList<>());
        service.setCurrentFileName(null);
        System.out.println("Файлът е затворен. Данните са изчистени от паметта.");
    }
}

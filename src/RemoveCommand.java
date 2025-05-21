import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 * Команда за премахване на продукт от склада.
 * Позволява на потребителя да въведе параметри за премахване.
 */

public class RemoveCommand implements Command{
    private WarehouseService service;
    private Scanner scanner;

    public RemoveCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Име на продукт за изваждане: ");
        String name = scanner.nextLine();

        System.out.print("Количество за изваждане: ");
        double quantityToRemove = Double.parseDouble(scanner.nextLine());

        double totalRemoved = 0;
        List<Product> toRemoveLogs = new ArrayList<>();

        Iterator<Product> iterator = service.getProducts().iterator();
        while (iterator.hasNext() && totalRemoved < quantityToRemove) {
            Product p = iterator.next();
            if (p.getName().equalsIgnoreCase(name)) {
                double available = p.getQuantity();

                if (available <= quantityToRemove - totalRemoved) {
                    totalRemoved += available;
                    System.out.println("Премахнато: " + available + " от " + p);
                    iterator.remove();
                    toRemoveLogs.add(p);
                } else {
                    double remaining = available - (quantityToRemove - totalRemoved);
                    iterator.remove();

                    Product updated = new Product(p.name, p.expiryDate, p.arrivalDate, p.manufacturer,
                            p.unit, remaining, p.location, p.comment);
                    service.getProducts().add(updated);

                    Product removedPart = new Product(p.name, p.expiryDate, p.arrivalDate, p.manufacturer,
                            p.unit, quantityToRemove - totalRemoved, p.location, p.comment);
                    toRemoveLogs.add(removedPart);

                    totalRemoved = quantityToRemove;
                    System.out.println("Премахнато: " + (quantityToRemove - totalRemoved + available) + " от " + p);
                }
            }
        }

        for (Product p : toRemoveLogs) {
            service.logChange("remove", p);
        }

        if (totalRemoved == 0) {
            System.out.println("Няма наличност от продукта с име: " + name);
        } else if (totalRemoved < quantityToRemove) {
            System.out.println("Няма достатъчно количество. Премахнато е общо: " + totalRemoved);
        } else {
            System.out.println("Премахнато е общо: " + totalRemoved);
        }
    }
}


import java.util.*;

/**
 * Команда за изваждане на продукти от склада по зададено име и количество.
 * Приоритетно намалява продуктите с най-скоро изтичащ срок на годност.
 * Извежда детайли за всяка намалена партида и обработва недостиг.
 */
public class RemoveCommand implements Command {
    private WarehouseService service;
    private Scanner scanner;

    public RemoveCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Изпълнява командата Remove: търси продукти по име, изважда количество,
     * сортирано по срок на годност, извежда информация и логва всяко действие.
     */
    @Override
    public void execute(String[] args) {
        System.out.print("Име на продукт за изваждане: ");
        String name = scanner.nextLine().trim();

        System.out.print("Количество за изваждане: ");
        double quantityToRemove;
        try {
            quantityToRemove = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Невалидно число.");
            return;
        }

        List<Product> matching = new ArrayList<>();
        for (Product p : service.getProducts()) {
            if (p.getName().equalsIgnoreCase(name)) {
                matching.add(p);
            }
        }

        if (matching.isEmpty()) {
            System.out.println("Няма такъв продукт в склада.");
            return;
        }


        matching.sort(Comparator.comparing(Product::getExpiryDate));

        double remaining = quantityToRemove;
        List<Product> toRemoveLogs = new ArrayList<>();

        Iterator<Product> iterator = matching.iterator();
        while (iterator.hasNext() && remaining > 0) {
            Product p = iterator.next();
            double available = p.getQuantity();

            if (available <= remaining) {
                remaining -= available;
                service.getProducts().remove(p);
                toRemoveLogs.add(p);
                System.out.println("Премахнато: " + available + " от " + p.getName() + " на " + p.getLocation());
            } else {
                double newQuantity = available - remaining;

                Product updated = new Product(p.getName(), p.getExpiryDate(), p.getArrivalDate(),
                        p.getManufacturer(), p.getUnit(), newQuantity, p.getLocation(), p.getComment());

                service.getProducts().remove(p);
                service.getProducts().add(updated);

                Product removedPart = new Product(p.getName(), p.getExpiryDate(), p.getArrivalDate(),
                        p.getManufacturer(), p.getUnit(), remaining, p.getLocation(), p.getComment());

                toRemoveLogs.add(removedPart);
                System.out.println("Премахнато: " + remaining + " от " + p.getName() + " на " + p.getLocation());
                remaining = 0;
            }
        }

        if (remaining > 0) {
            System.out.println("Недостатъчно количество. Налични: " + (quantityToRemove - remaining));
            System.out.print("Искате ли да извадите наличното количество? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.equals("yes")) {
                System.out.println("Операцията е прекратена.");
                return;
            }
        }


        for (Product p : toRemoveLogs) {
            service.logChange("remove", p);
        }

        System.out.println("Изваждането завърши успешно.");
    }
}

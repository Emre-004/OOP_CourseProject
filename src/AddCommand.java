import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

/**
 * Команда за добавяне на нов продукт в склада.
 * Извършва пълна валидация на въведените данни и проверява за съвпадения
 * на име и срок на годност. Спазва правилата за добавяне към съществуваща партида или създаване на нова.
 */
public class AddCommand implements Command {
    private WarehouseService service;
    private Scanner scanner;

    public AddCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    private boolean containsDigit(String input) {
        return input.matches(".*\\d.*");
    }

    private String promptNonDigitInput(String name) {
        while (true) {
            //System.out.print(label + ": ");
            //String input = scanner.nextLine().trim();
            if (!containsDigit(name)) return name;
            System.out.println(name + " не трябва да съдържа цифри!");
        }
    }

    private LocalDate promptValidDate(String date) {
        while (true) {
            //System.out.print(label + " (YYYY-MM-DD): ");
            //String input = scanner.nextLine().trim();
            try {
                //return LocalDate.parse(input);
                return LocalDate.parse(date);
            } catch (Exception e) {
                System.out.println("Невалиден формат за дата!");
            }
        }
    }

    private double promptPositiveDouble(String label) {
        while (true) {
            //System.out.print(label + ": ");
            try {
                //double val = Double.parseDouble(scanner.nextLine().trim());
                double val = Double.parseDouble(label);
                if (val > 0) return val;
                System.out.println(label + " трябва да е положително число!");
            } catch (NumberFormatException e) {
                System.out.println("Моля, въведете валидно число!");
            }
        }
    }

    /**
     * Изпълнява командата за добавяне на продукт:
     * валидира входа, проверява за партиди със същото име и срок,
     * добавя към съществуваща или създава нова партида,
     * и извежда текущото общо количество на продукта по име.
     */
    @Override
    public void execute(String[] args) {
        //String name = promptNonDigitInput("Име");
        String name = promptNonDigitInput(args[0]);
        List<Product> sameNameProducts = service.findProductsByName(name);
        double totalQuantity = 0.0;
        for (Product p : sameNameProducts) {
            totalQuantity += p.getQuantity();
        }
        System.out.println("Общо количество налично за \"" + name + "\": " + totalQuantity);

        //LocalDate expiryDate = promptValidDate("Срок на годност");
        LocalDate expiryDate = promptValidDate(args[1]);
        //LocalDate arrivalDate = promptValidDate("Дата на постъпване");
        LocalDate arrivalDate = promptValidDate(args[2]);
        //String manufacturer = promptNonDigitInput("Производител");
        String manufacturer = promptNonDigitInput(args[3]);
        //String unit = promptNonDigitInput("Единица (kg/l)");
        String unit = promptNonDigitInput(args[4]);
        //double quantity = promptPositiveDouble("Количество");
        double quantity = promptPositiveDouble(args[5]);

        //System.out.print("Коментар: ");
        //String comment = scanner.nextLine().trim();
        String comment = args[6];

        for (Product existing : sameNameProducts) {
            if (existing.getExpiryDate().equals(expiryDate)) {
                double newQuantity = existing.getQuantity() + quantity;
                Product updated = new Product(
                        existing.getName(),
                        existing.getExpiryDate(),
                        existing.getArrivalDate(),
                        existing.getManufacturer(),
                        existing.getUnit(),
                        newQuantity,
                        existing.getLocation(),
                        existing.getComment() + " | + " + comment
                );

                service.getProducts().remove(existing);
                service.addProduct(updated);
                System.out.println("Продуктът беше добавен към съществуваща партида на същото място.");

                double newTotal = calculateTotalQuantityByName(name);
                System.out.println("Текущо общо количество за \"" + name + "\": " + newTotal);
                return;
            }
        }

        Location location = null;
        while (true) {
            //System.out.print("Местоположение (секция/рафт/номер): ");
            //String[] loc = scanner.nextLine().trim().split("/");
            String[] loc = args[7].split("/");
            if (loc.length != 3) {
                System.out.println("Местоположението трябва да е във формат секция/рафт/номер");
                continue;
            }

            location = new Location(loc[0].trim(), loc[1].trim(), loc[2].trim());

            if (service.isLocationOccupied(location)) {
                System.out.println("Местоположението " + location + " е заето!");
            } else {
                break;
            }
        }

        Product product = new Product(name, expiryDate, arrivalDate, manufacturer, unit, quantity, location, comment);
        service.addProduct(product);
        System.out.println("Продуктът беше добавен като нова партида на ново място.");

        double newTotal = calculateTotalQuantityByName(name);
        System.out.println("Текущо общо количество за \"" + name + "\": " + newTotal);
    }

    private double calculateTotalQuantityByName(String name) {
        double total = 0.0;
        for (Product p : service.findProductsByName(name)) {
            total += p.getQuantity();
        }
        return total;
    }
}
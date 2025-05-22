import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Команда за извеждане на логове по зададен период от време.
 */
public class LogCommand implements Command {
    private WarehouseService service;
    private Scanner scanner;

    /**
     * Създава инстанция на LogCommand с достъп до склада и вход от потребителя.
     */
    public LogCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Проверява дали даден низ е валидна дата.
     */
    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Изпълнява командата за извеждане на логове между две дати.
     * Филтрира записите според начална и крайна дата, въведени от потребителя.
     */
    @Override
    public void execute() {
        String fromStr;
        String toStr;

        while (true) {
            System.out.print("Въведи начален период (YYYY-MM-DD): ");
            fromStr = scanner.nextLine();
            if (isValidDate(fromStr)) break;
            System.out.println("Невалиден формат за дата!");
        }

        while (true) {
            System.out.print("Въведи краен период (YYYY-MM-DD): ");
            toStr = scanner.nextLine();
            if (isValidDate(toStr)) break;
            System.out.println("Невалиден формат за дата!");
        }

        LocalDate from = LocalDate.parse(fromStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate to = LocalDate.parse(toStr, DateTimeFormatter.ISO_LOCAL_DATE);

        List<LogEntry> logs = service.getLogs();

        System.out.println("Лог записи от " + fromStr + " до " + toStr + ":");
        for (LogEntry entry : logs) {
            LocalDate entryDate = LocalDate.parse(entry.getTimestamp().substring(0, 10));
            if ((entryDate.isEqual(from) || entryDate.isAfter(from)) &&
                    (entryDate.isEqual(to) || entryDate.isBefore(to))) {
                System.out.println(entry);
            }
        }
    }
}

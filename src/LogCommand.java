import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LogCommand implements Command{
    private WarehouseService service;
    private Scanner scanner;

    public LogCommand(WarehouseService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Въведи начален период (YYYY-MM-DD): ");
        String fromStr = scanner.nextLine();
        System.out.print("Въведи краен период (YYYY-MM-DD): ");
        String toStr = scanner.nextLine();

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

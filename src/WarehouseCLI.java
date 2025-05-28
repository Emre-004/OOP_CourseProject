

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Команден интерфейс за управление на склада.
 * Позволява въвеждане и изпълнение на различни команди чрез конзолата.
 */
public class WarehouseCLI {

    /**
     * Стартира интерфейса за работа със склада.
     * Обработва команди, въведени от потребителя.
     */
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        Map<String, Command> instructions = new HashMap<>();
        FileManager fileManager = new FileManager();
        WarehouseService service = new WarehouseService();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();//.trim();
            String[] parts = input.split(" ");
            String commandName = parts[0].toLowerCase();
            String[] args = new String[10];
            for (int i = 1; i < parts.length; i++) {
                args[i-1] = parts[i];
            }

            instructions.put("add", new AddCommand(service, scanner));
            instructions.put("remove", new RemoveCommand(service, scanner));
            instructions.put("print", new PrintCommand(service));
            instructions.put("log", new LogCommand(service, scanner));
            instructions.put("clean", new CleanCommand(service));
            instructions.put("open", new OpenCommand(service, fileManager, scanner));
            instructions.put("save", new SaveCommand(service, fileManager));
            instructions.put("saveas", new SaveAsCommand(service, fileManager, scanner));
            instructions.put("close", new CloseCommand(service));
            instructions.put("help", new HelpCommand());
            instructions.put("exit", new ExitCommand());

            Command command = instructions.get(commandName);
            if (command != null) {
                command.execute(args);
            } else {
                System.out.println("Неразпозната команда.");
            }
        }
    }
}

public class HelpCommand implements Command{
    @Override
    public void execute(String[] args) {
        System.out.println("Команди: add, remove, print, open, save, saveas, close, clean, log, help, exit");
    }
}

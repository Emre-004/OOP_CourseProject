public class ExitCommand implements Command{
    @Override
    public void execute(String[] args) {
        System.out.println("Изход от системата.");
        System.exit(0);
    }
}

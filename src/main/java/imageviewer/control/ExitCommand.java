package imageviewer.control;

public class ExitCommand implements Command{

    @Override
    public void execute() {
        System.exit(0);
    }
    
}

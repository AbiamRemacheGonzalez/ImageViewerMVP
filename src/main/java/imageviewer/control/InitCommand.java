package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitCommand implements Command{
    private final Map<String, Command> commands = new HashMap<>();
    private final ImageDisplay imageDisplay;
    private final List<Image> images;

    public InitCommand(ImageDisplay imageDisplay, List<Image> images) {
        this.imageDisplay = imageDisplay;
        this.images = images;
    }
    
    @Override
    public void execute() {
        imageDisplay.display(images.get(0));
        commands.put("N", new NextImageCommand(images, imageDisplay));
        commands.put("P", new PrevImageCommand(images, imageDisplay));
        commands.put("Q", new ExitCommand());
    }

    public Map<String,Command> getcommands() {
        return commands;
    }
    
}

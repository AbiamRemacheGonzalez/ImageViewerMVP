package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

public class PrevImageCommand implements Command{
    private final ImageDisplay imageDisplay;
    private final List<Image> images;

    public PrevImageCommand(List<Image> images, ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
        this.images = images;
    }
    
    @Override
    public void execute() {
        imageDisplay.display(prev());
    }

    private Image prev() {
        return images.get(prevIndex());
    }

    private int prevIndex() {
        return (currentIndex() + images.size() - 1) % images.size();
    }

    private int currentIndex() {
        return images.indexOf(imageDisplay.currentImage());
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer.control;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

public class NextImageCommand implements Command{
    private final ImageDisplay imageDisplay;
    private final List<Image> images;

    public NextImageCommand(List<Image> images, ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
        this.images = images;
    }
    
    @Override
    public void execute() {
        imageDisplay.display(next());
    }

    private Image next() {
        return images.get(nextIndex());
    }

    private int nextIndex() {
        return (currentIndex() + 1) % images.size();
    }

    private int currentIndex() {
        return images.indexOf(imageDisplay.currentImage());
    }
    
}

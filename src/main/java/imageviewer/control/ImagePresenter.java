package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

public class ImagePresenter {

    private final List<Image> images;

    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imagedisplay) {
        this.images = images;
        this.imageDisplay = imagedisplay;
        this.imageDisplay.display(images.get(0).getName());
        this.imageDisplay.on(shift());
    }
    private ImageDisplay.Shift shift(){
        return new ImageDisplay.Shift() {
            @Override
            public String left() {
                return images.get(bounds(index()+1)).getName();
            }

            @Override
            public String right() {
                 return images.get(bounds(index()- 1)).getName();
            }

            private int bounds(int i) {
                return (i + images.size())%images.size();
            }
        };
    }
    private int index(){
        for (int i = 0; i < images.size(); i++) {
            if (imageDisplay.current().equals(images.get(i).getName())) return i;
        }
        return -1;
    }
    
}

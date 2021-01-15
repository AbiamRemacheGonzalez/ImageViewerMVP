package imageviewer.view;

import imageviewer.model.Image;
    
public interface ImageDisplay {
    /*Cuando vamos a trabajar con MVP debemos tener
    en cuenta que la vista no puede ver al modelo, esdecir
    ImageDisplay no puede ver a Image
    
    void display(Image image);
    public Image currentImage();*/
    
    void display(String image);
    public String current();
    void on(Shift shift);
    
    interface Shift{
        String left();
        String right();
    }
}

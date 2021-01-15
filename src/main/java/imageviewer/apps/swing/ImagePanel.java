package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ImagePanel extends JPanel implements ImageDisplay{
//Para vincular esta clase con el modelo debemos hacer que implemente a 
//ImageDisplay al igual que hicimos que implementara en el MockImagedisplay
    private BufferedImage bitmap;
    private Image image;
    /*public ImagePanel(){
        try {
            bitmap = ImageIO.read(new File("fotos/b.jpg"));
        } catch (IOException ex) {
        }
    }*/
    //Este constructor no hace falta
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (bitmap == null) return; //centinela
        Scale scale = new Scale(bitmap.getWidth(), bitmap.getHeight(), getWidth() ,getHeight());
        g.drawImage(bitmap, scale.x(), scale.y(), scale.width(), scale.height(), this);
        //sx, sy --> Posición dentro del panel.
        //sw, sh --> Ancho y alto con el cual se presenta la imagen.
    }

    @Override
    public void display(Image image) {
        this.image = image;
        loadBitmap();
        repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }

    private void loadBitmap() {
        try {
            bitmap = ImageIO.read(new File(image.getName()));
        } catch (IOException ex) {
            bitmap = null;
        }
    }
    
    private static class Scale{

        private final int iw;
        private final int ih;
        private final int pw;
        private final int ph;

        private Scale(int iw, int ih, int pw, int ph) {
            this.iw = iw;
            this.ih = ih;
            this.pw = pw;
            this.ph = ph;
        }
        int x(){
            return (pw - width())/2;
        }
        int y(){
            return (ph - height())/2;
        }
        int width(){
            return adjustWidth() ? pw : (int)(iw * (double)ph) /ih;
        }
        int height(){
            return adjustWidth() ? (int)(ih * (double) pw / iw ): ph;
        }
        private boolean adjustWidth(){
            return iw * ph > pw * ih;
        }
        
    }
}


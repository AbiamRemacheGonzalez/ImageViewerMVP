package imageviewer.apps.swing;

import imageviewer.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ImagePanel extends JPanel implements ImageDisplay{
    private BufferedImage bitmap;
    private BufferedImage futureBitmap;
    private String current;
    private String future;
    private int offset = 0;
    private Shift shift;
    
    ImagePanel(){
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (bitmap == null) return; //centinela
        //Se intentó escalar de alguna forma, pero no llegué a una solución viable
        /*Scale currentScale = new Scale(bitmap.getWidth(), bitmap.getHeight(), getWidth() ,getHeight());
        g.drawImage(bitmap, offset, currentScale.y(), currentScale.width(), currentScale.height(), null);*/
        g.drawImage(bitmap, offset, 0, null);
        if (offset == 0)return;
        g.drawImage(futureBitmap,offset<0 ? bitmap.getWidth()+offset:offset-futureBitmap.getWidth(), 0, null);
    }

    @Override
    public void display(String image) {
        this.current = image;
        this.bitmap = loadBitmap(image);
        repaint();
    }
    
    private void setOffset(int offset){
        this.offset = offset;
        if(offset<0)setFurture(shift.left());
        if(offset>0)setFurture(shift.right());
        repaint();
    }
    
    private void toggle(){
        this.current = this.future;
        this.bitmap = this.futureBitmap;
        setOffset(0);

    }
    @Override
    public String current() {
        return current;
    }

    private BufferedImage loadBitmap(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    private void setFurture(String name) {
        if (name.equals(future)) return;
        this.future=name;
        this.futureBitmap=loadBitmap(name);
    }
    
    
    private class MouseHandler implements MouseListener, MouseMotionListener{

        private int initial;

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            initial = event.getX();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            if(Math.abs(offset)>getWidth()/2)
                toggle();
            else  
            setOffset(0);
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            setOffset(event.getX()-initial);
            //--> offset positivo
            //<-- offset negativo
        }

        @Override
        public void mouseMoved(MouseEvent event) {
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


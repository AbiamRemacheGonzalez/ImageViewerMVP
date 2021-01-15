package imageviewer.apps.swing;

import imageviewer.control.ImagePresenter;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main extends JFrame{
    public static void main(String[] args) {
        new Main().execute();
    }
    private ImageDisplay imagedisplay;
    private final ImagePresenter imagePresenter;
    
    public Main(){
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        this.imagePresenter = createImagePresenter();
    }
    private void execute() {
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        ImagePanel panel = new ImagePanel();
        this.imagedisplay = panel;
        return panel;
    }

    private ImagePresenter createImagePresenter() {
        return new ImagePresenter(loadImages(), imagedisplay);
    }

    private List<Image> loadImages() {
        return new FileImageLoader(new File("fotos")).load();
    }
}

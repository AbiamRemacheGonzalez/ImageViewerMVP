package imageviewer.apps.swing;

import imageviewer.control.Command;
import imageviewer.control.NextImageCommand;
import imageviewer.control.PrevImageCommand;
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
    private Map<String,Command> commands = new HashMap<>();
    
    public Main(){
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        this.add(toolbar(),BorderLayout.SOUTH);
        List<Image> images = new FileImageLoader(new File("fotos")).load();
        this.imagedisplay.display(images.get(0));
        this.commands.put("Prev", new PrevImageCommand(images, imagedisplay));
        this.commands.put("Next", new NextImageCommand(images, imagedisplay));
    }
    private void execute() {
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        ImagePanel panel = new ImagePanel();
        this.imagedisplay = panel;
        return panel;
    }

    private JMenuBar toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("Prev"));
        toolbar.add(button("Next"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        return button;
    }
}

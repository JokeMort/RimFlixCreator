import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {
    Window() {
        this.setTitle("RimflixCreator");
        this.setSize(500, 600);
        this.setMinimumSize(new Dimension(300, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon logo = new ImageIcon("RimflixCreator\\src\\logo.jpg");
        this.setIconImage(logo.getImage());
    }

}

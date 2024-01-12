import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame {
    JButton button1;

    Window() {
        this.setTitle("RimflixCreator");
        this.setSize(500, 600);
        this.setMinimumSize(new Dimension(300, 400));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon logo = new ImageIcon("RimflixCreator\\src\\logo.jpg");
        this.setIconImage(logo.getImage());

        button1 = new JButton("Generate XML");
        button1.setBounds(200, 100, 200, 50);
        this.add(button1);
        button1.addActionListener(e -> System.out.println("Hehe"));
        button1.setFocusable(false);
    }
}

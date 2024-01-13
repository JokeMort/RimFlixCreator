
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame {
    JButton button1;
    JTextField textField1;
    JTextField textField2;
    JTextField textField3;

    Window(showsGenerator showGen) {
        this.setTitle("RimflixCreator");
        this.setSize(500, 600);
        this.setMinimumSize(new Dimension(300, 400));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon logo = new ImageIcon("src\\logo.jpg");
        this.setIconImage(logo.getImage());

        Label label = new Label("Test text");
        label.setBounds(0, 50, 100, 50);

        button1 = new JButton("Generate XML");
        button1.setBounds(0, 0, 200, 50);

        button1.addActionListener(
                e -> showGen.redButton(textField1.getText(), textField2.getText(), List.of(true, true, true, true)));
        button1.setFocusable(false);

        textField1 = new JTextField("Shows");
        textField1.setBounds(200, 0, 100, 50);

        textField2 = new JTextField("Defs");
        textField2.setBounds(200, 100, 100, 50);

        textField3 = new JTextField();
        textField3.setBounds(200, 150, 100, 50);

        JPanel panelBottom = new JPanel();
        panelBottom.setBackground(Color.WHITE);
        panelBottom.add(button1);
        panelBottom.add(textField1);
        panelBottom.add(textField2);
        panelBottom.add(textField3);
        panelBottom.add(label);
        panelBottom.setBounds(0, 0, 800, 200);

        this.add(panelBottom);

    }
}

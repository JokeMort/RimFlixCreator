
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Window extends JFrame implements ActionListener {

    JButton targetChooser;
    JButton sourceChooser;
    JCheckBox tvTube;
    JCheckBox tvFlat;
    JCheckBox tvMega;
    JCheckBox tvUltra;
    JButton generateButton;
    String pickedSource;
    String pickedTarget;
    JTextField sourceField;
    JTextField targetField;

    Window(showsGenerator showGen) {
        pickedSource = "Shows";
        pickedTarget = "Defs";

        this.setTitle("RimflixCreator");
        this.setSize(500, 500);
        this.setMinimumSize(new Dimension(100, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        ImageIcon logo = new ImageIcon("src\\secondlogo.jpg");
        this.setIconImage(logo.getImage());

        JPanel panelTop = new JPanel();

        JPanel panelMid1 = new JPanel();
        JPanel panelMid2 = new JPanel();
        JPanel panelBottom = new JPanel();

        JLabel sourceTip = new JLabel();
        sourceTip.setText("Pick your own directory or use default one");
        panelTop.add(sourceTip, BorderLayout.NORTH);
        JPanel panelTop1 = new JPanel();
        JPanel panelTop2 = new JPanel();
        JLabel sTip = new JLabel("source directory:");
        sourceField = new JTextField(pickedSource, 15);
        sourceChooser = new JButton("...");
        sourceChooser.addActionListener(this);

        panelTop1.add(sTip, BorderLayout.WEST);
        panelTop1.add(sourceField, BorderLayout.CENTER);
        panelTop1.add(sourceChooser, BorderLayout.EAST);

        JLabel tTip = new JLabel("target directory:");
        targetField = new JTextField(pickedTarget, 15);
        targetChooser = new JButton("...");
        targetChooser.addActionListener(this);

        panelTop2.add(tTip, BorderLayout.WEST);
        panelTop2.add(targetField, BorderLayout.CENTER);
        panelTop2.add(targetChooser, BorderLayout.EAST);
        panelTop.add(panelTop1, BorderLayout.CENTER);
        panelTop.add(panelTop2, BorderLayout.SOUTH);

        JLabel checkboxTip = new JLabel("Pick at least one");
        panelMid1.add(checkboxTip, BorderLayout.NORTH);

        tvTube = new JCheckBox("Tube Television");
        tvFlat = new JCheckBox("Flatscreen Television");
        tvMega = new JCheckBox("Megascreen Television");
        tvUltra = new JCheckBox("Ultrascreen Television");

        panelMid2.add(tvTube);
        panelMid2.add(tvFlat);
        panelMid2.add(tvMega);
        panelMid2.add(tvUltra);

        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        panelBottom.add(generateButton);
        /*
         * Label label = new Label("Test text");
         * label.setBounds(0, 50, 100, 50);
         * 
         * button1 = new JButton("Generate XML");
         * button1.setBounds(0, 0, 200, 50);
         * 
         * button1.addActionListener(
         * e -> showGen.redButton(textField1.getText(), textField2.getText(),
         * List.of(true, true, true, true)));
         * button1.setFocusable(false);
         * 
         * textField1 = new JTextField("Shows");
         * textField1.setBounds(200, 0, 100, 50);
         * 
         * textField2 = new JTextField("Defs");
         * textField2.setBounds(200, 100, 100, 50);
         * 
         * textField3 = new JTextField();
         * textField3.setBounds(200, 150, 100, 50);
         * 
         * JPanel panelBottom = new JPanel();
         * panelBottom.setBackground(Color.WHITE);
         * panelBottom.add(button1);
         * panelBottom.add(textField1);
         * panelBottom.add(textField2);
         * panelBottom.add(textField3);
         * panelBottom.add(label);
         * panelBottom.setBounds(0, 0, 800, 200);
         * 
         * this.add(panelBottom);
         */
        panelMid1.add(panelMid2, BorderLayout.SOUTH);
        panelTop.setPreferredSize(new Dimension(500, 150));
        panelTop1.setPreferredSize(new Dimension(500, 50));
        panelTop2.setPreferredSize(new Dimension(500, 50));
        panelMid1.setPreferredSize(new Dimension(500, 100));
        panelMid2.setPreferredSize(new Dimension(500, 100));
        this.getContentPane().add(panelTop, BorderLayout.NORTH);
        this.getContentPane().add(panelMid1, BorderLayout.CENTER);
        this.getContentPane().add(panelBottom, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            if (tvFlat.isSelected() || tvMega.isSelected() || tvTube.isSelected() || tvUltra.isSelected() != false) {
                List<Boolean> checkedBoxes = new ArrayList<>();
                checkedBoxes.add(tvFlat.isSelected());
                checkedBoxes.add(tvMega.isSelected());
                checkedBoxes.add(tvTube.isSelected());
                checkedBoxes.add(tvUltra.isSelected());
                showsGenerator.redButton(pickedSource, pickedTarget, checkedBoxes);
            } else {
                System.out.println("Pick some box");
            }
        }
        if (e.getSource() == targetChooser) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose your target directory");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = fileChooser.showOpenDialog(this);

            if (userSelection == fileChooser.APPROVE_OPTION) {
                pickedTarget = fileChooser.getSelectedFile().getAbsolutePath();
                targetField.setText(pickedTarget);
            }
        }
        if (e.getSource() == sourceChooser) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose your source directory");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = fileChooser.showOpenDialog(this);

            if (userSelection == fileChooser.APPROVE_OPTION) {
                pickedTarget = fileChooser.getSelectedFile().getAbsolutePath();
                sourceField.setText(pickedTarget);
            }
        }
    }
}

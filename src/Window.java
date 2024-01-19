
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
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
    JTextField speedField;

    Window(showsGenerator showGen) {
        pickedSource = "Shows";
        pickedTarget = "Defs";

        this.setTitle("RimflixCreator");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        ImageIcon logo = new ImageIcon("src\\secondlogo.jpg");
        this.setIconImage(logo.getImage());

        JPanel panelTop = new JPanel();

        JPanel panelMid = new JPanel();

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
        panelMid.add(new JLabel(""));

        JLabel checkboxTip = new JLabel("Pick at least one");
        panelMid.add(checkboxTip);

        tvTube = new JCheckBox("Tube Television");
        tvFlat = new JCheckBox("Flatscreen Television");
        tvMega = new JCheckBox("Megascreen Television");
        tvUltra = new JCheckBox("Ultrascreen Television");

        panelMid.add(new JLabel(""));
        panelMid.add(new JLabel(""));
        panelMid.add(tvTube);
        panelMid.add(new JLabel(""));
        panelMid.add(new JLabel(""));
        panelMid.add(tvFlat);
        panelMid.add(new JLabel(""));
        panelMid.add(new JLabel(""));
        panelMid.add(tvMega);
        panelMid.add(new JLabel(""));
        panelMid.add(new JLabel(""));
        panelMid.add(tvUltra);

        JPanel panelBottomNorth = new JPanel();
        JPanel panelBottomCenter = new JPanel();
        JPanel panelBottomSouth = new JPanel();

        JLabel framesJLabel = new JLabel("How many frames per second?");
        panelBottomNorth.add(framesJLabel);
        speedField = new JTextField("20");
        panelBottomCenter.add(speedField);
        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        panelBottomSouth.add(generateButton);

        panelMid.setLayout(new GridLayout(5, 2));

        panelTop.setPreferredSize(new Dimension(500, 150));
        panelTop1.setPreferredSize(new Dimension(500, 50));
        panelTop2.setPreferredSize(new Dimension(500, 50));
        panelMid.setPreferredSize(new Dimension(100, 150));

        panelBottom.setPreferredSize(new Dimension(500, 150));
        panelBottomNorth.setPreferredSize(new Dimension(500, 25));
        panelBottomCenter.setPreferredSize(new Dimension(500, 25));
        panelBottom.add(panelBottomNorth, BorderLayout.NORTH);
        panelBottom.add(panelBottomCenter, BorderLayout.CENTER);
        panelBottom.add(panelBottomSouth, BorderLayout.SOUTH);
        this.getContentPane().add(panelTop, BorderLayout.NORTH);
        this.getContentPane().add(panelMid, BorderLayout.CENTER);
        this.getContentPane().add(panelBottom, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            if ((tvFlat.isSelected() || tvMega.isSelected() || tvTube.isSelected() || tvUltra.isSelected() != false)
                    && isDigit(speedField.getText())) {
                List<Boolean> checkedBoxes = new ArrayList<>();
                checkedBoxes.add(tvTube.isSelected());
                checkedBoxes.add(tvFlat.isSelected());
                checkedBoxes.add(tvMega.isSelected());
                checkedBoxes.add(tvUltra.isSelected());
                String speed = speedField.getText();
                showsGenerator.redButton(pickedSource, pickedTarget, checkedBoxes, speed);
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
                pickedSource = fileChooser.getSelectedFile().getAbsolutePath();
                sourceField.setText(pickedSource);
                System.out.println(pickedSource);
            }
        }
    }

    public boolean isDigit(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

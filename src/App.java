
import java.io.File;

import javax.swing.*;

public class App extends JFrame {
    public static void main(String[] args) throws Exception {
        checkIfDirectoriesExist();
        showsGenerator showGen = new showsGenerator();
        new Window(showGen);

    }

    private static void checkIfDirectoriesExist() {
        File shows = new File("Shows");
        File defs = new File("Defs");
        if (!shows.isDirectory()) {
            shows.mkdir();
        }
        if (!defs.isDirectory()) {
            defs.mkdir();
        }

    }

}

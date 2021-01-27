package MazeRunner;

import java.awt.*;
import javax.swing.*;

public class Main {
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu fileMenu;
    private static GUI UI;
    public static void main(String[] args) {
        initializeMenuBar();
        initializeFrame();
    }

    private static void initializeFrame() {
        frame = new JFrame();
        UI = new HomeGUI();
        frame.add(UI.getPanel(), BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setTitle("Maze Runner");
        frame.pack();
        frame.setVisible(true);
    }

    private static void initializeMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("New/Restart"));

        menuBar.add(fileMenu);

        for (Component menu : menuBar.getComponents()) {
            for (Component item : ((JMenu) menu).getMenuComponents()) {
                item.setName(((JMenuItem) item).getText());
                ((JMenuItem) item).addActionListener(new MyActionListener());
            }
        }

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Stack");
    }

    public static void setMenuVisibility(boolean bool) {
        for (Component menu : menuBar.getComponents()) {
            for (Component item : ((JMenu) menu).getMenuComponents()) {
                ((JMenuItem) item).setEnabled(bool);
            }
        }
    }

    public static void setGUI(GUI UI) {
        Main.UI = UI;
    }

    public static JFrame getFrame() {
        return Main.frame;
    }

    public static GUI getGUI() {
        return Main.UI;
    }
}
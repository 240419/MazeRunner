package MazeRunner;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyComponentListener extends ComponentAdapter {
    @Override
    public void componentHidden(ComponentEvent e) {
        JPanel currentPanel = ((JPanel) e.getSource());
        switch (currentPanel.getName()) {
            case "Home":
            Main.setGUI(new GameGUI( ((HomeGUI) Main.getGUI()).getGridSize()));
            break;

            default:
            Main.setGUI(new HomeGUI());
            Main.getFrame().removeKeyListener(Main.getFrame().getKeyListeners()[0]);
            break;
        }
        currentPanel.removeAll();
        currentPanel.getParent().revalidate();
        currentPanel.getParent().repaint();
        currentPanel.getParent().add(Main.getGUI().getPanel(), BorderLayout.CENTER);
    }
}

package MazeRunner;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyActionListener extends AbstractAction {
  private static final long serialVersionUID = 1L;

  @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = ((JMenuItem) e.getSource());
        switch (menuItem.getName()) {
            case "Show cheat code":
            // ((GameGUI) Main.getGUI()).setTurnInfoChoice(GameGUI.turnInfo.NOTIFICATION);
            Main.getFrame().revalidate();
            Main.getFrame().repaint();
            Main.getFrame().add(Main.getGUI().getPanel(), BorderLayout.CENTER);
            break;

            default:
            Main.getGUI().getPanel().setVisible(false);
            break;
        }        
    }
  
}

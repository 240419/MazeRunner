package MazeRunner;
import javax.swing.*;
import java.awt.event.*;

public class MyActionListener extends AbstractAction {
  private static final long serialVersionUID = 1L;

  @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = ((JMenuItem) e.getSource());
        switch (menuItem.getName()) {
            case "Show cheat code":
            // Main.getFrame().revalidate();
            // Main.getFrame().repaint();
            // Main.getFrame().add(Main.getGUI().getPanel(), BorderLayout.CENTER);
            ((GameGUI) Main.getGUI()).showCheatCode();
            break;

            default:
            Main.getGUI().getPanel().setVisible(false);
            break;
        }        
    }
  
}

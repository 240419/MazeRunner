import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameGUI extends GUI implements ActionListener {

  private Maze maze;
  private JLabel[][] mazeLabels;
  private JLabel infoLabel;
  public GameGUI() {
    super("Game");
    Main.setMenuVisibility(false);

    maze = new Maze();
    mazeLabels = new JLabel[maze.getMap().length][maze.getMap().length];
    infoLabel = new JLabel("Here is your current position:");
    maze.printMap();
    this.getPanel().add(infoLabel);

    for (int i = 0; i < mazeLabels.length; i++) {
      JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
      Dimension frameSize = Main.getFrame().getSize();
      int height = (int) (frameSize.getHeight())/mazeLabels.length;
      for (int j = 0; j < mazeLabels[i].length; j++) {
        // initialize square
        JLabel square = new JLabel(Character.toString(maze.getMap()[i][j]));
        System.out.println(square.getText());
        // square.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        square.setPreferredSize(new Dimension(height, height));
        square.setName(String.valueOf(i*10 + j));
        newPanel.add(square);
        mazeLabels[i][j] = square;
        this.getPanel().add(newPanel);
      }
    }

    this.setComponentAlignment();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}

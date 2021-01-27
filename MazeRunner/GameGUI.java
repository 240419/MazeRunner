package MazeRunner;

import javax.swing.*;

import MazeRunner.Maze.Player;

import java.awt.*;
import java.awt.event.*;

public class GameGUI extends GUI implements KeyListener {

  private Maze maze;
  private Player player;
  private JLabel[][] mazeLabels;
  private JLabel infoLabel;

  public GameGUI() {
    super("Game");
    Main.setMenuVisibility(true);

    maze = new Maze();
    player = maze.getPlayer();
    mazeLabels = new JLabel[maze.getMap().length][maze.getMap().length];
    infoLabel = new JLabel("Here is your current position:");
    this.getPanel().add(infoLabel);

    for (int i = 0; i < mazeLabels.length; i++) {
      JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
      Dimension frameSize = Main.getFrame().getSize();
      int height = (int) (frameSize.getHeight())/mazeLabels.length;
      for (int j = 0; j < mazeLabels[i].length; j++) {
        // initialize square
        JLabel square = new JLabel(Character.toString(maze.getMap()[i][j]));
        // square.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        square.setPreferredSize(new Dimension(height, height));
        square.setName(String.valueOf(i*10 + j));
        newPanel.add(square);
        mazeLabels[i][j] = square;
        this.getPanel().add(newPanel);
      }
    }

    Main.getFrame().addKeyListener(this);
    Main.getFrame().requestFocus();
    this.setComponentAlignment();

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int x, y;
    mazeLabels[player.getR()][player.getC()].setText(".");
    switch (e.getKeyCode()) {
      case 37: // left
      x = 0; y = -1;
      break;

      case 38: // up
      x = -1; y = 0;
      break;

      case 39: // right
      x = 0; y = 1;
      break;

      case 40: // down
      x = 0; y = 1;
      break;

      default:
      x = 0; y = 0;
      break;
    }
    if (player)
    player.move(x, y);
    mazeLabels[player.getR()][player.getC()].setText("x");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    Main.getFrame().revalidate();
    Main.getFrame().repaint();
    Main.getFrame().add(Main.getGUI().getPanel(), BorderLayout.CENTER);
  }
}

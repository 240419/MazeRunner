package MazeRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameGUI extends GUI implements KeyListener {

  private Maze maze;
  private Maze.Player player;
  private JLabel[][] mazeLabels;
  private JLabel movesLabel;

  public GameGUI() {
    super("Game");
    Main.setMenuVisibility(true);
    maze = new Maze();
    player = maze.getPlayer();
    mazeLabels = new JLabel[maze.getMap().length][maze.getMap().length];
    movesLabel = new JLabel("Number of moves taken: " + player.getMoves());
    this.getPanel().add(movesLabel);

    for (int i = 0; i < mazeLabels.length; i++) {
      JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
      Dimension frameSize = Main.getFrame().getSize();
      int height = (int) (frameSize.getHeight())/mazeLabels.length;
      for (int j = 0; j < mazeLabels[i].length; j++) {
        // initialize square
        JLabel square = new JLabel();
        square.setPreferredSize(new Dimension(height, height));
        mazeLabels[i][j] = square;
        newPanel.add(square);
        this.getPanel().add(newPanel);
      }
    }

    refreshMap();
    
    Main.getFrame().addKeyListener(this);
    Main.getFrame().requestFocus();
    this.setComponentAlignment();

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    boolean didMove = false;
    String message, title;

    switch (e.getKeyCode()) {
      case 37: // left
      didMove = player.moveLeft();
      break;

      case 38: // up
      didMove = player.moveUp();
      break;

      case 39: // right
      didMove = player.moveRight();
      break;

      case 40: // down
      didMove = player.moveDown();
      break;

      default:
      break;
    }

    if (!didMove) {
      message = "You can't move there! \nPlease also only use the arrow keys to move!";
      title = "Invalid move";
      JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);

    } else if (player.didIWin()) {
      message = "You won the game! It took you " + player.getMoves() + " moves to beat the game!";
      title = "Maze beaten!";
      JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);
      this.getPanel().setVisible(false);

    } else if (player.getMoves() == 50 || player.getMoves() == 75 || player.getMoves() == 90) {
      message = "You have made %d moves, you have %d remaining before the exit closes!";
      message = String.format(message, player.getMoves(), Maze.Player.MAX_MOVES - player.getMoves());
      title = "WARNING";
      JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);

    } else if (player.getMoves() == Maze.Player.MAX_MOVES) {
      message = "Oh no! You took too long to escape!";
      title = "Game Over"; 
      JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);
      this.getPanel().setVisible(false);
    }
    refreshMap();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    Main.getFrame().revalidate();
    Main.getFrame().repaint();
  }

  private void refreshMap() {
    for (int i = 0; i < mazeLabels.length; i++) {
      for (int j = 0; j < mazeLabels[i].length; j++) {
        mazeLabels[i][j].setText(String.valueOf(maze.getMap()[i][j]));

        Color color; 
        switch (maze.getMap()[i][j]) {
          case 'x':
          color = Color.blue;
          break;

          case '-':
          color = Color.green;
          break;

          case '0':
          color = Color.red;
          break;

          default:
          color = Color.black;
          break;
        }
        movesLabel.setText("Number of moves taken: " + player.getMoves());
        mazeLabels[i][j].setForeground(color);
      }
    }
  }
}

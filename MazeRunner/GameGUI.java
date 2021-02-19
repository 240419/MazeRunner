package MazeRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends GUI implements KeyListener {

  private Dimension frameSize, squareSize;
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
    frameSize = Main.getFrame().getSize();
    squareSize = new Dimension((int) frameSize.getHeight()/mazeLabels.length, (int) frameSize.getHeight()/mazeLabels.length);
    this.getPanel().add(movesLabel);

    for (int i = 0; i < mazeLabels.length; i++) {
      JPanel newPanel = new JPanel();
      for (int j = 0; j < mazeLabels[i].length; j++) {
        // initialize square
        JLabel square = new JLabel();
        square.setPreferredSize(squareSize);
        mazeLabels[i][j] = square;
        newPanel.add(square);
      }
      this.getPanel().add(newPanel);
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
    String dir = "";

    switch (e.getKeyCode()) {
      case 37: // left
      didMove = player.moveLeft();
      dir = "L";
      break;

      case 38: // up
      didMove = player.moveUp();
      dir = "U";
      break;

      case 39: // right
      didMove = player.moveRight();
      dir = "R";
      break;

      case 40: // down
      didMove = player.moveDown();
      dir = "D";
      break;

      default:
      break;
    }

    if (!didMove) {
      if (!dir.isEmpty() && player.isThereAPit(dir)) {
        message = "Watch out! There's a pit ahead, jump in it?";
        title = "Mysterious Pit";
        int result = JOptionPane.showConfirmDialog(Main.getFrame(), message, title, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
          player.jumpOverPit(dir);
        } else {
          message = "Oh No! You fell to your death...oof";
          title = "Fall Damage";
          JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);
          this.getPanel().setVisible(false);
        }
      } else {
        message = "You can't move there! \nPlease also only use the arrow keys to move!";
        title = "Invalid move";
        JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);
      }
      

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

          case '|': case '-':
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

  public void showCheatCode() {
    JLabel[][] solutionLabels = new JLabel[maze.getMap().length][maze.getMap().length];
    char[][] solutionChars = new char[maze.getMap().length][maze.getMap().length];
    JPanel cheatCodePanel = new JPanel();
    int[] start = {1, 0}, end = {10, 19};

    solutionChars = MazeSolver.solver(maze.getSolutionWOPitfalls(), start, end, maze.getSolution());
    cheatCodePanel.setPreferredSize(new Dimension( (int) (this.getPanel().getWidth()/1.5), (int) (this.getPanel().getHeight()/1.125)));
    cheatCodePanel.setLayout(new GridLayout(0, 1));

    for (int i = 0; i < solutionChars.length; i++) {
      JPanel newPanel = new JPanel();
      for (int j = 0; j < solutionChars[i].length; j++) {
        JLabel square = new JLabel(String.valueOf(solutionChars[i][j]));
        square.setPreferredSize(squareSize);
        if (solutionChars[i][j] == '#') { square.setForeground(Color.blue); }
        solutionLabels[i][j] = square;
        newPanel.add(square);
      }
      cheatCodePanel.add(newPanel);
    }
    
    // need to "refill" solution because array passed is a reference (we want to restore the solution)
    maze.fillSolution();
    JOptionPane.showMessageDialog(Main.getFrame(), cheatCodePanel, "Cheat Code", JOptionPane.PLAIN_MESSAGE);
  }
}

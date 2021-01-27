package MazeRunner;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class HomeGUI extends GUI implements ActionListener {
  private JLabel introLabel, infoLabel;
  private JTextField gridSizeTextField;
  private JButton submitButton;
  public HomeGUI() {
    super("Home");
    Main.setMenuVisibility(false);

    introLabel = new JLabel("Welcome to Maze Runner!");
    infoLabel = new JLabel("Enter the size of the board");
    gridSizeTextField = new JTextField();
    submitButton = new JButton("Play!");

    introLabel.setFont(new Font(introLabel.getFont().getName(), Font.PLAIN, 60));
    submitButton.addActionListener(this);

    this.getPanel().add(introLabel);
    this.getPanel().add(infoLabel);
    this.getPanel().add(gridSizeTextField);
    this.getPanel().add(submitButton);

    this.setComponentAlignment();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String message = "Please enter a valid integer";
    String title = "Invalid Input";
    try {
      int gridSize = Integer.parseInt(gridSizeTextField.getText());
      if (gridSize > 20 || gridSize < 0) {
        message = "The size you choose is either too large or too small!";
        throw new Exception();
      }
      this.getPanel().setVisible(false);
    } catch (Exception exception) {
      JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);
    }
    
  }

}
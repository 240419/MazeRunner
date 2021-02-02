package MazeRunner;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class HomeGUI extends GUI implements ActionListener {
  private JLabel introLabel, infoLabel;
  private JButton submitButton;
  
  public HomeGUI() {
    super("Home");
    Main.setMenuVisibility(false);

    introLabel = new JLabel("Welcome to Maze Runner!");
    infoLabel = new JLabel("Press the \"Play\" button down below");
    submitButton = new JButton("Play!");

    introLabel.setFont(new Font(introLabel.getFont().getName(), Font.PLAIN, 60));
    submitButton.addActionListener(this);

    this.getPanel().add(introLabel);
    this.getPanel().add(infoLabel);
    this.getPanel().add(submitButton);

    this.setComponentAlignment();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.getPanel().setVisible(false);
  }
}
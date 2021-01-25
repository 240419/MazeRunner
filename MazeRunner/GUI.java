import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GUI {
    JPanel panel;
    private HashMap<String, Font> fontsMap;

    private enum fonts {
        OXYGEN_BOLD, OXYGEN_REGULAR;

        @Override
        public String toString() {
            return "Oxygen/" + super.toString().toLowerCase() + ".ttf";
        }
    }

    public GUI(String panelName) {
        panel = new JPanel();

        try {
            fontsMap = new HashMap<>();
            for (fonts font : fonts.values()) {
                Font myFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource(font.toString()).openStream());
                fontsMap.put(font.toString(), myFont);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(myFont);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUIFont("Label.font", fontsMap.get(fonts.OXYGEN_BOLD.toString()), 30);
        setUIFont("Button.font", fontsMap.get(fonts.OXYGEN_REGULAR.toString()), 25);
        setUIFont("TextField.font", fontsMap.get(fonts.OXYGEN_REGULAR.toString()), 25);

        panel.addComponentListener(new MyComponentListener());
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setLayout(new GridLayout(0, 1));
        panel.setName(panelName);
    }

    public void setComponentAlignment() {
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
            } else if (component instanceof JButton) {
                ((JButton) component).setHorizontalAlignment(JButton.CENTER);
            } else if (component instanceof JTextField) {
                ((JTextField) component).setHorizontalAlignment(JTextField.CENTER);
            }
        }
    }

    private static void setUIFont(String component, Font font, int fontSize) {
        UIManager.put(component, new Font(font.getName(), Font.PLAIN, fontSize));
    }

    public JPanel getPanel() {
        return panel;
    }
}


package ui.gui.panel;

import javax.swing.*;
import java.awt.*;

public class PagePanel {

    public static JPanel createPage(String title) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(Color.DARK_GRAY);

        panel.add(label);

        return panel;
    }
}

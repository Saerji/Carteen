
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;

public class HistoryPanel extends JPanel {

    public HistoryPanel() {

        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("ORDER HISTORY");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        add(title);
    }
}

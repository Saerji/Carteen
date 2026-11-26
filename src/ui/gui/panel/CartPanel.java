
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;

public class CartPanel extends JPanel {

    public CartPanel() {

        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("SHOPPING CART");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        add(title);
    }
}

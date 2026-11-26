
package ui.gui.action;

import javax.swing.*;
import java.awt.*;

public class NavigationHandler {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JFrame parentFrame;

    public NavigationHandler(
        CardLayout cardLayout,
        JPanel contentPanel,
        JFrame parentFrame
    ) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.parentFrame = parentFrame;
    }

    public void initializeActions(
        JButton menuBtn,
        JButton historyBtn,
        JButton cartBtn,
        JButton signOutBtn
    ) {

        menuBtn.addActionListener(e ->
            cardLayout.show(contentPanel, "MENU")
        );

        historyBtn.addActionListener(e ->
            cardLayout.show(contentPanel, "HISTORY")
        );

        cartBtn.addActionListener(e ->
            cardLayout.show(contentPanel, "CART")
        );

        signOutBtn.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                parentFrame,
                "Are you sure you want to sign out?",
                "Sign Out",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                parentFrame.dispose();
            }
        });
    }
}


package ui.gui;

import javax.swing.*;
import java.awt.*;

import ui.gui.navigation.NavigationBar;
import ui.gui.panel.PagePanel;
import ui.gui.action.NavigationHandler;

public class MainMenu_GUI extends JFrame {

    public MainMenu_GUI() {

        setTitle("Student Ordering System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // NAVIGATION BAR
        NavigationBar navigationBar = new NavigationBar();

        // CONTENT PANEL
        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);

        contentPanel.add(PagePanel.createPage("MENU PAGE"), "MENU");
        contentPanel.add(PagePanel.createPage("ORDER HISTORY"), "HISTORY");
        contentPanel.add(PagePanel.createPage("SHOPPING CART"), "CART");

        // NAVIGATION ACTIONS
        NavigationHandler navigationHandler =
            new NavigationHandler(cardLayout, contentPanel, this);

        navigationHandler.initializeActions(
            navigationBar.getMenuBtn(),
            navigationBar.getHistoryBtn(),
            navigationBar.getCartBtn(),
            navigationBar.getSignOutBtn()
        );

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(navigationBar.getNavBar(), BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainMenu_GUI mainMenuGUI = new MainMenu_GUI();
            mainMenuGUI.setVisible(true);
        });
    }
}

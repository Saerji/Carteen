package ui.gui.navigation;

import javax.swing.*;
import java.awt.*;

public class NavigationBar {

    private JPanel navBar;

    private JButton menuBtn;
    private JButton historyBtn;
    private JButton cartBtn;
    private JButton signOutBtn;

    public NavigationBar() {
        navBar = new JPanel(new BorderLayout());

        navBar.setBackground(new Color(190, 30, 45));
        navBar.setPreferredSize(new Dimension(0, 75));
        navBar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        createLogoSection();
        createNavigationButtons();
        createSignOutButton();
    }

    private void createLogoSection() {

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logoPanel.setOpaque(false);

        ImageIcon originalIcon = new ImageIcon(
            "resources/images/carteen-logo.png"
        );

        Image scaledImage = originalIcon.getImage()
            .getScaledInstance(180, 50, Image.SCALE_SMOOTH);

        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));

        logoPanel.add(logoLabel);

        navBar.add(logoPanel, BorderLayout.WEST);
    }

    private void createNavigationButtons() {

        JPanel centerNav = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 20, 10)
        );

        centerNav.setOpaque(false);

        menuBtn = createNavButton("MENU");
        cartBtn = createNavButton("CART");
        historyBtn = createNavButton("HISTORY");

        centerNav.add(menuBtn);
        centerNav.add(cartBtn);
        centerNav.add(historyBtn);

        navBar.add(centerNav, BorderLayout.CENTER);
    }

    private void createSignOutButton() {

        JPanel rightPanel = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 0, 10)
        );

        rightPanel.setOpaque(false);

        signOutBtn = createNavButton("SIGN OUT");

        rightPanel.add(signOutBtn);

        navBar.add(rightPanel, BorderLayout.EAST);
    }

    private JButton createNavButton(String text) {

        JButton button = new JButton(text);

        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public JPanel getNavBar() {
        return navBar;
    }

    public JButton getMenuBtn() {
        return menuBtn;
    }

    public JButton getHistoryBtn() {
        return historyBtn;
    }

    public JButton getCartBtn() {
        return cartBtn;
    }

    public JButton getSignOutBtn() {
        return signOutBtn;
    }
}

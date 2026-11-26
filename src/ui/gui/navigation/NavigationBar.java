
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

        createStudentSection();
        createNavigationButtons();
        createSignOutButton();
    }

    private void createStudentSection() {

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setOpaque(false);

        JLabel srCode = new JLabel("BSIT 25-00743");
        srCode.setForeground(Color.WHITE);
        srCode.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel studentName = new JLabel("Juan Dela Cruz");
        studentName.setForeground(Color.WHITE);
        studentName.setFont(new Font("Arial", Font.PLAIN, 12));

        studentPanel.add(srCode);
        studentPanel.add(studentName);

        navBar.add(studentPanel, BorderLayout.WEST);
    }

    private void createNavigationButtons() {

        JPanel centerNav = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 20, 10)
        );

        centerNav.setOpaque(false);

        menuBtn = createNavButton("MENU");
        historyBtn = createNavButton("HISTORY");
        cartBtn = createNavButton("CART");

        centerNav.add(menuBtn);
        centerNav.add(historyBtn);
        centerNav.add(cartBtn);

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

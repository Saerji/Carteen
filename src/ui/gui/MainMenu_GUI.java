
package ui.gui;

import javax.swing.*;
import java.awt.*;

public class MainMenu_GUI extends JFrame {

    public void initialize() {

        setTitle("Student Ordering System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());

        // TOP NAVIGATION BAR
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(new Color(190, 30, 45));
        navBar.setPreferredSize(new Dimension(0, 75));
        navBar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // LEFT SECTION - STUDENT INFORMATION
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

        // CENTER SECTION - NAVIGATION BUTTONS
        JPanel centerNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        centerNav.setOpaque(false);

        JButton menuBtn = createNavButton("MENU");
        JButton historyBtn = createNavButton("HISTORY");
        JButton cartBtn = createNavButton("CART");

        centerNav.add(menuBtn);
        centerNav.add(historyBtn);
        centerNav.add(cartBtn);

        // RIGHT SECTION - SIGN OUT
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        rightPanel.setOpaque(false);

        JButton signOutBtn = createNavButton("SIGN OUT");
        rightPanel.add(signOutBtn);

        // ADD SECTIONS TO NAVBAR
        navBar.add(studentPanel, BorderLayout.WEST);
        navBar.add(centerNav, BorderLayout.CENTER);
        navBar.add(rightPanel, BorderLayout.EAST);

        // CONTENT PANEL
        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);

        contentPanel.add(createPage("MENU PAGE"), "MENU");
        contentPanel.add(createPage("ORDER HISTORY"), "HISTORY");
        contentPanel.add(createPage("SHOPPING CART"), "CART");

        // NAVIGATION ACTIONS
        menuBtn.addActionListener(e ->
            cardLayout.show(contentPanel, "MENU"));

        historyBtn.addActionListener(e ->
            cardLayout.show(contentPanel, "HISTORY"));

        cartBtn.addActionListener(e ->
            cardLayout.show(contentPanel, "CART"));

        // SIGN OUT ACTION
        signOutBtn.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to sign out?",
                "Sign Out",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        // ADD COMPONENTS
        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    // NAVIGATION BUTTON DESIGN
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

    // PAGE DESIGN
    private JPanel createPage(String title) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(Color.DARK_GRAY);

        panel.add(label);

        return panel;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainMenu_GUI mainMenuGUI = new MainMenu_GUI();
            mainMenuGUI.initialize();
        });
    }
}

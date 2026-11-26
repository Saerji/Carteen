
package ui.gui.navigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigationBar {

    private static final Color RED = new Color(190, 30, 45);
    private static final Color DARK_RED = new Color(155, 20, 35);
    private static final Color WHITE = Color.WHITE;

    private JPanel navBar;

    private JButton menuBtn;
    private JButton historyBtn;
    private JButton cartBtn;
    private JButton signOutBtn;

    public NavigationBar() {

        navBar = new JPanel(new BorderLayout());

        navBar.setBackground(RED);
        navBar.setPreferredSize(new Dimension(0, 80));

        navBar.setBorder(
            BorderFactory.createEmptyBorder(10, 30, 10, 30)
        );

        createLogoSection();
        createNavigationButtons();
        createSignOutButton();
    }

    // LOGO SECTION
    private void createLogoSection() {

        JPanel logoPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 0, 0)
        );

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

    // NAVIGATION BUTTONS
    private void createNavigationButtons() {

        JPanel centerNav = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 12, 5)
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

    // CREATE NAV BUTTON
    private JButton createNavButton(String text) {

        NavButton button = new NavButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setForeground(WHITE);

        button.setPreferredSize(new Dimension(110, 42));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // SIGN OUT BUTTON
    private void createSignOutButton() {

        JPanel rightPanel = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 0, 5)
        );

        rightPanel.setOpaque(false);

        signOutBtn = new JButton();

        ImageIcon originalIcon = new ImageIcon(
            "resources/icons/sign_out.png"
        );

        Image scaledImage = originalIcon.getImage()
            .getScaledInstance(28, 28, Image.SCALE_SMOOTH);

        signOutBtn.setIcon(new ImageIcon(scaledImage));

        signOutBtn.setBackground(RED);
        signOutBtn.setPreferredSize(new Dimension(45, 42));

        signOutBtn.setFocusPainted(false);
        signOutBtn.setBorderPainted(false);
        signOutBtn.setContentAreaFilled(true);
        signOutBtn.setOpaque(true);

        signOutBtn.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        signOutBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                signOutBtn.setBackground(DARK_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signOutBtn.setBackground(RED);
            }
        });

        rightPanel.add(signOutBtn);

        navBar.add(rightPanel, BorderLayout.EAST);
    }

    // SET ACTIVE NAVIGATION BUTTON
    public void setActiveButton(JButton selectedButton) {

        menuBtn.setSelected(menuBtn == selectedButton);
        cartBtn.setSelected(cartBtn == selectedButton);
        historyBtn.setSelected(historyBtn == selectedButton);

        menuBtn.repaint();
        cartBtn.repaint();
        historyBtn.repaint();
    }

    // GETTERS
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

    // CUSTOM ROUNDED NAVIGATION BUTTON
    private class NavButton extends JButton {

        private boolean hovered = false;

        public NavButton(String text) {

            super(text);

            setOpaque(false);

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            if (isSelected() || hovered) {

                g2.setColor(WHITE);

                g2.fillRoundRect(
                    0, 0, getWidth(), getHeight(),
                    18, 18
                );

                setForeground(RED);

            } else {

                g2.setColor(RED);

                g2.fillRoundRect(
                    0, 0, getWidth(), getHeight(),
                    18, 18
                );

                setForeground(WHITE);
            }

            g2.dispose();

            super.paintComponent(g);
        }
    }
}

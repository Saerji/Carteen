
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import javax.swing.ImageIcon;
import java.awt.Image;

import model.CartItem;
import service.CartService;

public class CartPanel extends JPanel {

    private static final Color RED = new Color(190, 30, 45);
    private static final Color DARK_RED = new Color(150, 20, 35);
    private static final Color LIGHT_GRAY = new Color(248, 248, 248);
    private static final Color BORDER_GRAY = new Color(225, 225, 225);
    private static final Color TEXT_GRAY = new Color(110, 110, 110);

    private final CartService cartService;

    private JPanel cartItemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;

    public CartPanel(CartService cartService) {

        this.cartService = cartService;

        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(
                25, 35, 25, 35));

        // =========================
        // HEADER
        // =========================

        JPanel header = new JPanel();

        header.setLayout(
                new BoxLayout(header, BoxLayout.Y_AXIS));

        header.setBackground(Color.WHITE);

        JLabel title = new JLabel("SHOPPING CART");

        title.setFont(
                new Font("Arial", Font.BOLD, 28));

        title.setForeground(RED);

        JLabel subtitle = new JLabel(
                "Review your selected products before checkout.");

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 13));

        subtitle.setForeground(TEXT_GRAY);

        header.add(title);
        header.add(Box.createVerticalStrut(7));
        header.add(subtitle);

        add(header, BorderLayout.NORTH);

        // =========================
        // CART ITEMS
        // =========================

        cartItemsPanel = new JPanel();

        cartItemsPanel.setLayout(
                new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

        cartItemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);

        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);

        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        // =========================
        // FOOTER
        // =========================

        JPanel footer = new JPanel(new BorderLayout(15, 0));

        footer.setBackground(LIGHT_GRAY);

        footer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_GRAY),
                        BorderFactory.createEmptyBorder(
                                18, 20, 18, 20)));

        JPanel totalPanel = new JPanel();

        totalPanel.setLayout(
                new BoxLayout(totalPanel, BoxLayout.Y_AXIS));

        totalPanel.setOpaque(false);

        JLabel totalTitle = new JLabel("ORDER TOTAL");

        totalTitle.setFont(
                new Font("Arial", Font.BOLD, 12));

        totalTitle.setForeground(TEXT_GRAY);

        totalLabel = new JLabel();

        totalLabel.setFont(
                new Font("Arial", Font.BOLD, 25));

        totalLabel.setForeground(RED);

        totalPanel.add(totalTitle);
        totalPanel.add(Box.createVerticalStrut(5));
        totalPanel.add(totalLabel);

        checkoutButton = new RoundedButton(
                "CHECKOUT",
                RED,
                DARK_RED,
                Color.WHITE,
                Color.WHITE,
                18
        );

        checkoutButton.setFont(
                new Font("Arial", Font.BOLD, 14));

        checkoutButton.setPreferredSize(
                new Dimension(170, 48));

        footer.add(totalPanel, BorderLayout.WEST);
        footer.add(checkoutButton, BorderLayout.EAST);

        add(footer, BorderLayout.SOUTH);

        refreshCart();
    }

    // =========================
    // CONNECT CHECKOUT ACTION
    // =========================

    public void setCheckoutAction(Runnable checkoutAction) {

        checkoutButton.addActionListener(
                e -> checkoutAction.run());
    }

    // =========================
    // REFRESH CART DISPLAY
    // =========================

    public void refreshCart() {

        cartItemsPanel.removeAll();

        List<CartItem> items = cartService.getCartItems();

        if (items.isEmpty()) {

            // EMPTY CART MESSAGE

            JPanel emptyPanel = new JPanel();

            emptyPanel.setLayout(
                    new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));

            emptyPanel.setBackground(Color.WHITE);

            ImageIcon emptyCartIcon = new ImageIcon("resources/icons/empty_cart.png");

            Image scaledImage = emptyCartIcon.getImage().getScaledInstance(
                    100,
                    100,
                    Image.SCALE_SMOOTH
            );

            JLabel emptyIcon = new JLabel(new ImageIcon(scaledImage));

            emptyIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel emptyTitle = new JLabel("Your cart is empty");

            emptyTitle.setFont(
                    new Font("Arial", Font.BOLD, 20));

            emptyTitle.setForeground(Color.DARK_GRAY);

            emptyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel emptySubtitle = new JLabel(
                    "Add some products from the shops!");

            emptySubtitle.setFont(
                    new Font("Arial", Font.PLAIN, 14));

            emptySubtitle.setForeground(TEXT_GRAY);

            emptySubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            emptyPanel.add(Box.createVerticalStrut(70));
            emptyPanel.add(emptyIcon);
            emptyPanel.add(Box.createVerticalStrut(12));
            emptyPanel.add(emptyTitle);
            emptyPanel.add(Box.createVerticalStrut(7));
            emptyPanel.add(emptySubtitle);

            emptyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            cartItemsPanel.add(emptyPanel);

            checkoutButton.setEnabled(false);

        } else {

            checkoutButton.setEnabled(true);

            for (CartItem item : items) {

                // =========================
                // CART ITEM CARD
                // =========================

                RoundedPanel itemPanel = new RoundedPanel();

                itemPanel.setLayout(
                        new BorderLayout(20, 0));

                itemPanel.setBackground(LIGHT_GRAY);

                itemPanel.setBorder(
                        BorderFactory.createEmptyBorder(
                                15, 20, 15, 20));

                // =========================
                // LEFT SIDE - PRODUCT DETAILS
                // =========================

                JPanel details = new JPanel();

                details.setLayout(
                        new BoxLayout(details, BoxLayout.Y_AXIS));

                details.setOpaque(false);

                // PRODUCT NAME

                JLabel name = new JLabel(
                        item.getProduct().getName());

                name.setFont(
                        new Font("Arial", Font.BOLD, 17));

                name.setForeground(Color.DARK_GRAY);

                name.setAlignmentX(Component.LEFT_ALIGNMENT);

                // QUANTITY PANEL

                JPanel quantityPanel = new JPanel(
                        new FlowLayout(FlowLayout.LEFT, 7, 0));

                quantityPanel.setOpaque(false);

                quantityPanel.setAlignmentX(
                        Component.LEFT_ALIGNMENT);

                JLabel quantityTitle = new JLabel("Quantity:");

                quantityTitle.setFont(
                        new Font("Arial", Font.PLAIN, 13));

                quantityTitle.setForeground(TEXT_GRAY);

                JLabel quantityLabel = new JLabel(
                        String.valueOf(item.getQuantity()));

                quantityLabel.setFont(
                        new Font("Arial", Font.BOLD, 14));

                quantityLabel.setHorizontalAlignment(
                        SwingConstants.CENTER);

                quantityLabel.setPreferredSize(
                        new Dimension(25, 32));

                // MINUS BUTTON

                RoundedButton minusButton = new RoundedButton(
                        "-",
                        new Color(230, 230, 230),
                        new Color(210, 210, 210),
                        Color.DARK_GRAY,
                        Color.DARK_GRAY,
                        20
                );

                minusButton.setFont(
                        new Font("Arial", Font.BOLD, 18));

                minusButton.setPreferredSize(new Dimension(40, 38));

                minusButton.addActionListener(e -> {

                    cartService.decreaseProductQuantity(
                            item.getProduct());

                    refreshCart();
                });

                // PLUS BUTTON

                RoundedButton plusButton = new RoundedButton(
                        "+",
                        new Color(230, 230, 230),
                        new Color(210, 210, 210),
                        Color.DARK_GRAY,
                        Color.DARK_GRAY,
                        20
                );

                plusButton.setFont(
                        new Font("Arial", Font.BOLD, 18));

                plusButton.setPreferredSize(new Dimension(40, 38));

                plusButton.addActionListener(e -> {

                    cartService.increaseProductQuantity(
                            item.getProduct());

                    refreshCart();
                });

                quantityPanel.add(quantityTitle);
                quantityPanel.add(minusButton);
                quantityPanel.add(quantityLabel);
                quantityPanel.add(plusButton);

                // UNIT PRICE

                JLabel unitPrice = new JLabel(
                        String.format(
                                "Unit Price: ₱%.2f",
                                item.getProduct().getPrice()));

                unitPrice.setFont(
                        new Font("Arial", Font.PLAIN, 12));

                unitPrice.setForeground(TEXT_GRAY);

                unitPrice.setAlignmentX(
                        Component.LEFT_ALIGNMENT);

                // ADD LEFT COMPONENTS

                details.add(name);
                details.add(Box.createVerticalStrut(10));
                details.add(quantityPanel);
                details.add(Box.createVerticalStrut(6));
                details.add(unitPrice);

                // =========================
                // RIGHT SIDE - SUBTOTAL
                // AND REMOVE BUTTON
                // =========================

                JPanel rightPanel = new JPanel(
                        new GridBagLayout());

                rightPanel.setOpaque(false);

                GridBagConstraints gbc = new GridBagConstraints();

                gbc.gridx = 0;
                gbc.anchor = GridBagConstraints.NORTHEAST;
                gbc.fill = GridBagConstraints.NONE;
                gbc.weightx = 1;
                gbc.insets = new Insets(0, 0, 12, 0);

                // SUBTOTAL

                JLabel subtotal = new JLabel(
                        String.format(
                                "₱%.2f",
                                item.getSubtotal()));

                subtotal.setFont(
                        new Font("Arial", Font.BOLD, 19));

                subtotal.setForeground(RED);

                gbc.gridy = 0;

                rightPanel.add(subtotal, gbc);

                // REMOVE BUTTON

                RoundedButton removeButton = new RoundedButton(
                        "REMOVE",
                        new Color(255, 235, 238),
                        new Color(255, 210, 215),
                        RED,
                        DARK_RED,
                        12
                );

                removeButton.setFont(
                        new Font("Arial", Font.BOLD, 11));

                removeButton.setPreferredSize(
                        new Dimension(95, 35));

                gbc.gridy = 1;
                gbc.insets = new Insets(0, 0, 0, 0);

                rightPanel.add(removeButton, gbc);

                removeButton.addActionListener(e -> {

                    cartService.removeProduct(
                            item.getProduct());

                    refreshCart();
                });

                // =========================
                // ADD COMPONENTS TO CARD
                // =========================

                itemPanel.add(details, BorderLayout.CENTER);
                itemPanel.add(rightPanel, BorderLayout.EAST);

                // CONSISTENT CARD SIZE

                itemPanel.setPreferredSize(
                        new Dimension(700, 120));

                itemPanel.setMinimumSize(
                        new Dimension(300, 120));

                itemPanel.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, 120));

                itemPanel.setAlignmentX(
                        Component.LEFT_ALIGNMENT);

                cartItemsPanel.add(itemPanel);

                cartItemsPanel.add(
                        Box.createVerticalStrut(15));
            }
        }

        // =========================
        // UPDATE TOTAL
        // =========================

        totalLabel.setText(
                String.format(
                        "₱%.2f",
                        cartService.getTotalPrice()));

        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
    }

    // =========================
    // ROUNDED PANEL
    // =========================

    private static class RoundedPanel extends JPanel {

        public RoundedPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());

            g2.fill(new RoundRectangle2D.Float(
                    0, 0,
                    getWidth(),
                    getHeight(),
                    22, 22));

            g2.setColor(BORDER_GRAY);

            g2.draw(new RoundRectangle2D.Float(
                    1, 1,
                    getWidth() - 2,
                    getHeight() - 2,
                    22, 22));

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =========================
    // ROUNDED BUTTON WITH HOVER
    // =========================

    private static class RoundedButton extends JButton {

        private final Color normalColor;
        private final Color hoverColor;
        private final Color normalText;
        private final Color hoverText;
        private final int cornerRadius;

        private boolean hovered = false;

        public RoundedButton(
                String text,
                Color normalColor,
                Color hoverColor,
                Color normalText,
                Color hoverText,
                int cornerRadius) {

            super(text);

            this.normalColor = normalColor;
            this.hoverColor = hoverColor;
            this.normalText = normalText;
            this.hoverText = hoverText;
            this.cornerRadius = cornerRadius;

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);

            setForeground(normalText);

            setCursor(new Cursor(Cursor.HAND_CURSOR));

            setBorder(BorderFactory.createEmptyBorder(
                    8, 15, 8, 15));

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {

                    hovered = true;

                    setForeground(
                            RoundedButton.this.hoverText);

                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {

                    hovered = false;

                    setForeground(
                            RoundedButton.this.normalText);

                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(
                    hovered ? hoverColor : normalColor);

            g2.fill(new RoundRectangle2D.Float(
                    0, 0,
                    getWidth(),
                    getHeight(),
                    cornerRadius,
                    cornerRadius));

            g2.dispose();

            super.paintComponent(g);
        }
    }
}

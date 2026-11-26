
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import model.Product;
import model.Shop;
import service.MenuService;
import service.CartService;

public class ShopMenuPanel extends JPanel {

    private static final Color RED = new Color(190, 30, 45);
    private static final Color DARK_RED = new Color(150, 20, 35);
    private static final Color LIGHT_GRAY = new Color(248, 248, 248);
    private static final Color BORDER_GRAY = new Color(225, 225, 225);
    private static final Color TEXT_GRAY = new Color(110, 110, 110);

    public ShopMenuPanel(
            MenuService menuService,
            Shop shop,
            CartService cartService,
            Runnable backAction) {

        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(
                25, 35, 25, 35));

        // =========================
        // HEADER
        // =========================

        JPanel header = new JPanel(new BorderLayout(20, 0));
        header.setBackground(Color.WHITE);

        RoundedButton backButton = new RoundedButton(
                "← BACK TO SHOPS",
                new Color(240, 240, 240),
                new Color(225, 225, 225),
                Color.DARK_GRAY,
                Color.DARK_GRAY,
                12
        );

        backButton.setPreferredSize(new Dimension(155, 40));
        backButton.addActionListener(e -> backAction.run());

        JLabel shopTitle = new JLabel(
                shop.name().replace("_", " "));

        shopTitle.setFont(new Font("Arial", Font.BOLD, 26));
        shopTitle.setForeground(RED);

        header.add(backButton, BorderLayout.WEST);
        header.add(shopTitle, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        // =========================
        // PRODUCT GRID
        // =========================

        JPanel productGrid = new JPanel(
                new GridLayout(0, 3, 20, 20));

        productGrid.setBackground(Color.WHITE);

        List<Product> products =
                menuService.getProductsByShop(shop);

        for (Product product : products) {

            // PRODUCT CARD
            RoundedPanel productCard = new RoundedPanel();

            productCard.setLayout(
                    new BoxLayout(productCard, BoxLayout.Y_AXIS));

            productCard.setBackground(LIGHT_GRAY);

            productCard.setBorder(
                    BorderFactory.createEmptyBorder(
                            20, 18, 20, 18));

            // PRODUCT NAME
            JLabel productName = new JLabel(product.getName());

            productName.setFont(
                    new Font("Arial", Font.BOLD, 17));

            productName.setForeground(Color.DARK_GRAY);
            productName.setAlignmentX(Component.CENTER_ALIGNMENT);

            // CATEGORY
            JLabel category = new JLabel(
                    "Category: " + product.getCategory());

            category.setFont(
                    new Font("Arial", Font.PLAIN, 12));

            category.setForeground(TEXT_GRAY);
            category.setAlignmentX(Component.CENTER_ALIGNMENT);

            // PRICE
            JLabel price = new JLabel(
                    String.format("₱%.2f", product.getPrice()));

            price.setFont(
                    new Font("Arial", Font.BOLD, 21));

            price.setForeground(RED);
            price.setAlignmentX(Component.CENTER_ALIGNMENT);

            // =========================
            // QUANTITY SELECTOR
            // =========================

            int[] quantity = {1};

            JPanel quantityPanel = new JPanel(
                    new FlowLayout(FlowLayout.CENTER, 12, 0));

            quantityPanel.setBackground(LIGHT_GRAY);
            quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel quantityLabel = new JLabel("1");

            quantityLabel.setFont(
                    new Font("Arial", Font.BOLD, 16));

            quantityLabel.setHorizontalAlignment(
                    SwingConstants.CENTER);

            quantityLabel.setPreferredSize(
                    new Dimension(30, 30));

                RoundedButton minusButton = new RoundedButton(
                        "-",
                        new Color(230, 230, 230),
                        new Color(210, 210, 210),
                        Color.DARK_GRAY,
                        Color.DARK_GRAY,
                        20
                );

                RoundedButton plusButton = new RoundedButton(
                        "+",
                        new Color(230, 230, 230),
                        new Color(210, 210, 210),
                        Color.DARK_GRAY,
                        Color.DARK_GRAY,
                        20
                );

                minusButton.setFont(new Font("Arial", Font.BOLD, 18));
                plusButton.setFont(new Font("Arial", Font.BOLD, 18));

                minusButton.setPreferredSize(new Dimension(40, 38));
                plusButton.setPreferredSize(new Dimension(40, 38));

                minusButton.setMinimumSize(new Dimension(40, 38));
                plusButton.setMinimumSize(new Dimension(40, 38));

            minusButton.addActionListener(e -> {

                if (quantity[0] > 1) {
                    quantity[0]--;
                    quantityLabel.setText(
                            String.valueOf(quantity[0]));
                }
            });

            plusButton.addActionListener(e -> {

                quantity[0]++;
                quantityLabel.setText(
                        String.valueOf(quantity[0]));
            });

            quantityPanel.add(minusButton);
            quantityPanel.add(quantityLabel);
            quantityPanel.add(plusButton);

            // =========================
            // ADD TO CART BUTTON
            // =========================

            RoundedButton addButton = new RoundedButton(
                    "ADD TO CART",
                    RED,
                    DARK_RED,
                    Color.WHITE,
                    Color.WHITE,
                    18
            );

            addButton.setFont(
                    new Font("Arial", Font.BOLD, 13));

            addButton.setPreferredSize(
                    new Dimension(180, 42));

            addButton.setMaximumSize(
                    new Dimension(180, 42));

            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            addButton.addActionListener(e -> {

                int selectedQuantity = quantity[0];

                // Add the selected quantity to the cart.
                for (int i = 0; i < selectedQuantity; i++) {
                    cartService.addProduct(product);
                    cartService.logAdd(product);
                }

                JOptionPane.showMessageDialog(
                        this,
                        selectedQuantity + " x "
                                + product.getName()
                                + " added to cart!",
                        "Added to Cart",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Reset quantity after adding.
                quantity[0] = 1;
                quantityLabel.setText("1");
            });

            // =========================
            // ADD COMPONENTS TO CARD
            // =========================

            productCard.add(productName);
            productCard.add(Box.createVerticalStrut(8));

            productCard.add(category);
            productCard.add(Box.createVerticalStrut(15));

            productCard.add(price);
            productCard.add(Box.createVerticalStrut(18));

            productCard.add(quantityPanel);
            productCard.add(Box.createVerticalStrut(18));

            productCard.add(addButton);

            productGrid.add(productCard);
        }

        // =========================
        // SCROLL PANE
        // =========================

        JScrollPane scrollPane = new JScrollPane(productGrid);

        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    // =========================
    // ROUNDED PRODUCT CARD
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

            g2.setStroke(new BasicStroke(1f));

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

            setFont(new Font("Arial", Font.BOLD, 12));

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
                    setForeground(RoundedButton.this.hoverText);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    setForeground(RoundedButton.this.normalText);
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

            g2.setColor(hovered ? hoverColor : normalColor);

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

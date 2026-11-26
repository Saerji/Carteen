
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.CartItem;
import service.CartService;

public class CartPanel extends JPanel {

    private static final Color RED = new Color(190, 30, 45);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);

    private final CartService cartService;

    private JPanel cartItemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;

    public CartPanel(CartService cartService) {

        this.cartService = cartService;

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(
                25, 35, 25, 35));

        // HEADER
        JLabel title = new JLabel("SHOPPING CART");

        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(RED);

        add(title, BorderLayout.NORTH);

        // CART ITEMS
        cartItemsPanel = new JPanel();

        cartItemsPanel.setLayout(
                new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));

        cartItemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);

        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel(new BorderLayout());

        footer.setBackground(Color.WHITE);

        totalLabel = new JLabel();

        totalLabel.setFont(
                new Font("Arial", Font.BOLD, 22));

        totalLabel.setForeground(RED);

        checkoutButton = new JButton("CHECKOUT");

        checkoutButton.setBackground(RED);
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFocusPainted(false);
        checkoutButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        footer.add(totalLabel, BorderLayout.WEST);
        footer.add(checkoutButton, BorderLayout.EAST);

        add(footer, BorderLayout.SOUTH);

        refreshCart();
    }

    // CONNECT CHECKOUT ACTION
    public void setCheckoutAction(Runnable checkoutAction) {

        checkoutButton.addActionListener(
                e -> checkoutAction.run());
    }

    // REFRESH CART DISPLAY
    public void refreshCart() {

        cartItemsPanel.removeAll();

        List<CartItem> items = cartService.getCartItems();

        if (items.isEmpty()) {

            JLabel emptyLabel = new JLabel(
                    "Your cart is empty.");

            emptyLabel.setFont(
                    new Font("Arial", Font.PLAIN, 18));

            emptyLabel.setForeground(Color.GRAY);

            cartItemsPanel.add(emptyLabel);

        } else {

            for (CartItem item : items) {

                JPanel itemPanel = new JPanel(
                        new BorderLayout(15, 10));

                itemPanel.setBackground(LIGHT_GRAY);

                itemPanel.setBorder(
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15));

                JLabel name = new JLabel(
                        item.getProduct().getName());

                JLabel quantity = new JLabel(
                        "Quantity: " + item.getQuantity());

                JLabel subtotal = new JLabel(
                        String.format(
                                "₱%.2f",
                                item.getSubtotal()));

                JButton removeButton = new JButton("REMOVE");

                removeButton.addActionListener(e -> {

                    cartService.removeProduct(
                            item.getProduct());

                    refreshCart();
                });

                JPanel details = new JPanel();

                details.setLayout(
                        new BoxLayout(details, BoxLayout.Y_AXIS));

                details.setOpaque(false);

                details.add(name);
                details.add(quantity);

                itemPanel.add(details, BorderLayout.CENTER);
                itemPanel.add(subtotal, BorderLayout.EAST);
                itemPanel.add(removeButton, BorderLayout.SOUTH);

                itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                cartItemsPanel.add(itemPanel);

                cartItemsPanel.add(
                        Box.createVerticalStrut(10));
            }
        }

        totalLabel.setText(
                String.format(
                        "TOTAL: ₱%.2f",
                        cartService.getTotalPrice()));

        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
    }
}

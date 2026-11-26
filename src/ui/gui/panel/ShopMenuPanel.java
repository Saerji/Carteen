
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.Product;
import model.Shop;
import service.MenuService;
import service.CartService;

public class ShopMenuPanel extends JPanel {

    private static final Color RED = new Color(190, 30, 45);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);

    public ShopMenuPanel(
            MenuService menuService,
            Shop shop,
            CartService cartService,
            Runnable backAction) {

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(
                25, 35, 25, 35));

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);

        JButton backButton = new JButton("← BACK TO SHOPS");
        backButton.addActionListener(e -> backAction.run());

        JLabel shopTitle = new JLabel(
                shop.name().replace("_", " "));

        shopTitle.setFont(new Font("Arial", Font.BOLD, 26));
        shopTitle.setForeground(RED);

        header.add(backButton, BorderLayout.WEST);
        header.add(shopTitle, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        // PRODUCT GRID
        JPanel productGrid = new JPanel(
                new GridLayout(0, 3, 18, 18));

        productGrid.setBackground(Color.WHITE);

        List<Product> products =
                menuService.getProductsByShop(shop);

        for (Product product : products) {

            JPanel productCard = new JPanel();

            productCard.setLayout(
                    new BoxLayout(productCard, BoxLayout.Y_AXIS));

            productCard.setBackground(LIGHT_GRAY);

            productCard.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(220, 220, 220)),
                            BorderFactory.createEmptyBorder(
                                    15, 15, 15, 15)));

            JLabel productName = new JLabel(product.getName());

            productName.setFont(
                    new Font("Arial", Font.BOLD, 15));

            JLabel category = new JLabel(
                    "Category: " + product.getCategory());

            JLabel price = new JLabel(
                    String.format("₱%.2f", product.getPrice()));

            price.setFont(
                    new Font("Arial", Font.BOLD, 17));

            price.setForeground(RED);

            JButton addButton = new JButton("ADD TO CART");

            addButton.setBackground(RED);
            addButton.setForeground(Color.WHITE);
            addButton.setFocusPainted(false);
            addButton.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));

            addButton.addActionListener(e -> {

                cartService.addProduct(product);
                cartService.logAdd(product);

                JOptionPane.showMessageDialog(
                        this,
                        product.getName() + " added to cart!",
                        "Added to Cart",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            productCard.add(productName);
            productCard.add(Box.createVerticalStrut(10));
            productCard.add(category);
            productCard.add(Box.createVerticalStrut(10));
            productCard.add(price);
            productCard.add(Box.createVerticalStrut(15));
            productCard.add(addButton);

            productGrid.add(productCard);
        }

        JScrollPane scrollPane = new JScrollPane(productGrid);

        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }
}

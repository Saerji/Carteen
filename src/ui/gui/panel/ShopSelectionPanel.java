
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

import model.Product;
import model.Shop;
import service.MenuService;

public class ShopSelectionPanel extends JPanel {

    private static final Color RED = new Color(190, 30, 45);

    public ShopSelectionPanel(
            MenuService menuService,
            Consumer<Shop> shopAction) {

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JLabel title = new JLabel("SELECT A SHOP");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(RED);

        JLabel subtitle = new JLabel(
                "Choose a shop to browse its available products.");

        JPanel heading = new JPanel();
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.setBackground(Color.WHITE);

        heading.add(title);
        heading.add(Box.createVerticalStrut(8));
        heading.add(subtitle);

        add(heading, BorderLayout.NORTH);

        JPanel shopGrid = new JPanel(
                new GridLayout(0, 3, 20, 20));

        shopGrid.setBackground(Color.WHITE);

        for (Shop shop : Shop.values()) {

            List<Product> products =
                    menuService.getProductsByShop(shop);

            if (products.isEmpty()) {
                continue;
            }

            JButton shopButton = new JButton(
                    "<html><center><b>"
                    + getShopName(shop)
                    + "</b><br><br>"
                    + products.size()
                    + " Available Products</center></html>");

            shopButton.setFont(new Font("Arial", Font.PLAIN, 16));
            shopButton.setForeground(Color.WHITE);
            shopButton.setBackground(RED);
            shopButton.setFocusPainted(false);
            shopButton.setBorder(BorderFactory.createEmptyBorder(
                    25, 15, 25, 15));
            shopButton.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));

            shopButton.addActionListener(e ->
                    shopAction.accept(shop));

            shopGrid.add(shopButton);
        }

        JScrollPane scrollPane = new JScrollPane(shopGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private String getShopName(Shop shop) {

        switch (shop) {
            case TENDER_JUICY: return "Tender Juicy Stall";
            case FRUIT_JUICE: return "Fruit Juice";
            case SPOT_G: return "Spot G";
            case CHOWKING: return "Chowking";
            case CAFE_AROMATIKO: return "Cafe Aromatiko";
            case POTATO_CORNER: return "Potato Corner";
            case HAPPY_HAUS: return "Happy Haus";
            case THE_EATERY: return "The Eatery";
            case JULIES_BAKESHOP: return "Julie's Bakeshop";
            case WAFFLE_TIME: return "Waffle Time";
            case RC_BEEF_SHAWARMA: return "RC Beef Shawarma";
            case RR_SORELLAS: return "RR Sorellas";
            case FRIED_NOODLES_HAUS: return "Fried Noodles Haus";
            default: return shop.name();
        }
    }
}

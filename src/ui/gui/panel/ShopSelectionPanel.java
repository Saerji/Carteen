
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.function.Consumer;

import model.Product;
import model.Shop;
import service.MenuService;

public class ShopSelectionPanel extends JPanel {

    private static final Color RED = new Color(190, 30, 45);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color BORDER_GRAY = new Color(220, 220, 220);

    public ShopSelectionPanel(
            MenuService menuService,
            Consumer<Shop> shopAction) {

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        // HEADER
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

        // SHOP GRID
        JPanel shopGrid = new JPanel(
                new GridLayout(0, 3, 20, 20));

        shopGrid.setBackground(Color.WHITE);
        shopGrid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (Shop shop : Shop.values()) {

            List<Product> products =
                    menuService.getProductsByShop(shop);

            if (products.isEmpty()) {
                continue;
            }

            // CUSTOM ROUNDED CARD
            RoundedShopButton shopButton = new RoundedShopButton();

            shopButton.setLayout(
                    new BoxLayout(shopButton, BoxLayout.Y_AXIS));

            shopButton.setBackground(LIGHT_GRAY);
            shopButton.setHoverBackground(RED);

            shopButton.setBorder(
                    BorderFactory.createEmptyBorder(15, 15, 15, 15));

            // SHOP IMAGE
            String imagePath = getShopImage(shop);

            if (imagePath != null) {

                ImageIcon icon = new ImageIcon(imagePath);

                if (icon.getIconWidth() > 0) {

                    int originalWidth = icon.getIconWidth();
                    int originalHeight = icon.getIconHeight();

                    int targetWidth = 220;
                    int targetHeight = 130;

                    double scale = Math.min(
                            (double) targetWidth / originalWidth,
                            (double) targetHeight / originalHeight);

                    int newWidth = (int) (originalWidth * scale);
                    int newHeight = (int) (originalHeight * scale);

                    Image image = icon.getImage().getScaledInstance(
                            newWidth,
                            newHeight,
                            Image.SCALE_SMOOTH);

                    JLabel imageLabel =
                            new JLabel(new ImageIcon(image));

                    imageLabel.setAlignmentX(
                            Component.CENTER_ALIGNMENT);

                    shopButton.add(imageLabel);

                } else {
                    JLabel noImage = new JLabel("Image not found");
                    noImage.setAlignmentX(Component.CENTER_ALIGNMENT);
                    shopButton.add(noImage);
                }
            }

            // SHOP NAME
            JLabel shopName = new JLabel(getShopName(shop));

            shopName.setFont(
                    new Font("Arial", Font.BOLD, 17));

            shopName.setForeground(Color.BLACK);

            shopName.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            // PRODUCT COUNT
            JLabel productCount = new JLabel(
                    products.size() + " Available Products");

            productCount.setFont(
                    new Font("Arial", Font.PLAIN, 12));

            productCount.setForeground(Color.GRAY);

            productCount.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            shopButton.add(Box.createVerticalStrut(10));
            shopButton.add(shopName);
            shopButton.add(Box.createVerticalStrut(5));
            shopButton.add(productCount);

            // HOVER TEXT EFFECT
            shopButton.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    shopName.setForeground(Color.WHITE);
                    productCount.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    shopName.setForeground(Color.BLACK);
                    productCount.setForeground(Color.GRAY);
                }
            });

            // NAVIGATION
            shopButton.addActionListener(e ->
                    shopAction.accept(shop));

            shopGrid.add(shopButton);
        }

        // SCROLL PANE
        JScrollPane scrollPane = new JScrollPane(shopGrid);

        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    // CUSTOM ROUNDED BUTTON
    private static class RoundedShopButton extends JButton {

        private Color hoverBackground = RED;
        private boolean hovered = false;

        public RoundedShopButton() {

            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);

            setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        public void setHoverBackground(Color color) {
            hoverBackground = color;
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            Color background = hovered
                    ? hoverBackground
                    : LIGHT_GRAY;

            g2.setColor(background);

            g2.fill(new RoundRectangle2D.Float(
                    0, 0,
                    getWidth(),
                    getHeight(),
                    25, 25));

            g2.setColor(hovered ? RED : BORDER_GRAY);

            g2.setStroke(new BasicStroke(1.5f));

            g2.draw(new RoundRectangle2D.Float(
                    1, 1,
                    getWidth() - 2,
                    getHeight() - 2,
                    25, 25));

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // GET SHOP IMAGE
    private String getShopImage(Shop shop) {

        switch (shop) {

            case TENDER_JUICY:
                return "resources/images/tender_juicy.png";

            case FRUIT_JUICE:
                return "resources/images/fruit_juice.png";

            case SPOT_G:
                return "resources/images/spot_g.png";

            case CHOWKING:
                return "resources/images/chowking.png";

            case CAFE_AROMATIKO:
                return "resources/images/cafe_aromatiko.jpg";

            case POTATO_CORNER:
                return "resources/images/potato_corner.png";

            case JULIES_BAKESHOP:
                return "resources/images/julies_bakeshop.png";

            case WAFFLE_TIME:
                return "resources/images/waffle_time.png";

            case RC_BEEF_SHAWARMA:
                return "resources/images/rc_beef_shawarma.png";

            case RR_SORELLAS:
                return "resources/images/rr_sorellas.png";

            case FRIED_NOODLES_HAUS:
                return "resources/images/fried_noodles_haus.png";

            default:
                return null;
        }
    }

    // GET SHOP NAME
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

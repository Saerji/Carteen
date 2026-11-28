
package ui.gui.panel;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Order;
import model.OrderItem;
import service.HistoryService;

public class HistoryPanel extends JPanel {

    private static final Color RED = new Color(190, 30, 45);
    private static final Color DARK_RED = new Color(155, 20, 35);
    private static final Color LIGHT_GRAY = new Color(248, 248, 248);
    private static final Color BORDER_GRAY = new Color(230, 230, 230);
    private static final Color TEXT_GRAY = new Color(120, 120, 120);

    private final HistoryService historyService;

    private JPanel historyItemsPanel;

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("MMM dd, yyyy - hh:mm a");

    public HistoryPanel(HistoryService historyService) {

        this.historyService = historyService;

        setLayout(new BorderLayout(0, 20));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(
                30, 40, 30, 40));

        createHeader();
        createHistorySection();

        refreshHistory();
    }

    // HEADER
    private void createHeader() {

        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(
                new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        headerPanel.setOpaque(false);

        JLabel title = new JLabel("ORDER HISTORY");

        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(RED);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "View and keep track of your previous orders.");

        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(TEXT_GRAY);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(6));
        headerPanel.add(subtitle);

        add(headerPanel, BorderLayout.NORTH);
    }

    // HISTORY SECTION
    private void createHistorySection() {

        historyItemsPanel = new JPanel();

        historyItemsPanel.setLayout(
                new BoxLayout(historyItemsPanel, BoxLayout.Y_AXIS));

        historyItemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(historyItemsPanel);

        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    // REFRESH HISTORY
    public void refreshHistory() {

        historyItemsPanel.removeAll();

        List<Order> orders = historyService.getOrders();

        if (orders.isEmpty()) {

            showEmptyHistory();

        } else {

            // DISPLAY NEWEST ORDERS FIRST
            for (int i = orders.size() - 1; i >= 0; i--) {

                Order order = orders.get(i);

                JPanel orderCard = createOrderCard(order);

                historyItemsPanel.add(orderCard);

                historyItemsPanel.add(
                        Box.createVerticalStrut(18));
            }
        }

        historyItemsPanel.revalidate();
        historyItemsPanel.repaint();
    }

    // CREATE ORDER CARD
    private JPanel createOrderCard(Order order) {

        RoundedPanel card = new RoundedPanel(
                20, Color.WHITE, BORDER_GRAY);

        card.setLayout(new BorderLayout(0, 15));

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 20, 25));

        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ORDER HEADER
        JPanel orderHeader = new JPanel(
                new BorderLayout());

        orderHeader.setOpaque(false);

        JLabel orderNumber = new JLabel(
                "ORDER #" + order.getOrderNumber());

        orderNumber.setFont(
                new Font("Arial", Font.BOLD, 19));

        orderNumber.setForeground(RED);

        JLabel orderDate = new JLabel(
                order.getOrderTime().format(dateFormatter));

        orderDate.setFont(
                new Font("Arial", Font.PLAIN, 12));

        orderDate.setForeground(TEXT_GRAY);

        orderHeader.add(orderNumber, BorderLayout.WEST);
        orderHeader.add(orderDate, BorderLayout.EAST);

        // PRODUCT SECTION
        JPanel productsPanel = new JPanel();

        productsPanel.setLayout(
                new BoxLayout(productsPanel, BoxLayout.Y_AXIS));

        productsPanel.setOpaque(false);

        JLabel itemsTitle = new JLabel("ORDER ITEMS");

        itemsTitle.setFont(
                new Font("Arial", Font.BOLD, 12));

        itemsTitle.setForeground(TEXT_GRAY);

        itemsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        productsPanel.add(itemsTitle);
        productsPanel.add(Box.createVerticalStrut(12));

        for (OrderItem item : order.getItems()) {

            JPanel productRow = createProductRow(item);

            productsPanel.add(productRow);
            productsPanel.add(Box.createVerticalStrut(10));
        }

        // TOTAL SECTION
        JPanel totalPanel = new JPanel(
                new BorderLayout());

        totalPanel.setOpaque(false);

        totalPanel.setBorder(
                BorderFactory.createMatteBorder(
                        1, 0, 0, 0, BORDER_GRAY));

        JLabel totalLabel = new JLabel("TOTAL AMOUNT");

        totalLabel.setFont(
                new Font("Arial", Font.BOLD, 13));

        totalLabel.setForeground(Color.DARK_GRAY);

        JLabel totalAmount = new JLabel(
                String.format("₱%.2f", order.getTotal()));

        totalAmount.setFont(
                new Font("Arial", Font.BOLD, 21));

        totalAmount.setForeground(RED);

        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(totalAmount, BorderLayout.EAST);

        totalPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                1, 0, 0, 0, BORDER_GRAY),
                        BorderFactory.createEmptyBorder(
                                15, 0, 0, 0)));

        // ADD SECTIONS TO CARD
        card.add(orderHeader, BorderLayout.NORTH);
        card.add(productsPanel, BorderLayout.CENTER);
        card.add(totalPanel, BorderLayout.SOUTH);

        // CARD SIZE
        card.setPreferredSize(new Dimension(700, card.getPreferredSize().height));
        card.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        return card;
    }

    // PRODUCT ROW
    private JPanel createProductRow(OrderItem item) {

        JPanel row = new JPanel(
                new BorderLayout(10, 0));

        row.setOpaque(false);

        JLabel productName = new JLabel(
                item.getProductName());

        productName.setFont(
                new Font("Arial", Font.BOLD, 14));

        productName.setForeground(Color.DARK_GRAY);

        JLabel quantity = new JLabel(
                "x" + item.getQuantity());

        quantity.setFont(
                new Font("Arial", Font.PLAIN, 13));

        quantity.setForeground(TEXT_GRAY);

        JLabel subtotal = new JLabel(
                String.format("₱%.2f", item.getSubtotal()));

        subtotal.setFont(
                new Font("Arial", Font.BOLD, 14));

        subtotal.setForeground(Color.DARK_GRAY);

        JPanel leftPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 0));

        leftPanel.setOpaque(false);

        leftPanel.add(productName);
        leftPanel.add(quantity);

        row.add(leftPanel, BorderLayout.WEST);
        row.add(subtotal, BorderLayout.EAST);

        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        return row;
    }

    // EMPTY HISTORY
    private void showEmptyHistory() {

        JPanel emptyPanel = new JPanel();

        emptyPanel.setLayout(
                new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));

        emptyPanel.setBackground(Color.WHITE);

        emptyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emptyTitle = new JLabel("No Orders Yet");

        emptyTitle.setFont(
                new Font("Arial", Font.BOLD, 23));

        emptyTitle.setForeground(RED);

        emptyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emptySubtitle = new JLabel(
                "Your previous orders will appear here.");

        emptySubtitle.setFont(
                new Font("Arial", Font.PLAIN, 14));

        emptySubtitle.setForeground(TEXT_GRAY);

        emptySubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyPanel.add(Box.createVerticalStrut(100));
        emptyPanel.add(emptyTitle);
        emptyPanel.add(Box.createVerticalStrut(10));
        emptyPanel.add(emptySubtitle);

        historyItemsPanel.add(emptyPanel);
    }

    // ROUNDED CARD
    private class RoundedPanel extends JPanel {

        private final int radius;
        private final Color backgroundColor;
        private final Color borderColor;

        public RoundedPanel(
                int radius,
                Color backgroundColor,
                Color borderColor) {

            this.radius = radius;
            this.backgroundColor = backgroundColor;
            this.borderColor = borderColor;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(backgroundColor);

            g2.fillRoundRect(
                    0, 0,
                    getWidth() - 1,
                    getHeight() - 1,
                    radius, radius);

            g2.setColor(borderColor);

            g2.setStroke(new BasicStroke(1));

            g2.drawRoundRect(
                    0, 0,
                    getWidth() - 1,
                    getHeight() - 1,
                    radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }
    }
}

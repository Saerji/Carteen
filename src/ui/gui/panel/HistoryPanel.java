
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
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);

    private final HistoryService historyService;

    private JPanel historyItemsPanel;

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("MMM dd, yyyy - hh:mm a");

    public HistoryPanel(HistoryService historyService) {

        this.historyService = historyService;

        setLayout(new BorderLayout(15, 15));
        setBackground(Color.WHITE);

        setBorder(BorderFactory.createEmptyBorder(
                25, 35, 25, 35));

        // HEADER
        JLabel title = new JLabel("ORDER HISTORY");

        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(RED);

        add(title, BorderLayout.NORTH);

        // HISTORY ITEMS
        historyItemsPanel = new JPanel();

        historyItemsPanel.setLayout(
                new BoxLayout(historyItemsPanel, BoxLayout.Y_AXIS));

        historyItemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(historyItemsPanel);

        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        refreshHistory();
    }

    public void refreshHistory() {

        historyItemsPanel.removeAll();

        List<Order> orders = historyService.getOrders();

        if (orders.isEmpty()) {

            JLabel emptyLabel = new JLabel(
                    "You haven't placed any orders yet.");

            emptyLabel.setFont(
                    new Font("Arial", Font.PLAIN, 18));

            emptyLabel.setForeground(Color.GRAY);

            historyItemsPanel.add(emptyLabel);

        } else {

            // DISPLAY NEWEST ORDERS FIRST
            for (int i = orders.size() - 1; i >= 0; i--) {

                Order order = orders.get(i);

                JPanel orderCard = new JPanel();

                orderCard.setLayout(
                        new BoxLayout(orderCard, BoxLayout.Y_AXIS));

                orderCard.setBackground(LIGHT_GRAY);

                orderCard.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        new Color(220, 220, 220)),
                                BorderFactory.createEmptyBorder(
                                        15, 20, 15, 20)));

                // ORDER INFORMATION
                JLabel orderNumber = new JLabel(
                        "ORDER #" + order.getOrderNumber());

                orderNumber.setFont(
                        new Font("Arial", Font.BOLD, 18));

                orderNumber.setForeground(RED);

                JLabel orderDate = new JLabel(
                        order.getOrderTime().format(dateFormatter));

                orderDate.setFont(
                        new Font("Arial", Font.PLAIN, 12));

                // PRODUCTS
                JPanel productsPanel = new JPanel();

                productsPanel.setLayout(
                        new BoxLayout(productsPanel, BoxLayout.Y_AXIS));

                productsPanel.setOpaque(false);

                for (OrderItem item : order.getItems()) {

                    JLabel productLabel = new JLabel(
                            item.getProductName()
                            + "  x" + item.getQuantity()
                            + "     ₱"
                            + String.format("%.2f", item.getSubtotal()));

                    productLabel.setFont(
                            new Font("Arial", Font.PLAIN, 14));

                    productsPanel.add(productLabel);
                }

                // TOTAL
                JLabel total = new JLabel(
                        String.format(
                                "TOTAL: ₱%.2f",
                                order.getTotal()));

                total.setFont(
                        new Font("Arial", Font.BOLD, 17));

                total.setForeground(RED);

                // ADD COMPONENTS
                orderCard.add(orderNumber);
                orderCard.add(Box.createVerticalStrut(5));

                orderCard.add(orderDate);
                orderCard.add(Box.createVerticalStrut(15));

                orderCard.add(productsPanel);
                orderCard.add(Box.createVerticalStrut(15));

                orderCard.add(total);

                orderCard.setAlignmentX(Component.LEFT_ALIGNMENT);

                historyItemsPanel.add(orderCard);

                historyItemsPanel.add(
                        Box.createVerticalStrut(15));
            }
        }

        historyItemsPanel.revalidate();
        historyItemsPanel.repaint();
    }
}

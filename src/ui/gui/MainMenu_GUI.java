
package ui.gui;

import javax.swing.*;
import java.awt.*;

import model.Shop;
import model.Order;

import service.MenuService;
import service.CartService;
import service.CheckoutService;
import service.HistoryService;

import ui.gui.navigation.NavigationBar;
import ui.gui.action.NavigationHandler;

import ui.gui.panel.ShopSelectionPanel;
import ui.gui.panel.ShopMenuPanel;
import ui.gui.panel.CartPanel;
import ui.gui.panel.HistoryPanel;

public class MainMenu_GUI extends JFrame {

    public MainMenu_GUI() {

        setTitle("Student Ordering System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // SERVICES
        MenuService menuService = new MenuService();

        CartService cartService = new CartService();

        CheckoutService checkoutService =
                new CheckoutService(cartService);

        HistoryService historyService =
                new HistoryService();

        // NAVIGATION BAR
        NavigationBar navigationBar = new NavigationBar();

        // CONTENT PANEL
        CardLayout cardLayout = new CardLayout();

        JPanel contentPanel = new JPanel(cardLayout);

        // SHOP SELECTION PAGE
        contentPanel.add(
                new ShopSelectionPanel(
                        menuService,
                        shop -> cardLayout.show(
                                contentPanel,
                                shop.name())),
                "MENU");

        // INDIVIDUAL SHOP PAGES
        for (Shop shop : Shop.values()) {

            if (!menuService.getProductsByShop(shop).isEmpty()) {

                contentPanel.add(
                        new ShopMenuPanel(
                                menuService,
                                shop,
                                cartService,
                                () -> cardLayout.show(
                                        contentPanel,
                                        "MENU")),
                        shop.name());
            }
        }

        // HISTORY PAGE
        HistoryPanel historyPanel =
                new HistoryPanel(historyService);

        contentPanel.add(
                historyPanel,
                "HISTORY");

        // CART PAGE
        CartPanel cartPanel =
                new CartPanel(cartService);

        contentPanel.add(
                cartPanel,
                "CART");

        // CONNECT CHECKOUT BUTTON
        cartPanel.setCheckoutAction(() ->
                processCheckout(
                        cartService,
                        checkoutService,
                        historyService,
                        cartPanel,
                        historyPanel));

        // NAVIGATION ACTIONS
        NavigationHandler navigationHandler =
                new NavigationHandler(
                        cardLayout,
                        contentPanel,
                        this);

        navigationHandler.initializeActions(
        navigationBar.getMenuBtn(),
        navigationBar.getHistoryBtn(),
        navigationBar.getCartBtn(),
        navigationBar.getSignOutBtn(),
        cartPanel);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(
                navigationBar.getNavBar(),
                BorderLayout.NORTH);

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER);

        add(mainPanel);
    }

    // CHECKOUT PROCESS
    private void processCheckout(
            CartService cartService,
            CheckoutService checkoutService,
            HistoryService historyService,
            CartPanel cartPanel,
            HistoryPanel historyPanel) {

        // CHECK EMPTY CART
        if (cartService.getCartItems().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty!",
                    "Checkout Failed",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // CONFIRM CHECKOUT
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to place your order?",
                "Confirm Checkout",
                JOptionPane.YES_NO_OPTION);

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        // CREATE ORDER
        Order order = checkoutService.createOrder();

        if (order != null) {

            // SAVE ORDER TO HISTORY
            historyService.addOrder(order);

            // COMPLETE CHECKOUT AND CLEAR CART
            checkoutService.completeCheckout();

            // REFRESH BOTH PAGES
            cartPanel.refreshCart();
            historyPanel.refreshHistory();

            // SUCCESS MESSAGE
            JOptionPane.showMessageDialog(
                    this,
                    "Order #" + order.getOrderNumber()
                    + " placed successfully!\n"
                    + String.format(
                            "Total: ₱%.2f",
                            order.getTotal()),
                    "Checkout Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainMenu_GUI mainMenuGUI =
                    new MainMenu_GUI();

            mainMenuGUI.setVisible(true);
        });
    }
}


package ui.gui;

import javax.swing.*;
import java.awt.*;

import model.Shop;

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

        // CART PAGE
        contentPanel.add(
                new CartPanel(),
                "CART");

        // HISTORY PAGE
        contentPanel.add(
                new HistoryPanel(),
                "HISTORY");

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
                navigationBar.getSignOutBtn());

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

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainMenu_GUI mainMenuGUI = new MainMenu_GUI();
            mainMenuGUI.setVisible(true);
        });
    }
}

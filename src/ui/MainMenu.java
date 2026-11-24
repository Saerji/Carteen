package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import service.CartService;
import service.HistoryService;
import service.CheckoutService;
import service.MenuService;

import model.Order;
import model.OrderItem;

public class MainMenu {
    private final Scanner sc;
    private MenuService menuService;
    private CartService cartService;
    private final CheckoutService checkoutService;
    private final HistoryService historyService;

    public MainMenu(Scanner sc, MenuService menuService, CartService cartService, CheckoutService checkoutService, HistoryService historyService){
        this.sc = sc;
        this.menuService = menuService;
        this.cartService = cartService;
        this.checkoutService = checkoutService;
        this.historyService = historyService;
    }

    public int displayMenu() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║           CARTEEN MENU           ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  Welcome User!                   ║");
        System.out.println("║                                  ║");
        System.out.println("║  [1] Add Foods to Cart           ║");
        System.out.println("║  [2] View / Edit Cart            ║");
        System.out.println("║  [3] Checkout Cart               ║");
        System.out.println("║  [4] View Cart Adding History    ║");
        System.out.println("║  [5] Exit                        ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        
        int choice = sc.nextInt();
        return choice;
    }

    public void runMenu(){
        boolean isRunning = true;

        while(isRunning) {
            int choice = displayMenu();
            switch(choice) {
                case 1:
                    orderfood();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    checkout();
                case 4:
                    viewHistory();
                    break;
                case 5:
                    exitProgram();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    public void orderfood(){
        ShopsMenu shopsMenu = new ShopsMenu(menuService, cartService, sc);

        shopsMenu.displayShops();
    }

    public void viewCart(){
        CartMenu cartMenu = new CartMenu(cartService);
        cartMenu.displayCart();
    }

    public void checkout() {
        Order order = checkoutService.createOrder();

        if(order == null) {
            System.out.println("Your cart can't be empty.");
            return;
        }

        System.out.println("========== Order Review ==========");
        for(OrderItem item : order.getItems()) {
                System.out.printf("%s x %d     ₱%.2f%n",
                    item.getProductName(),
                    item.getQuantity(),
                    item.getSubtotal()
                );
            }
            System.out.printf("Total: ₱%.2f%n", order.getTotal());
            System.out.println();
            System.out.println("====================");

        while(true) {
            System.out.println("Confirm Checkout:\n[1] Yes\n[2] No");
            System.out.print("Enter choice:");
            try{
                int choice = sc.nextInt();
                if(choice == 1) {
                    historyService.addOrder(order);
                    checkoutService.completeCheckout();
                    System.out.println("Checkout completed successfully!");
                    break;
                } else if(choice == 2) {
                    return;
                } else {
                    System.out.println("Invalid input.");
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input.");
                sc.next();
            }
        }
    }

    public void viewHistory(){
        HistoryMenu historyMenu = new HistoryMenu(historyService);
        historyMenu.displayHistory();
    }

    public void exitProgram(){
        System.out.println("Thank you for using Carteen!");
    }

}
import ui.*;
import service.MenuService;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        ShopsMenu shopsMenu = new ShopsMenu(menuService);
        
        shopsMenu.displayShops();
    }
}
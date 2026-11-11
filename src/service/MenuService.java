package service;

import model.FoodCategory;
import model.Product;
import model.Shop;

import java.util.ArrayList;
import java.util.List;


public class MenuService {
    private final ArrayList<Product> menu = new ArrayList<>();
    
    public MenuService() {
        
    }

    public List<Product> getMenu() {
        return List.copyOf(menu);
    }
}

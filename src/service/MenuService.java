package service;

import model.FoodCategory;
import model.Product;
import model.Shop;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private final ArrayList<Product> products = new ArrayList<>();

    public MenuService() {
        initializeProducts();
    }

    public void initializeProducts (){
        // Tender Juicy Stall
        products.add(new Product("Jumbo", 25, FoodCategory. MEAL, Shop.TENDER_JUICY));
        // Spot G

        // Chowking

        //Fruit Juice

        // Potato Corner

        //Waffle Time
    }

    public List<Product> getAllProduct(){
        return new ArrayList<Product>(products);  
    }

    public List<Product> getProductsByShop(Shop shop) {
        List<Product> shopResult = new ArrayList<>();
        for(Product product : products) {
            if(product.getShop() == shop) {
                shopResult.add(product);
            }
        }
        return shopResult;
    }
}
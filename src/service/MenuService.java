
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

    public void initializeProducts() {

        // Tender Juicy Stall
        products.add(new Product("Jumbo", 25, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Regular Hotdog", 20, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Cheese Hotdog", 30, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Footlong", 45, FoodCategory.MEAL, Shop.TENDER_JUICY));

        // Spot G
        products.add(new Product("Siomai Rice", 50, FoodCategory.MEAL, Shop.SPOT_G));
        products.add(new Product("Shanghai Rice", 45, FoodCategory.MEAL, Shop.SPOT_G));
        products.add(new Product("Pancit Canton", 40, FoodCategory.MEAL, Shop.SPOT_G));
        products.add(new Product("Lumpiang Shanghai", 10, FoodCategory.SNACK, Shop.SPOT_G));

        // Chowking
        products.add(new Product("Chao Fan", 65, FoodCategory.MEAL, Shop.CHOWKING));
        products.add(new Product("Pork Siomai", 45, FoodCategory.SNACK, Shop.CHOWKING));
        products.add(new Product("Halo-Halo", 55, FoodCategory.DESSERT, Shop.CHOWKING));
        products.add(new Product("Buchi", 35, FoodCategory.DESSERT, Shop.CHOWKING));

        // Fruit Juice
        products.add(new Product("Orange Juice", 25, FoodCategory.DRINK, Shop.FRUIT_JUICE));
        products.add(new Product("Mango Juice", 30, FoodCategory.DRINK, Shop.FRUIT_JUICE));
        products.add(new Product("Watermelon Juice", 30, FoodCategory.DRINK, Shop.FRUIT_JUICE));
        products.add(new Product("Pineapple Juice", 25, FoodCategory.DRINK, Shop.FRUIT_JUICE));

        // Potato Corner
        products.add(new Product("Regular Fries", 50, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Cheese Fries", 60, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("BBQ Fries", 60, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Sour Cream Fries", 60, FoodCategory.SNACK, Shop.POTATO_CORNER));

        // Waffle Time
        products.add(new Product("Classic Waffle", 25, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("Chocolate Waffle", 35, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("Cheese Waffle", 35, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("Ham and Cheese Waffle", 45, FoodCategory.MEAL, Shop.WAFFLE_TIME));
    }

    public List<Product> getAllProduct() {
        return new ArrayList<Product>(products);
    }

    public List<Product> getProductsByShop(Shop shop) {
        List<Product> shopResult = new ArrayList<>();
        for (Product product : products) {
            if (product.getShop() == shop) {
                shopResult.add(product);
            }
        }
        return shopResult;
    }
}

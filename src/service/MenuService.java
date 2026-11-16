
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
        products.add(new Product("Hungarian On Stick", 55, FoodCategory.SNACK, Shop.TENDER_JUICY));
        products.add(new Product("Jumbo On Stick", 25, FoodCategory.SNACK, Shop.TENDER_JUICY));
        products.add(new Product("Chick n' Cheese On Stick", 30, FoodCategory.SNACK, Shop.TENDER_JUICY));
        products.add(new Product("Kingsize On Stick", 40, FoodCategory.SNACK, Shop.TENDER_JUICY));
        products.add(new Product("Giant On Stick", 45, FoodCategory.SNACK, Shop.TENDER_JUICY));
        products.add(new Product("Hungarian Sandwich", 60, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Jumbo Sandwich", 30, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Chick n' Cheese Sandwich", 35, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Kingsize Sandwich", 45, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Giant Sandwich", 50, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Half Long Sandwich", 25, FoodCategory.MEAL, Shop.TENDER_JUICY));
        products.add(new Product("Footlong Sandwich", 50, FoodCategory.MEAL, Shop.TENDER_JUICY));

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
        products.add(new Product("Regular Flavored Fries", 45, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Large Flavored Fries", 70, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Jumbo Flavored Fries", 99, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Mega Flavored Fries", 130, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Giga Flavored Fries", 205, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Tera Flavored Fries", 235, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Large Loopys", 75, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Mega Loopys", 135, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Regular Crunchy Chicken Pops", 79, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Large Crunchy Chicken Pops", 99, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Mega Crunchy Chicken Pops", 199, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Large Chicken Fries", 99, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Mega Chicken Fries", 199, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Large Mix of 2", 99, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Mega Mix of 2", 149, FoodCategory.SNACK, Shop.POTATO_CORNER));
        products.add(new Product("Tera Mix of 3", 259, FoodCategory.SNACK, Shop.POTATO_CORNER));

        // Waffle Time
        products.add(new Product("Mixed Berries", 34, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("TJ Chicken Hotdog", 32, FoodCategory.MEAL, Shop.WAFFLE_TIME));
        products.add(new Product("Bacon & Cheese", 35, FoodCategory.MEAL, Shop.WAFFLE_TIME));
        products.add(new Product("Ham N' Cheese", 32, FoodCategory.MEAL, Shop.WAFFLE_TIME));
        products.add(new Product("German Cheese Franks", 35, FoodCategory.MEAL, Shop.WAFFLE_TIME));
        products.add(new Product("Sausage Delight", 34, FoodCategory.MEAL, Shop.WAFFLE_TIME));
        products.add(new Product("Hazelnut", 32, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("Belgian Chocolate", 30, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("Cheese Delite", 25, FoodCategory.SNACK, Shop.WAFFLE_TIME));
        products.add(new Product("Cheesedog", 35, FoodCategory.MEAL, Shop.WAFFLE_TIME));
        products.add(new Product("Cream Cheese", 35, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("Tuna Salad", 32, FoodCategory.MEAL, Shop.WAFFLE_TIME));
        products.add(new Product("Bavarian Cream", 28, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("Ultimate Ube", 26, FoodCategory.DESSERT, Shop.WAFFLE_TIME));
        products.add(new Product("American Hotdog", 32, FoodCategory.MEAL, Shop.WAFFLE_TIME));

        //Julie's Bakeshop
        products.add(new Product("Chocolate Cake", 25, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Graciosa Icing", 12, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Marble Ring", 12, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Cheese Streusel", 15, FoodCategory.SNACK, Shop.JULIES_BAKESHOP));
        products.add(new Product("Cheese Bread", 12, FoodCategory.SNACK, Shop.JULIES_BAKESHOP));
        products.add(new Product("Sweet Heart", 14, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Choco German", 12, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Cheese Muffins", 12, FoodCategory.SNACK, Shop.JULIES_BAKESHOP));
        products.add(new Product("Chocolate Bar", 10, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Choco Chip Cookies", 12, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Chocolate Crinkles", 12, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));
        products.add(new Product("Ube Bar", 10, FoodCategory.DESSERT, Shop.JULIES_BAKESHOP));

        //RC Beef Shawarma
        products.add(new Product("Shawarma Nachos", 55, FoodCategory.SNACK, Shop.RC_BEEF_SHAWARMA));
        products.add(new Product("Shawarma Pita", 60, FoodCategory.MEAL, Shop.RC_BEEF_SHAWARMA));
        products.add(new Product("Shawarma w/ Cheese", 65, FoodCategory.MEAL, Shop.RC_BEEF_SHAWARMA));
        products.add(new Product("Shawarma Rice", 70, FoodCategory.MEAL, Shop.RC_BEEF_SHAWARMA));

        //RR Sorellas
        products.add(new Product("Cheesy Garlic 1 Slice", 30, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Cheesy Garlic Whole", 215, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Ham and Cheese 1 Slice", 32, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Ham and Cheese Whole", 230, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Hawaiian 1 Slice", 35, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Hawaiian Whole", 250, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Garden Fresh 1 Slice", 37, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Garden Fresh Whole", 270, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Overload 1 Slice", 41, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Overload Whole", 305, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Buffalo 1 Slice", 40, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Buffalo Whole", 295, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Pepperoni 1 Slice", 35, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Pepperoni Whole", 250, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Cheesy Garlic in White Sauce 1 Slice", 32, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Cheesy Garlic in White Sauce Whole", 230, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Spinach Pizza 1 Slice", 42, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Spinach Pizza Whole", 310, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Bacon and Cheese 1 Slice", 40, FoodCategory.MEAL, Shop.RR_SORELLAS));
        products.add(new Product("Bacon and Cheese Whole", 295, FoodCategory.MEAL, Shop.RR_SORELLAS));

        //Fried Noodles Haus
        products.add(new Product("Solo Fried Noodles", 35, FoodCategory.MEAL, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Double Fried Noodles", 65, FoodCategory.MEAL, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Jumbo Fried Noodles", 95, FoodCategory.MEAL, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Siomai Topping 3pcs (Pork/Beef/Chicken/Pork and Shrimp)", 20, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Sharksfin Topping", 10, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Gyoza Topping", 10, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Japanese Siomai Topping", 10, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Cheese Lumpia Topping", 10, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Japanese Sausage Topping", 10, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Nuggets Topping", 10, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Add Egg", 15, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Dumpling Bundle 15pcs Pork/Chicken/Beef", 100, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Dumpling Bundle 15pcs Pork and Shrimp", 100, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Dumpling Bundle 15pcs Japanese", 150, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Dumpling Bundle 12pcs Sharksfin", 120, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Dumpling Bundle 12pcs Gyoza", 120, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Jumbo Pork Asado", 40, FoodCategory.SNACK, Shop.FRIED_NOODLES_HAUS));
        products.add(new Product("Gulaman", 15, FoodCategory.DRINK, Shop.FRIED_NOODLES_HAUS));
        
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

package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {

	private final static Dish tiger = new Dish("Tiger Roll", "Avocado, shrimp tempura, cucumber, tobiko", 8, 30, 75);
	private final static Dish philly = new Dish("Philadelphia Roll", "Salmon, avocado, cream cheese", 9, 25, 55);
	private final static Dish crunch = new Dish("Crunch Roll", "Spicy tuna, crispy seaweed, tempura", 8, 45, 80);
	private final static Dish rainbow = new Dish("Rainbow Roll", "Fish cake, avocado, cucumber, tuna, avocado, salmon, shrimp, yellowtail", 9, 45, 85);
	private final static Dish dragon = new Dish("Dragon Roll", "Eel, crab, cucumber, eel sauce", 5, 50, 130);
	
	private final Ingredient salmon = new Ingredient("Salmon", "g", new Supplier("Man's fish", 0.9));
	private final Ingredient eel = new Ingredient("Eel", "g", new Supplier("Man's fish", 0.9));
	private final Ingredient avocado = new Ingredient("Avocado", "pcs", new Supplier("Wholefoods", 1.2));
	private final Ingredient cucumber = new Ingredient("Cucumber", "pcs", new Supplier("Wholefoods", 1.2));
	private final Ingredient tuna = new Ingredient("Tuna", "g", new Supplier("Man's fish", 0.9));
	private final Ingredient crab = new Ingredient("Crab", "g", new Supplier("Crust-Oceans", 2.1));
	private final Ingredient tobiko = new Ingredient("Tobiko", "g", new Supplier("Man's fish", 0.9));
	private final Ingredient seaweed = new Ingredient("Seaweed", "pcs", new Supplier("The See", 2.2));
	private final Ingredient eelSauce = new Ingredient("Eel Sauce", "mL", new Supplier("The See", 2.2));
	
	public static List<Dish> dishList = new ArrayList<Dish>();
    public static List<Ingredient> ingredientList = new ArrayList<Ingredient>();
    public static List<Supplier> supplierList = new ArrayList<Supplier>();
    public static List<Staff> staffList = new ArrayList<Staff>();
	public static List<Drone> droneList = new ArrayList<Drone>();
//    public static List<Postcode> postcodeList = new ArrayList<Postcode>();
//    public static List<User> userList;
    
    
    public Inventory() {
    		addDish();
    		addIngredient();
    }
    

    public static void addDish() {
        dishList.add(tiger);
        dishList.add(philly);
        dishList.add(crunch);
        dishList.add(rainbow);
        dishList.add(dragon);
    }
    
    public void addIngredient() {
    		ingredientList.add(salmon);
    		ingredientList.add(eel);
    		ingredientList.add(avocado);
    		ingredientList.add(cucumber);
    		ingredientList.add(tuna);
    		ingredientList.add(crab);
    		ingredientList.add(tobiko);
    		ingredientList.add(seaweed);
    		ingredientList.add(eelSauce);
    }
    
    public static List<Dish> getDishList() {
    		addDish();
    		return dishList;
    }
    
    public static List<Ingredient> getIngredientList() {
    		return ingredientList;
    }

    public static synchronized void checkDishStock() {
        for (Dish dish : dishList){
            if (dish.getDishManager().checkRestock()) {
                dish.getDishManager().manageStock(100);
            }
        }
    }
    
    public static synchronized void checkIngredientStock() {
	    	for (Ingredient ing : ingredientList) {
	        	if (ing.getIngredientManager().checkRestock()) {
	            ing.getIngredientManager().manageStock(100);
	        }
	    }
    }

}

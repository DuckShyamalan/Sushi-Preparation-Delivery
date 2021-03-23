package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import client.ClientSide;

public class Configuration {

	static Path path = Paths.get("/Users/SyedF/Desktop/Uni stuff/COMP1206/labs/Sushi Prep & Delv./Mailbox/");
	static File configFile = new File(path + "/config.txt/");
	Inventory inventory = new Inventory();
//	Supplier supplier = new Supplier("Man's fish", 0.7);
//	Ingredient ingredient = new Ingredient("Salmon", "g", supplier);
//	Dish dish = new Dish("Crunch Roll", "Spicy tuna, crispy seaweed, tempura", 8.75, new HashMap<Ingredient, Number>());
//	Postcode postcode = new Postcode(7, "SO17 1BJ");
//	User user = new User("afs1n17", "password", "Soton", postcode);
//	Staff staff = new Staff("Don", inventory);
//	Order order = new Order("Crunch Roll", user);
//	Drone drone = new Drone(13, "SUX18", inventory, 1);
//	DishManager dishStock = dish.getDishManager();
//	IngredientManager ingStock = ingredient.getIngredientManager();
	
	public Configuration(File configFile) {
		this.configFile = configFile;
	}
	
	public void readConfig(File configFile) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(configFile));
			String read;
			while ((read = br.readLine()) != null) {
		        String[] splitFile = read.split(":");
		        for (String part : splitFile) {
		        		switch(splitFile[0]) {
			        		case ("SUPPLIER") :
			        			addSupplier(splitFile);
			        		case ("INGREDIENT"):
			        			addIngredient(splitFile);
			        		case ("DISH"):
			        			addDish(splitFile);
			        		case ("POSTCODE"):
			        			addPostcode(splitFile);
			        		case ("USER"):
			        			addUser(splitFile);
			        		case ("ORDER"):
			        			addOrder(splitFile);
			        		case ("STOCK"):
			        			addStock(splitFile);
			        		case ("STAFF"):
			        			addStaff(splitFile);
			        		case ("DRONE"):
			        			addDrone(splitFile);
		        		}
		            System.out.println(part);
		        } 
		    }	
		} catch(Exception e) {
		    System.err.println(e);
		}
	}
	
	public void addSupplier(String[] splitFile) {
		Supplier supplier = new Supplier(splitFile[1],Double.parseDouble(splitFile[2]));
		Inventory.supplierList.add(supplier);
	}
	
	public void addIngredient(String[] splitFile) {
		Ingredient ing = new Ingredient(splitFile[1], splitFile[2], getSupplier(splitFile[3]));
		ing.getIngredientManager().setThreshold(Integer.valueOf(splitFile[4]));
		ing.getIngredientManager().setRestock(Integer.valueOf(splitFile[5]));
		Inventory.ingredientList.add(ing);
	}
	
	public void addDish(String[] splitFile) {
	    String[] ingRequired = splitFile[6].split(",");
	    HashMap<Ingredient, Number> recipe = null;
	    Dish dish = new Dish(splitFile[1], splitFile[2], Integer.valueOf(splitFile[3]), Integer.valueOf(splitFile[4]), Integer.valueOf(splitFile[5]));
	    for (int x = 0; x < ingRequired.length;x++) {
	        String[] ingredients = ingRequired[x].split("\\*");
	        dish.addIngredient(getIngredient(ingredients[1]),Integer.valueOf(ingredients[0]));
	        recipe.put(getIngredient(ingredients[1]), Integer.valueOf(ingredients[0]));
	    }
	    dish.getDishManager().setThreshold(Integer.valueOf(splitFile[4]));
	    dish.getDishManager().setRestock(Integer.valueOf(splitFile[5]));
	    Inventory.dishList.add(dish);
	}
	
	public void addPostcode(String[] splitFile) {
		Postcode postcode = new Postcode(Double.parseDouble(splitFile[1]), splitFile[2]);
		ClientSide.postcodeList.add(postcode);
	}
	
	public void addUser(String[] splitFile) {
		User user = new User(splitFile[1], splitFile[2], splitFile[3], getPostcode(splitFile[4]));
		ClientSide.userList.add(user);
	}
	
	public void addOrder(String[] splitFile) {
		User user = getUser(splitFile[1]);
		String[] dishOrder = null;
		for (int i = 0; i< dishOrder.length;i++) {
            String [] dishes = dishOrder[i].split("\\*");
            user.addDish(getDish(dishes[1]),Integer.valueOf(dishes[2]));
        }
        String orderNumber = (Integer.toString (user.getOrderList().size()+1));
        Order newOrder = new Order(orderNumber,user);
        User.orderList.add(newOrder);
        user.addOrder(newOrder);
        user.clearBasket();
	}
	
	public void addStock(String [] splitFile){
        Dish dish = getDish(splitFile[1]);
        Ingredient ingredient = getIngredient(splitFile[1]);
        if (dish != null) {
            dish.getDishManager().setAmount(Integer.valueOf(splitFile[2]));
        }
        else {
            ingredient.getIngredientManager().setAmount(Integer.valueOf(splitFile[2]));
        }
    }

    public void addStaff(String[] splitFile) {
        Staff staff = new Staff(splitFile[1], inventory);
        Inventory.staffList.add(staff);
    }

    public void addDrone (String [] splitFile) {
    		Random random = new Random();
        Drone drone = new Drone(Integer.valueOf(splitFile[1]), "Drone " + random.nextInt(40) , inventory, 2);
        Inventory.droneList.add(drone);
    }
	
	public Supplier getSupplier (String name) {
        for (Supplier sup: Inventory.supplierList){
            if (name.equals(sup.getName()))
            {
                return sup;
            }
        }
        return null;
    }
	
    public Ingredient getIngredient (String name) {
        for (Ingredient ing : Inventory.ingredientList){
            if (name.equals(ing.getName())) {
                return ing;
            }

        }
        return null;
    }
    
    public Dish getDish(String name) {
        for (Dish dish : Inventory.dishList) {
            if (name.equals(dish.getName())) {
            		return dish;
            }
        }
        return null;
    }
    
    public Postcode getPostcode(String name) {
        for (Postcode pc: ClientSide.postcodeList){
            if (name.equals(pc.getPostcode())) {
            		return pc;
            }
        }
        return null;
    }
    
    public User getUser(String name) {
        for (User user : ClientSide.userList) {
	        if (name.equals(user.getName())) {
	        		return user;
	        }
        }
        return null;
    }
}

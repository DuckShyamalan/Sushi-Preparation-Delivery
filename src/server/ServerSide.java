package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import client.ClientSide;
import common.Comms;
import common.Configuration;
import common.Dish;
import common.Drone;
import common.Ingredient;
import common.Inventory;
import common.Order;
import common.Postcode;
import common.Staff;
import common.Supplier;
import common.UpdateListener;
import common.User;

public class ServerSide implements ServerInterface {

	public ServerSide() {
		Inventory.dishList = new ArrayList<Dish>();
		Inventory.droneList = new ArrayList<Drone>();
		Inventory.ingredientList = new ArrayList<Ingredient>();
		Inventory.staffList = new ArrayList<Staff>();
		Inventory.supplierList = new ArrayList<Supplier>();
		ClientSide.postcodeList = new ArrayList<Postcode>();
		ClientSide.userList = new ArrayList<User>();
		User.orderList = new ArrayList<Order>();
	}

	@Override
	public void loadConfiguration(String filename) throws FileNotFoundException {
		File file = new File("/Users/SyedF/Desktop/Uni stuff/COMP1206/labs/Sushi Prep & Delv./bin/Mailbox/" + filename + ".txt");
		new Configuration(file);
	}

	@Override
	public void setRestockingIngredientsEnabled(boolean enabled) {
		enabled = true;
	}

	@Override
	public void setRestockingDishesEnabled(boolean enabled) {
		enabled = true;
	}

	@Override
	public void setStock(Dish dish, Number stock) {
		dish.getDishManager().setAmount(stock);
	}

	@Override
	public void setStock(Ingredient ingredient, Number stock) {
		ingredient.getIngredientManager().setAmount(stock);
	}

	@Override
	public List<Dish> getDishes() {
		return Inventory.getDishList();
	}

	@Override
	public Dish addDish(String name, String description, Number price, Number restockThreshold, Number restockAmount) {
		Dish newDish = new Dish(name, description, (Integer) price, (Integer) restockThreshold, (Integer) restockAmount);
		newDish.getDishManager().setThreshold((Integer) restockThreshold);
		newDish.getDishManager().setRestock((Integer) restockAmount);
		Inventory.dishList.add(newDish);
		Comms.sendDishList(getDishes());
		
		return newDish;
	}

	@Override
	public void removeDish(Dish dish) throws UnableToDeleteException {
		Inventory.dishList.remove(dish);
		Comms.sendDishList(getDishes());
	}

	@Override
	public void addIngredientToDish(Dish dish, Ingredient ingredient, Number quantity) {
		for(Dish d : Inventory.getDishList()) {
			if(dish.getName().equals(d.getName())) {
				dish.getRecipe().put(ingredient, (Integer) quantity);
			}
		}
	}

	@Override
	public void removeIngredientFromDish(Dish dish, Ingredient ingredient) {
		List<Ingredient> ingredients = null;
		for (Dish d : Inventory.getDishList()) {
			if(dish.getName().equals(d.getName())) {
				ingredients = new ArrayList<Ingredient>(dish.getRecipe().keySet());
				for (Ingredient i : Inventory.ingredientList) {
					if(ingredient.getName().equals(i.getName())) {
						dish.getRecipe().remove(i);
					}
				}
			}
		}
	}

	@Override
	public void setRecipe(Dish dish, Map<Ingredient, Number> recipe) {
		for (Dish d : Inventory.getDishList()) {
			if(dish.getName().equals(d.getName())) {
				dish.recipe = recipe;
			}
		}
	}

	@Override
	public void setRestockLevels(Dish dish, Number restockThreshold, Number restockAmount) {
		dish.getDishManager().setThreshold((Integer) restockThreshold);
		dish.getDishManager().setRestock((Integer) restockAmount);
	}

	@Override
	public Number getRestockThreshold(Dish dish) {
		return dish.getDishManager().getThreshold();
	}

	@Override
	public Number getRestockAmount(Dish dish) {
		return dish.getDishManager().getAmount();
	}

	@Override
	public Map<Ingredient, Number> getRecipe(Dish dish) {
		return dish.getRecipe();
	}

	@Override
	public Map<Dish, Number> getDishStockLevels() {
		Map<Dish, Number> dishStockLevels = new HashMap<Dish, Number>();
		for(Dish dish : Inventory.dishList) {
			dishStockLevels.put(dish, dish.getDishManager().getAmount());
		}
		return dishStockLevels;
	}

	@Override
	public List<Ingredient> getIngredients() {
		return Inventory.ingredientList;
	}

	@Override
	public Ingredient addIngredient(String name, String unit, Supplier supplier, Number restockThreshold,
			Number restockAmount) {
		Ingredient ingNew = new Ingredient(name, unit, supplier);
		ingNew.getIngredientManager().setThreshold((Integer) restockThreshold);
		ingNew.getIngredientManager().setRestock((Integer) restockAmount);
		Inventory.ingredientList.add(ingNew);
		Comms.sendIngredientList(Inventory.ingredientList);
		return ingNew;
	}

	@Override
	public void removeIngredient(Ingredient ingredient) throws UnableToDeleteException {
		Inventory.ingredientList.remove(ingredient);
		Comms.sendIngredientList(Inventory.ingredientList);
	}

	@Override
	public void setRestockLevels(Ingredient ingredient, Number restockThreshold, Number restockAmount) {
		ingredient.getIngredientManager().setThreshold((Integer) restockThreshold);
		ingredient.getIngredientManager().setRestock((Integer) restockAmount);
	}

	@Override
	public Number getRestockThreshold(Ingredient ingredient) {
		return ingredient.getIngredientManager().getThreshold();
	}

	@Override
	public Number getRestockAmount(Ingredient ingredient) {
		return ingredient.getIngredientManager().getRestock();
	}

	@Override
	public Map<Ingredient, Number> getIngredientStockLevels() {
		Map<Ingredient, Number> ingStockLevels = new HashMap<Ingredient, Number>();
		for(Ingredient ing : Inventory.ingredientList) {
			ingStockLevels.put(ing, ing.getIngredientManager().getAmount());
		}
		return ingStockLevels;
	}

	@Override
	public List<Supplier> getSuppliers() {
		return Inventory.supplierList;
	}

	@Override
	public Supplier addSupplier(String name, Number distance) {
		Supplier supNew = new Supplier(name, distance);
		Inventory.supplierList.add(supNew);
		Comms.sendSupplierList(Inventory.supplierList);
		return supNew;
	}

	@Override
	public void removeSupplier(Supplier supplier) throws UnableToDeleteException {
		Inventory.supplierList.remove(supplier);
		Comms.sendSupplierList(Inventory.supplierList);
	}

	@Override
	public Number getSupplierDistance(Supplier supplier) {
		return supplier.getDistance();
	}

	@Override
	public List<Drone> getDrones() {
		return Inventory.droneList;
	}

	@Override
	public Drone addDrone(Number speed) {
		Random random = new Random();
		Drone droneNew = new Drone((Integer) speed, "Drone " + random.nextInt(40), new Inventory(), 0);
		Inventory.droneList.add(droneNew);
		Comms.sendDroneList(Inventory.droneList);
		return droneNew;
	}

	@Override
	public void removeDrone(Drone drone) throws UnableToDeleteException {
		Inventory.droneList.remove(drone);
		Comms.sendDroneList(Inventory.droneList);
	}

	@Override
	public Number getDroneSpeed(Drone drone) {
		return drone.getSpeed();
	}

	@Override
	public String getDroneStatus(Drone drone) {
		return drone.getStatus();
	}

	@Override
	public List<Staff> getStaff() {
		return Inventory.staffList;
	}

	@Override
	public Staff addStaff(String name) {
		Staff staffNew = new Staff(name, new Inventory());
		Inventory.staffList.add(staffNew);
		Comms.sendStaffList(Inventory.staffList);
		return staffNew;
	}

	@Override
	public void removeStaff(Staff staff) throws UnableToDeleteException {
		Inventory.staffList.remove(staff);
		Comms.sendStaffList(Inventory.staffList);
	}

	@Override
	public String getStaffStatus(Staff staff) {
		for(Staff s : Inventory.staffList) {
		    if(s.getName().equals(staff.getName())) {
		    		return s.getJob();
		    }
		}
		return null;
	}

	@Override
	public List<Order> getOrders() {
		return User.orderList;
	}

	@Override
	public void removeOrder(Order order) throws UnableToDeleteException {
		User.orderList.remove(order);
	}

	@Override
	public Number getOrderDistance(Order order) {
		return order.getUser().getPostcode().getDistance();
	}

	@Override
	public boolean isOrderComplete(Order order) {
		return order.isCompleted();
	}

	@Override
	public String getOrderStatus(Order order) {
		return Order.status;
	}

	@Override
	public Number getOrderCost(Order order) {
		return order.getOrderCost();
	}

	@Override
	public List<Postcode> getPostcodes() {
		return ClientSide.postcodeList;
	}

	@Override
	public void addPostcode(String code, Number distance) {
		Postcode postcodeNew = new Postcode((double) distance, code);
		ClientSide.postcodeList.add(postcodeNew);
	}

	@Override
	public void removePostcode(Postcode postcode) throws UnableToDeleteException {
		ClientSide.postcodeList.remove(postcode);
	}

	@Override
	public List<User> getUsers() {
		return ClientSide.userList;
	}

	@Override
	public void removeUser(User user) throws UnableToDeleteException {
		ClientSide.userList.remove(user);
	}

	@Override
	public void addUpdateListener(UpdateListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUpdate() {
		// TODO Auto-generated method stub
		
	}

}

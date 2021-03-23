package common;

import java.util.*;

public class Dish extends Model {

	private String dishName;
	private String dishDesc;
	private Integer dishPrice;
	public Map<Ingredient, Number> recipe = new HashMap<Ingredient, Number>();
	private DishManager dishStock;
	Integer threshold;
	Integer restockAmt;
	Integer currentAmt;
	
	
	public Dish(String dishName, String dishDesc, Integer price, int threshold, int restockAmt) {
		this.dishName = dishName;
		this.dishDesc = dishDesc;
		this.dishPrice = price;
		this.threshold = threshold;
		this.restockAmt = restockAmt;
		
		dishStock = new DishManager(threshold, currentAmt, restockAmt, this);
	}
	
	public String getName() {
		return dishName;
	}

	public Map<Ingredient, Number> getRecipe() {
		return recipe;
	}
	
	public Integer getPrice() {
		return dishPrice;
	}

	public String getDescription() {
		return dishDesc;
	}

	public DishManager getDishManager() {
		return dishStock;
	}

	public void addIngredient(Ingredient ingredient, Integer valueOf) {
		recipe.put(ingredient, valueOf);
	}
	
}

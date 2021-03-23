package common;
import java.util.*;

public class DishManager extends StockManager {

	Integer dishQt;
	public HashMap<Ingredient, Integer> recipe = new HashMap<Ingredient, Integer>();
	private Dish dish;
	
	public DishManager(Integer threshold, Integer restockAmt, Integer currentAmt, Dish dish) {
		super(threshold, restockAmt, currentAmt);
		this.dish = dish;
	}
	
	/** Create a new Dish
	 * 
	 */
	public void manageStock(Integer amount) {
		dishQt += amount;
	}

	@Override
	boolean checkRestock() {
		return (dishQt < threshold);
	}

	@Override
	void use(Integer amount) {
		dishQt -= amount;
	}

	@Override
	boolean checkAmount(Integer amount) {
		return (dishQt > amount);
	}

}

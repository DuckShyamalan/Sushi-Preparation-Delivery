package common;
import java.util.*;

public class IngredientManager extends StockManager {

	Integer threshold;
	Integer restockAmt;
	Integer currentAmt;
	Integer ingQt = 0;
	Ingredient ingredient;
	
	public IngredientManager(Integer threshold, Integer restockAmt, Integer currentAmt, Ingredient ingredient) {
		super(threshold, restockAmt, currentAmt);
		this.ingredient = ingredient;
	}

	

	/** Order new ingredients
	 * @param amount
	 */
	public void manageStock(Integer amount) {
		ingQt += amount;
	}

	@Override
	boolean checkRestock() {
		return (ingQt < threshold);
	}

	@Override
	void use(Integer amount) {
		ingQt -= amount;
	}

	@Override
	boolean checkAmount(Integer amount) {
		return (ingQt > amount);
	}

	public void deliverStock(ArrayList<Ingredient> pickUpList, int restock) {
		// TODO Auto-generated method stub
		
	}
	
}

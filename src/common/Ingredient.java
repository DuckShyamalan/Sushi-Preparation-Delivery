package common;

import java.util.HashMap;

public class Ingredient extends Model {

	private String ingName;
	private String ingUnit;
	private Supplier ingSupplier = new Supplier(ingName, null);
	private IngredientManager ingStock;
	Integer threshold;
	Integer restockAmt;
	Integer currentAmt;
	
	public Ingredient(String ingName, String ingUnit, Supplier ingSupplier) {
		this.ingName = ingName;
		this.ingUnit = ingUnit;
		this.ingSupplier = ingSupplier;
		
		ingStock = new IngredientManager(threshold, restockAmt, currentAmt, this);
	}
	
	public String getName() {
		return ingName;
	}

//	public Object getStockManager() {
//		return ingStock;
//	}

	public Supplier getSupplier() {
		return ingSupplier;
	}

	public IngredientManager getIngredientManager() {
		return ingStock;
	}

}

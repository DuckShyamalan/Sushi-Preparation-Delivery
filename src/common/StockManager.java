package common;

public abstract class StockManager {
	
	Number amount;
	Integer threshold;
	Integer restock;
	
	public StockManager (Integer amount, Integer threshold, Integer restock) {
		this.amount = amount;
		this.threshold = threshold;
		this.restock = restock;
	}
	
	abstract boolean checkRestock();
	abstract void use(Integer amount);
	abstract boolean checkAmount(Integer amount);
	
	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number stock) {
		this.amount = stock;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Integer getRestock() {
		return restock;
	}

	public void setRestock(Integer restock) {
		this.restock = restock;
	}
	

}
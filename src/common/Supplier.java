package common;

public class Supplier extends Model {

	private String supName;
	private Number supDist; 
	
	public Supplier(String supName, Number supDist) {
		this.supName = supName;
		this.supDist = supDist;
	}
	
	public String getName() {
		return supName;
	}
	
	public Number getDistance() {
		return supDist;
	}

	
}

package common;

public class Postcode extends Model {

	private String name;
	private double distance;
	private String postcode;
	
	public Postcode(double distance, String postcode) {
		this.name = postcode;  //might change
		this.distance = distance;
		this.postcode = postcode;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}

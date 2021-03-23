package common;

import java.util.*;

public class Order extends Model {

	private String ordername;
	private String requests;
	private Map<Dish, Number> basket;
	private Postcode postcode;
	private String address;
	private User user;
	private String username;
	private String password;
	
	public static String status;
	
	public Order(String ordername, User user) {
		super();
		this.ordername = ordername;
		this.basket = user.getBasket();
		this.postcode = user.getPostcode();
		this.address = user.getAddress();
		this.user = user;
		this.username = user.getName();
		this.password = user.getPassword();
	}

	public Number getOrderCost() {
		if(user == null) {
			user = new User("NAME", "PASSWORD", "So'ton", new Postcode(1.9, "SO42 3XZ"));
		}
		return user.getBasketCost();
	}
	
	@Override
	public String getName() {
		return ordername;
	}
	
	public synchronized String getAddress() {
		return address;
	}
	
	public synchronized Map<Dish, Number> getBasket() {
		return basket;
	}
	
	public User getUser() {
		return user;
	}

	public synchronized String getRequest() {
//		Scanner extras = new Scanner(System.in);
//		System.out.println("Enter a number: ");
//		int n = extras.nextInt(); // Scans the next token of the input as an int.
//		//once finished
//		requests += extras;
//		extras.close();
		return requests;
	}

//	public String getStatus() {
//		//if statement
//		//return "Received";
//		return "Preparing";
//		//return "Out for delivery";
//		//return "Failed";
//	}
	
	public boolean isCompleted() {
		return (status.equals("COMPLETE"));
	}
	
	public synchronized void setAddress(String address) {
		this.address = address;
	}
	
	public synchronized void setRequest(String requests) {
		this.requests = requests;
	}
	
	public static String OrderStatus(String status) {
	    switch (status) {
	      case "COMPLETE":
	        return "COMPLETE";
	      case "PREPARING":
	        return "PREPARING";
	      case "CANCELLED": 
	        return "CANCELLED";
	      default:
	        throw new IllegalArgumentException("No such status: " + status);
	    }
	}

}

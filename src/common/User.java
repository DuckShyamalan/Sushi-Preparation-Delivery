package common;

import java.util.*;

public class User extends Model{

	private String username;
	private String password;
	private String address;
	private Postcode postcode;
	private Map<Dish, Number> basket;
	public static List<Order> orderList = new ArrayList<Order>();
	//private Order blankOrder;
	
	public User(String username, String password, String address, Postcode postcode) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.postcode = postcode;
		
		basket = new HashMap<Dish, Number>();
		orderList = new ArrayList<Order>();
	}

	@Override
	public String getName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Map<Dish, Number> getBasket() {
		return basket;
	}
	
	public Postcode getPostcode() {
		return postcode;
	}
	
	public String getAddress() {
		return address;
	}
	
	public synchronized void setPassword(String password) {
		this.password = password;
	}
	
	public synchronized void setPostcode(Postcode postcode) {
		this.postcode = postcode;
	}
	
	public synchronized void setAddress(String address) {
		this.address = address;
	}
	
	public void editBasket(Dish dish, Number amount) {
		basket.replace(dish, amount);
	}
	
	public Number getBasketCost() {
//		Iterator it = basket.entrySet().iterator();
//		Integer total = 0;
//		while (it.hasNext()) {
//			Map.Entry pair = (Map.Entry)it.next();
//			total += ((Dish) pair.getKey()).getPrice();
//			it.remove();
//		}
//		return total;
		Double totalCost = 0.1;
		Map<Dish, Double> costMap = new HashMap<Dish, Double>();
		for (Map.Entry<Dish, Number> e : basket.entrySet()) {
			Double total = (e.getValue().doubleValue()*e.getKey().getPrice());
			costMap.put(e.getKey(), total);
		}
		
		for (Double d : costMap.values()) {
		    totalCost += d;
		  System.out.println(d);
		}
		//System.out.println(totalCost);
		return totalCost;	
	}
	
	public void replace(Dish dish, Number amount) {
		basket.put(dish, amount);
	}

	public void addDish(Dish dish, Number amount) {
		basket.put(dish, amount);
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void clearBasket() {
		basket.clear();
	}

	public void addOrder(Order order) {
		orderList.add(order);
	}


}

package client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.*;

public class ClientSide implements ClientInterface {

	public static List<User> userList = new ArrayList<User>();  //reg. users
	private List<User> userLogged = new ArrayList<User>();  //logged in users
	public static List<Postcode> postcodeList = new ArrayList<Postcode>();
	
	public ClientSide() {
		userList = new ArrayList<User>();
		userLogged = new ArrayList<User>();
		postcodeList = new ArrayList<Postcode>();
		postcodeList.add(new Postcode(7, "SO17 1BJ"));
		postcodeList.add(new Postcode(18, "SO18 3NU"));
		postcodeList.add(new Postcode(15, "SO23 6NA"));
		postcodeList.add(new Postcode(17, "SO20 4TO"));
		postcodeList.add(new Postcode(6, "SO13 2CC"));
		//Comms.sendPostcodeList(postcodeList);
		
	}

	@Override
	public User register(String username, String password, String address, Postcode postcode) {
		if (username.length() > 20 || username.length() < 5) {
			System.err.println("Registration failed - Please select a username between 5 and 20 characters");
			return null;
		} else if (password.length() < 6) {
			System.err.println("Registration failed - Password must have at least 6 characters");
			return null;
		}
		User newUser = new User(username, password, address, postcode);
		userList.add(newUser);
		userLogged.add(newUser);
		return newUser;
	}

	@Override
	public User login(String username, String password) {
		for (User u : userList) {
			if(username == u.getName() && password == u.getPassword()) {
				userLogged.add(u);
				return u;
			}
		}
		System.err.println("Failed Login");
		return null;
	}

	@Override
	public List<Postcode> getPostcodes() {
		//return Comms.receivePostcodeList();
		return ClientSide.postcodeList;
	}

	@Override
	public List<Dish> getDishes() {
		//return Comms.receiveDishList();
		return Inventory.dishList;
	}

	@Override
	public String getDishDescription(Dish dish) {
		return dish.getDescription();
	}

	@Override
	public Number getDishPrice(Dish dish) {
		return dish.getPrice();
	}

	@Override
	public Map<Dish, Number> getBasket(User user) {
		return user.getBasket();
	}

	@Override
	public Number getBasketCost(User user) {
		return user.getBasketCost();
	}

	@Override
	public void addDishToBasket(User user, Dish dish, Number quantity) {
		user.addDish(dish, quantity);
	}

	@Override
	public void updateDishInBasket(User user, Dish dish, Number quantity) {
		user.editBasket(dish, quantity);
	}

	@Override
	public Order checkoutBasket(User user) {
		Order order = new Order(user.getName() + "'s Order: " + String.valueOf(user.getOrderList().size() + 1), user);
		user.addOrder(order);
		return order;
	}

	@Override
	public void clearBasket(User user) {
		user.clearBasket();
	}

	@Override
	public List<Order> getOrders(User user) {
		if (user == null) {
			user = new User("test", "test", "test", new Postcode(1, "XX00 X00"));
		}
		return user.getOrderList();
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
	public void cancelOrder(Order order) {
		User.orderList.remove(order);
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

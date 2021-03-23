package common;

import java.util.*;

public class Drone extends Model implements Runnable {

	private String droneName;
	private int speed;
	private static int restock = 100;
	private boolean isDelivering = false;
	private boolean isCollecting = false;
	Order order;
	Inventory inventory;
	Ingredient ingredient;
	ArrayList<Ingredient> pickUpList;
	private Thread thread;
	int i;  //mode of drone: 1 - dish delivery; 2 - ingredient collection; anything else - idle
	
//	public static void main(String[] args) {
//		Order order = new Order("California Roll", new User("afs1n17", "password", "Soton", new Postcode(1.9, "SO18 9NN")));
//		Drone drone = new Drone(13, "SUX1", new Inventory(), 1);
//		Thread thread = new Thread(drone);
//		thread.start();
//	}
	
	public Drone(int speed, String droneName, Inventory inventory, int i) {
		this.speed = speed;
		this.droneName = droneName;
		this.inventory = inventory;
		this.i = i;
	}

	@Override
	public String getName() {
		return droneName;
	}
	
	public synchronized void deliver(Order order) throws InterruptedException {
		Order.status = "IN PROGRESS";
		isDelivering = true;
		System.out.println("User: " + order.getUser());
		Postcode postcodeDlv = order.getUser().getPostcode();
		double dist = postcodeDlv.getDistance();
		double flyTime = 2*dist/speed;
		Thread.sleep((long) flyTime);
		System.out.println(droneName + " is flying to " + postcodeDlv);
		for (Dish dish1 : Inventory.dishList) {
			for (Dish dish2 : order.getBasket().keySet()) {
				if (dish1.equals(dish2)) {
					dish1.getDishManager().use(1);
				}
			}
		}
		System.out.println("Order at " + postcodeDlv + " has been delivered");
		Inventory.checkDishStock();
	}
	
	public synchronized void collect(Ingredient ingredient) throws InterruptedException {
		Supplier supply = ingredient.getSupplier();
		double dist = (double) supply.getDistance();
		double flyTime = 2*dist/speed;
		isCollecting = true;
		Thread.sleep((long) flyTime);
		System.out.println(droneName + " is flying to " + supply.getName());
		ingredient.getIngredientManager().manageStock(restock);
		System.out.println("The " + ingredient.getName() + " has been collected.");
		Inventory.checkIngredientStock();
	}

	@Override
	public void run() {
		if(i == 1) {
			try {
				deliver(order);
				Thread.sleep((long)order.getUser().getPostcode().getDistance()*2000/speed);
				System.out.println(droneName + " done");
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		} else if(i == 2) {
			try {
				collect(ingredient);
				Thread.sleep((long) ((double)ingredient.getSupplier().getDistance()*2000/speed));
			} catch (InterruptedException e) {
				System.err.println(e); 
			}
		} else {
			System.out.println("Idle \n Sending to collect ingredients");
			i = 2;
			try {
				collect(ingredient);
				Thread.sleep((long) ((double)ingredient.getSupplier().getDistance()*2000/speed));
			} catch (InterruptedException e) {
				System.err.println(e); 
			}
		}
	}
	
	public String getStatus() {
		if(i == 1) {
			return "Drone is delivering an order";
		} else if(i == 2) {
			return "Drone is collecting ingredients";
		} else {
			return "Drone is idle";
		}
	}

	public Number getSpeed() {
		return speed;
	}

}

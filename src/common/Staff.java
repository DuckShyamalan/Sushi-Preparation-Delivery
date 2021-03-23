package common;
import java.util.*;

public class Staff extends Model implements Runnable {

	public static void main(String[] args) {
		Staff jack = new Staff("Jack", new Inventory());
		Staff jill = new Staff("Jill", new Inventory());
		
		jack.start();
		jill.start();
	}
	private boolean busy;
	private String name;
	private Thread thread;
	Inventory inventory;
	
	public Staff(String name, Inventory inventory) {
		this.name = name;
		System.out.println("Hiring: " + name);
	}
	
	public void start() {
		System.out.println("STARTING ");
		if(thread == null) {
			thread = new Thread(this, name);
			thread.run();
		}
	}
	
	public void run() {
		while(!busy) {
			Random random = new Random();
			prepareDish(Inventory.dishList.get(random.nextInt(Inventory.dishList.size())));
		}
	}
	
	public synchronized void prepareDish(Dish dish) {
		long current = System.currentTimeMillis();
		Integer random = new Random().nextInt(5000) + 1000;
		long end = current + random;
		
		if (dish != null) {
			while(System.currentTimeMillis() < end) {
				busy = true;
			}
			System.out.println(name + " is currently preparing a " + dish);
			Order.status = "PREPARING";
			System.out.println(name + " spent " + random/1000 + " seconds preparing " + dish);
			if(dish.getDishManager().checkRestock()) {
				dish.getDishManager().manageStock(dish.getDishManager().getRestock());
			}
		}
		busy = false;
	}
	
	public String getJob() {
		if(busy) {
			return name + " is currently busy";
		}
		return name + " is idle";
	}

	@Override
	public String getName() {
		return name;
	}

}

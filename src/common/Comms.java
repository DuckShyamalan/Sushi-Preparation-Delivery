package common;
import java.util.*;
import java.io.*;
import java.util.*;

public class Comms implements Serializable {

//	private static DishManager dishStock;
//	private static IngredientManager ingStock;
	
	private static List<Order> orderList = new ArrayList<Order>();
	
	static String basicPath = "/Users/SyedF/Desktop/Uni stuff/COMP1206/labs/Sushi Prep & Delv./bin/Mailbox/";
	static File clientMessage = new File(basicPath + "/Client/" + "/clientMessage.txt");
	File businessMessage = new File(basicPath + "/Business/" + "/businessMessage.txt");
	Thread thread;
	
	
//	public Comms(DishManager dishStock, IngredientManager ingStock) {
//		this.dishStock = dishStock;
//		this.ingStock = ingStock;
//	}
	
	public static void clientSendMessage(Order order) throws IOException {
//		orderList.add(order);
//		List<String> message = Arrays.asList(order.getName(), order.getAddress(), order.getRequest());
//		if (!clientMessage.exists()) {
//			//BufferedWriter writeMessage = new BufferedWriter(new FileWriter(clientMessage, true));
//			//writeMessage.write("Uhh, I'd like 3 Tempura Rolls.. Yeah, soy sauce on all of them, wasabi as well");
//			//writeMessage.close();
//			PrintWriter pWriter = new PrintWriter(clientMessage);
//			pWriter.println(message);
//			pWriter.close();
//			System.out.println("X");
//		} else {
//			clientMessage.delete();
//			PrintWriter pWriter = new PrintWriter(clientMessage);
//			pWriter.println(message + " :YU: ");
//			pWriter.close();
//			System.out.println("zxc");
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Client/" + "/Order" + orderList.size() + ".txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(order);
			oOut.close();
			fOut.close();
		} catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void clientRecieveMessage() {
		
	}
	
	public static void businessSendMessage(List<String> message) throws IOException  {
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Order" + orderList.size() + ".txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(message);
			oOut.close();
			fOut.close();
		} catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public static void sendDishList(List<Dish> listDish) {
		List<Dish> dishList = listDish;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Menu.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(dishList);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void sendPostcodeList(List<Postcode> listPostcode) {
		List<Postcode> postcodeList = listPostcode;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Postcodes.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(postcodeList);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void sendDroneList(List<Drone> listDrone) {
		List<Drone> droneList = listDrone;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Drones.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(droneList);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void sendIngredientList(List<Ingredient> listIngredient) {
		List<Ingredient> ingList = listIngredient;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Ingredients.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(ingList);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void sendStaffList(List<Staff> listStaff) {
		List<Staff> staffList = listStaff;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Staff.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(staffList);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void sendSupplierList(List<Supplier> listSupplier) {
		List<Supplier> supList = listSupplier;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Suppliers.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(supList);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void sendUserList(List<User> listUser) {
		List<User> userList = listUser;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Users.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(userList);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void sendOrder(Order order) {
		Order orderNew = order;
		
		try {
			FileOutputStream fOut = new FileOutputStream(new File(basicPath + "/Business/" + "/Orders.txt"));
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(order);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static List<Dish> receiveDishList() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Business/" + "/Menu.txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (List<Dish>) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static List<Postcode> receivePostcodeList() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Business/" + "/Postcodes.txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (List<Postcode>) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static List<Drone> receiveDroneList() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Business/" + "/Drones.txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (List<Drone>) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static List<Ingredient> receiveIngredientList() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Business/" + "/Ingredients.txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (List<Ingredient>) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static List<Staff> receiveStaffList() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Business/" + "/Staff.txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (List<Staff>) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static List<Supplier> receiveSupplierList() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Business/" + "/Suppliers.txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (List<Supplier>) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static List<User> receiveUserList() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Business/" + "/Users.txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (List<User>) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static List<Order> getOrderList() {
		return orderList;
	}
	
	public static Order receiveOrder() {
		try {
			FileInputStream fInp = new FileInputStream(new File(basicPath + "/Client/" + "/Orders" + orderList.size() + ".txt"));
			ObjectInputStream oInp = new ObjectInputStream(fInp);
			return (Order) oInp.readObject();
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static void businessReceiveMessage() {
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader(basicPath + "/Business/" + "/businessMessage.txt");
			br = new BufferedReader(fr);
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				System.out.println(currentLine);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			try {
				if(br != null) {
					br.close();
				}
				if(fr != null) {
					fr.close();
				}
			} catch(Exception ex) {
				System.out.println(ex);
			}
		}
	}

	public void receiveMessage() throws IOException {
		BufferedReader readMessage = new BufferedReader(new FileReader("clientMessage.txt"));
		StringBuilder sb = new StringBuilder();
	    String line = readMessage.readLine();
	    System.out.println(line);

//	    while (line != null) {
//	        sb.append(line);
//	        sb.append(System.lineSeparator());
//	        line = readMessage.readLine();
//	    }
//	    String everything = sb.toString();
//	    System.out.println(everything);
	}
	
	public static void main(String[] args) throws IOException {
		Comms x = new Comms();
		Order order = new Order("California Roll", new User("afs1n17", "***", "Soton", new Postcode(1.9, "SO18")));
//		x.clientSendMessage(order);
//		x.businessSendMessage();
//		x.receiveMessage();
		
	}
	
	
}

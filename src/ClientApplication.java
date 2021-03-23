
import java.util.*;

import client.ClientInterface;
import client.ClientSide;
import client.ClientWindow;
import common.*;


public class ClientApplication {
	
	public static void main(String[] args) {
		//ClientApplication ca = new ClientApplication(clientSide);
		//ca.initialise();
		initialise();
		launchGUI(new ClientSide());
		
	}
	
	public static ClientSide initialise() {
		Inventory.getDishList();
		return new ClientSide();
	}
	
	public static void launchGUI(ClientSide clientSide) {
		initialise();
		ClientWindow window = new ClientWindow(clientSide);
	}

}

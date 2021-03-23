import server.ServerSide;
import server.ServerWindow;
import common.Inventory;

public class ServerApplication {

	public static void main(String[] args) {
		initialise();
		launchGUI(new ServerSide());
		
	}
	
	public static ServerSide initialise() {
		Inventory.getDishList();
		return new ServerSide();
	}
	
	public static void launchGUI(ServerSide serverSide) {
		initialise();
		ServerWindow window = new ServerWindow(serverSide);
	}

}

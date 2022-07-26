import control.GameController;

public class Main {

	public static void main(String[] args) throws Exception {
		GameController controller = new GameController(800);
		controller.startThread();
	}
}

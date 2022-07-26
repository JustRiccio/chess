package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	private GamePanel gamePanel;

	public GameView(int dim) {
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setTitle("Chess");

		gamePanel = new GamePanel(dim);
		this.add(gamePanel);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}
}

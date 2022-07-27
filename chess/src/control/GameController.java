package control;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.GameModel;
import view.GameView;

public class GameController implements Runnable, MouseListener {
	private GameView view;
	private GameModel model;
	private Thread mainThread;

	public GameController(int viewDim) {
		view = new GameView(viewDim);
		model = new GameModel();
		view.getGamePanel().addMouseListener(this);
	}

	public void startThread() {
		mainThread = new Thread(this);
		mainThread.start();
	}

	public void run() {
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();

		while (true) {
			// refresh
			view.getGamePanel().refresh(model.getMoves());

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = 32 - timeDiff;
			if (sleep < 0) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			beforeTime = System.currentTimeMillis();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int cellDim = view.getGamePanel().getCellDim();
		Point coordinates = view.getGamePanel().getMousePosition();
		int row = (int) coordinates.getY() / cellDim;
		int col = (int) coordinates.getX() / cellDim;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

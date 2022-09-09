package control;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

import model.GameModel;
import view.GameView;

public class GameController implements Runnable, MouseListener {
	private GameView view;
	private GameModel model;
	private Thread mainThread;
	public static boolean TURN = false; // white start
	private Semaphore mutex;
	public static boolean PLAYING = true;

	public GameController(int viewDim) {
		view = new GameView(viewDim);
		model = new GameModel();
		view.getGamePanel().addMouseListener(this);
		mutex = new Semaphore(1);
	}

	public void startThread() {
		mainThread = new Thread(this);
		mainThread.start();
	}

	public void run() {
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();

		while (PLAYING) {
			// refresh
			view.getGamePanel().refresh(model.getMoves(), model.getBoard());

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
		
		// show the winner
		String winner = TURN ? "Black wins" : "White wins";
		JOptionPane.showMessageDialog(view, winner);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			mutex.acquire();
			
			int cellDim = view.getGamePanel().getCellDim();
			Point coordinates = view.getGamePanel().getMousePosition();
			int row = (int) coordinates.getY() / cellDim;
			int col = (int) coordinates.getX() / cellDim;

			model.calculateMoves(row, col);

			mutex.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
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

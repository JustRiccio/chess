package control;

import view.GameView;

public class GameController implements Runnable {
	private GameView view;
	private Thread mainThread;

	public GameController(int viewDim) {
		view = new GameView(viewDim);
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
			view.getGamePanel().repaint();

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
}

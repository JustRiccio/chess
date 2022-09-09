package view;

import java.awt.*;

import javax.swing.JPanel;

import model.Piece;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private int cellDim;
	private int[][] moves;
	private Piece[][] board;

	public GamePanel(int dim) {
		this.setPreferredSize(new Dimension(dim, dim));
		this.setDoubleBuffered(true);

		cellDim = dim / 8;
	}

	public void refresh(int[][] moves, Piece[][] board) {
		this.moves = moves;
		this.board = board;
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		// drawing the board
		Color cellBackground = Color.white;
		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < 64; i++) {
			if (i % 8 != 0) {
				if (cellBackground == Color.white)
					cellBackground = new Color(102, 51, 0);
				else
					cellBackground = Color.white;
			}

			g.setColor(cellBackground);
			g.fillRect(i % 8 * cellDim, i / 8 * cellDim, cellDim, cellDim);

			// drawing the lines
			g.setColor(Color.black);
			g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.drawRect(i % 8 * cellDim, i / 8 * cellDim, cellDim, cellDim);
		}

		// drawing the pieces
		if (board != null)
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					Piece piece = board[i][j];
					if (piece != null) {
						g2.drawImage(piece.getIcon().getImage(), j * cellDim, i * cellDim, cellDim, cellDim, null);
					}
				}
			}

		// drawing the moves
		int circleDim = 0;
		Color circleColor = Color.black;

		if (moves != null)
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					// simple move
					if (moves[i][j] == 1) {
						circleDim = cellDim / 5;
						circleColor = Color.blue;
					}

					// can take
					else if (moves[i][j] == 2) {
						circleDim = cellDim / 4;
						circleColor = Color.red;
					}
					
					else if (moves[i][j] == 3) {
						circleDim = cellDim / 3;
						circleColor = Color.green;
					}

					// there is no move
					else
						circleDim = 0;

					if (circleDim > 0) {
						g.setColor(circleColor);
						g.fillOval(j * cellDim + cellDim / 2 - circleDim / 2, i * cellDim + cellDim / 2 - circleDim / 2,
								circleDim, circleDim);
					}
				}
			}
	}

	public int getCellDim() {
		return cellDim;
	}

}

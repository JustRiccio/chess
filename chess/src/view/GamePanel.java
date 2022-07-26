package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private int cellDim;

	public GamePanel(int dim) {
		this.setPreferredSize(new Dimension(dim, dim));
		this.setDoubleBuffered(true);
		
		cellDim = dim / 8;
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

		// TODO: drawing the pieces

		// TODO: drawing the moves
	}

}

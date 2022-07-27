package model;

public class GameModel {
	private int[][] moves = new int[8][8];

	public GameModel() {
		generateMatrix();
	}

	private void generateMatrix() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				moves[i][j] = 0;
			}
		}
	}

	public int[][] getMoves() {
		return moves;
	}
}

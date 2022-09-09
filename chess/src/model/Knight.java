package model;

public class Knight extends Piece {
	private static final String imgName = "Knight";

	public Knight(boolean color, int r, int c) {
		super(color, imgName, r, c);
	}

	int xOffset[] = { 1, 1, -1, -1, 2, 2, -2, -2 };
	int yOffset[] = { 2, -2, 2, -2, 1, -1, 1, -1 };

	public void calculateMoves(int[][] moves, Piece[][] board, int row, int col) {
		copyBoard(board, tmpBoard);
		King king = (King) findKing(board, this.color);

		for (int i = 0; i < xOffset.length; i++) {
			int dX = row + xOffset[i];
			int dY = col + yOffset[i];

			if (isValid(dX, dY)) {
				if (board[dX][dY] == null && !king.isUnderCheck(this, board, dX, dY))
					moves[dX][dY] = 1;
				else if (board[dX][dY] != null && board[dX][dY].color != this.color
						&& !king.isUnderCheck(this, board, dX, dY)) {
					int grade = board[dX][dY] instanceof King ? 3 : 2;
					moves[dX][dY] = grade;
				}
			}
		}
	}
}

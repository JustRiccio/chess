package model;

public class Rook extends Piece {
	private boolean moved; // if set to false, allows to castle
	private static final String imgName = "Rook";

	public Rook(boolean color, int r, int c) {
		super(color, imgName, r, c);
		moved = false;
	}

	public void calculateMoves(int[][] moves, Piece[][] board, int row, int col) {
		int i;
		copyBoard(board, tmpBoard);
		King king = (King) findKing(board, this.color);

		// top
		i = row - 1;
		while (isValid(i, col) && board[i][col] == null) {
			if (!king.isUnderCheck(this, board, i, col))
				moves[i][col] = 1;
			i--;
		}
		if (isValid(i, col) && board[i][col] != null && board[i][col].color != this.color) {
			int grade = board[i][col] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, i, col))
				moves[i][col] = grade;
		}

		// down
		i = row + 1;
		while (isValid(i, col) && board[i][col] == null) {
			if (!king.isUnderCheck(this, board, i, col))
				moves[i][col] = 1;
			i++;
		}
		if (isValid(i, col) && board[i][col] != null && board[i][col].color != this.color) {
			int grade = board[i][col] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, i, col))
				moves[i][col] = grade;
		}

		// left
		i = col - 1;
		while (isValid(row, i) && board[row][i] == null) {
			if (!king.isUnderCheck(this, board, row, i))
				moves[row][i] = 1;
			i--;
		}
		if (isValid(row, i) && board[row][i] != null && board[row][i].color != this.color) {
			int grade = board[row][i] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, row, i))
				moves[row][i] = grade;
		}

		// right
		i = col + 1;
		while (isValid(row, i) && board[row][i] == null) {
			if (!king.isUnderCheck(this, board, row, i))
				moves[row][i] = 1;
			i++;
		}
		if (isValid(row, i) && board[row][i] != null && board[row][i].color != this.color) {
			int grade = board[row][i] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, row, i))
				moves[row][i] = grade;
		}
	}
}

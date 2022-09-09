package model;

public class Bishop extends Piece {
	private static final String imgName = "Bishop";

	public Bishop(boolean color, int r, int c) {
		super(color, imgName, r, c);
	}

	public void calculateMoves(int[][] moves, Piece[][] board, int row, int col) {
		int i, j;
		copyBoard(board, tmpBoard);
		King king = (King) findKing(board, this.color);

		// top right
		i = row - 1;
		j = col + 1;
		while (isValid(i, j) && board[i][j] == null) {
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = 1;
			
			i--;
			j++;
		}
		if (isValid(i, j) && board[i][j].color != this.color) {
			int grade = board[i][j] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = grade;
		}

		// top left
		i = row - 1;
		j = col - 1;
		while (isValid(i, j) && board[i][j] == null) {
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = 1;
			
			i--;
			j--;
		}
		if (isValid(i, j) && board[i][j].color != this.color) {
			int grade = board[i][j] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = grade;
		}

		// bottom right
		i = row + 1;
		j = col + 1;
		while (isValid(i, j) && board[i][j] == null) {
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = 1;
			
			i++;
			j++;
		}
		if (isValid(i, j) && board[i][j].color != this.color) {
			int grade = board[i][j] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = grade;
		}

		// bottom left
		i = row + 1;
		j = col - 1;
		while (isValid(i, j) && board[i][j] == null) {
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = 1;
			
			i++;
			j--;
		}
		if (isValid(i, j) && board[i][j].color != this.color) {
			int grade = board[i][j] instanceof King ? 3 : 2;
			if (!king.isUnderCheck(this, board, i, j))
				moves[i][j] = grade;
		}
	}
}

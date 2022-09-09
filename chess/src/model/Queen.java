package model;

public class Queen extends Piece {
	private static final String imgName = "Queen";

	public Queen(boolean color, int r, int c) {
		super(color, imgName, r, c);
	}

	public void calculateMoves(int[][] moves, Piece[][] board, int row, int col) {
		int i, j;
		copyBoard(board, tmpBoard);
		King king = (King) findKing(board, this.color);

		// copied from Bishop
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

		// copied from Rook
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

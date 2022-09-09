package model;

public class Pawn extends Piece {
	private boolean moved; // if set to false, allows to move up to 2 cells on the first move, otherwise
							// move normally
	private static final String imgName = "Pawn";

	public Pawn(boolean color, int r, int c) {
		super(color, imgName, r, c);
		moved = false;
	}

	public void calculateMoves(int[][] moves, Piece[][] board, int row, int col) {
		copyBoard(board, tmpBoard);
		King king = (King) findKing(board, this.color);

		// white pawn
		if (!color) {
			// normal move
			if (board[row - 1][col] == null) {
				if (!king.isUnderCheck(this, board, row - 1, col))
					moves[row - 1][col] = 1;

				// first move
				if (!moved)
					if (board[row - 2][col] == null)
						if (!king.isUnderCheck(this, board, row - 2, col)) {
							moves[row - 2][col] = 1;
						}
			}

			// can take left
			if (col > 0 && board[row - 1][col - 1] != null && board[row - 1][col - 1].color != this.color) {
				int grade = board[row - 1][col - 1] instanceof King ? 3 : 2;

				if (!king.isUnderCheck(this, board, row - 1, col - 1))
					moves[row - 1][col - 1] = grade;
			}

			// can take right
			if (col < 7 && board[row - 1][col + 1] != null && board[row - 1][col + 1].color != this.color) {
				int grade = board[row - 1][col + 1] instanceof King ? 3 : 2;

				if (!king.isUnderCheck(this, board, row - 1, col + 1))
					moves[row - 1][col + 1] = grade;
			}

			// TODO en passant
		}

		// black pawn
		else {
			// normal move
			if (board[row + 1][col] == null) {
				if (!king.isUnderCheck(this, board, row + 1, col))
					moves[row + 1][col] = 1;

				// first move
				if (!moved)
					if (board[row + 2][col] == null)
						if (!king.isUnderCheck(this, board, row + 1, col)) {
							moves[row + 2][col] = 1;
						}
			}

			// can take left
			if (col > 0 && board[row + 1][col - 1] != null && board[row + 1][col - 1].color != this.color) {
				int grade = board[row + 1][col - 1] instanceof King ? 3 : 2;

				if (!king.isUnderCheck(this, board, row + 1, col - 1))
					moves[row + 1][col - 1] = grade;
			}

			// can take right
			if (col < 7 && board[row + 1][col + 1] != null && board[row + 1][col + 1].color != this.color) {
				int grade = board[row + 1][col + 1] instanceof King ? 3 : 2;

				if (!king.isUnderCheck(this, board, row + 1, col + 1))
					moves[row + 1][col + 1] = grade;
			}

			// TODO en passant
		}
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}

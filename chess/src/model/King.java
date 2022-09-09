package model;

public class King extends Piece {
	private boolean moved; // if set to false, allows to castle
	private static final String imgName = "King";

	public King(boolean color, int r, int c) {
		super(color, imgName, r, c);
		moved = false;
	}

	int[] xOffset = { -1, -1, -1, 0, 0, 1, 1, 1 };
	int[] yOffset = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public void calculateMoves(int[][] moves, Piece[][] board, int row, int col) {

		for (int i = 0; i < xOffset.length; i++) {
			int dX = row + xOffset[i];
			int dY = col + yOffset[i];

			if (isValid(dX, dY)) {
				if (board[dX][dY] == null)
					moves[dX][dY] = 1;
				else if (board[dX][dY] != null && board[dX][dY].color != this.color) {
					int grade = board[dX][dY] instanceof King ? 3 : 2;
					moves[dX][dY] = grade;
				}
			}
		}
	}

	public boolean simulateMove(Piece piece, Piece[][] board, int pieceRow, int pieceCol) {

		// knight's moves
		int xOffset[] = { 1, 1, -1, -1, 2, 2, -2, -2 };
		int yOffset[] = { 2, -2, 2, -2, 1, -1, 1, -1 };

		for (int i = 0; i < xOffset.length; i++) {
			int dX = this.row + xOffset[i];
			int dY = this.col + yOffset[i];

			if (isValid(dX, dY)) {
				if (board[dX][dY] != null && board[dX][dY] instanceof Knight
						&& board[dX][dY].color != this.color)
					return true;
			}
		}

		int i;
		Piece tmpPiece = null;

		// straight moves
		// top
		i = this.row - 1;
		while (isValid(i, col) && board[i][col] == null) {
			i--;
		}
		if (isValid(i, col))
			tmpPiece = board[i][col];
		if (tmpPiece != null && tmpPiece.color != this.color) {
			if (tmpPiece instanceof Queen || tmpPiece instanceof Rook)
				return true;
			if (tmpPiece instanceof King && i == row - 1)
				return true;
		}

		// bottom
		i = this.row + 1;
		while (isValid(i, col) && board[i][col] == null) {
			i--;
		}
		if (isValid(i, col))
			tmpPiece = board[i][col];
		if (tmpPiece != null && tmpPiece.color != this.color) {
			if (tmpPiece instanceof Queen || tmpPiece instanceof Rook)
				return true;
			if (tmpPiece instanceof King && i == row + 1)
				return true;
		}

		// right
		i = this.col + 1;
		while (isValid(row, i) && board[row][i] == null) {
			i++;
		}
		if (isValid(row, i))
			tmpPiece = board[row][i];
		if (tmpPiece != null && tmpPiece.color != this.color) {
			if (tmpPiece instanceof Queen || tmpPiece instanceof Rook)
				return true;
			if (tmpPiece instanceof King && i == col + 1)
				return true;
		}

		// left
		i = this.col - 1;
		while (isValid(row, i) && board[row][i] == null) {
			i++;
		}
		if (isValid(row, i))
			tmpPiece = board[row][i];
		if (tmpPiece != null && tmpPiece.color != this.color) {
			if (tmpPiece instanceof Queen || tmpPiece instanceof Rook)
				return true;
			if (tmpPiece instanceof King && i == col - 1)
				return true;
		}

		int j;

		// diagonal moves
		// top left
		i = this.row - 1;
		j = this.col - 1;
		while (isValid(i, j) && board[i][j] == null) {
			i--;
			j--;
		}
		if (isValid(i, j))
			tmpPiece = board[i][j];
		if (tmpPiece != null) {
			if (tmpPiece.color != this.color) {
				if ((tmpPiece instanceof King || tmpPiece instanceof Pawn) && i == row - 1 && j == col - 1)
					return true;
				if (tmpPiece instanceof Queen && tmpPiece instanceof Bishop)
					return true;
			}
		}

		// top right
		i = this.row - 1;
		j = this.col + 1;
		while (isValid(i, j) && board[i][j] == null) {
			i--;
			j++;
		}
		if (isValid(i, j))
			tmpPiece = board[i][j];
		if (tmpPiece != null) {
			if (tmpPiece.color != this.color) {
				if ((tmpPiece instanceof King || tmpPiece instanceof Pawn) && i == row - 1 && j == col + 1)
					return true;
				if (tmpPiece instanceof Queen && tmpPiece instanceof Bishop)
					return true;
			}
		}

		// bottom left
		i = this.row + 1;
		j = this.col - 1;
		while (isValid(i, j) && board[i][j] == null) {
			i++;
			j--;
		}
		if (isValid(i, j))
			tmpPiece = board[i][j];
		if (tmpPiece != null) {
			if (tmpPiece.color != this.color) {
				if (tmpPiece instanceof King && i == row + 1 && j == col - 1)
					return true;
				if (tmpPiece instanceof Queen && tmpPiece instanceof Bishop)
					return true;
			}
		}

		// bottom right
		i = this.row + 1;
		j = this.col + 1;
		while (isValid(i, j) && board[i][j] == null) {
			i++;
			j++;
		}
		if (isValid(i, j))
			tmpPiece = board[i][j];
		if (tmpPiece != null) {
			if (tmpPiece.color != this.color) {
				if (tmpPiece instanceof King && i == row + 1 && j == col + 1)
					return true;
				if (tmpPiece instanceof Queen && tmpPiece instanceof Bishop)
					return true;
			}
		}

		return false;
	}

	public boolean isUnderCheck(Piece piece, Piece[][] board, int row, int col) {
		Piece[][] tmpBoard = new Piece[8][8];
		copyBoard(board, tmpBoard);
		
		// move the piece
//		Piece oldPiece = tmpBoard[row][col];
		tmpBoard[piece.getRow()][piece.getCol()] = null;
		tmpBoard[row][col] = piece;

		// start the simulation
		boolean flag = simulateMove(piece, tmpBoard, row, col);
		
		return flag;
	}

}

package model;

public class GameModel {
	private int[][] moves = new int[8][8];
	private Piece[][] board = new Piece[8][8];
	private static final String startingPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

	public GameModel() {
		generateMatrix();
		initBoard();
	}

	private void generateMatrix() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				moves[i][j] = 0;
				board[i][j] = null;
			}
		}
	}

	private void initBoard() {
		int col = 0;
		int row = 0;
		for (int i = 0; i < startingPosition.length(); i++) {
			char c = startingPosition.charAt(i);

			if (c == '/') {
				col = 0;
				row++;
			} else if (c > '0' && c < '9') {
				col += c - '0';
			} else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				// System.out.println(row + " " + col);
				Boolean color = !(Character.isUpperCase(c));
				c = Character.toLowerCase(c);
				switch (c) {
					case 'r':
						board[row][col] = new Rook(color, row, col);
						break;
					case 'b':
						board[row][col] = new Bishop(color, row, col);
						break;
					case 'n':
						board[row][col] = new Knight(color, row, col);
						break;
					case 'q':
						board[row][col] = new Queen(color, row, col);
						break;
					case 'k':
						board[row][col] = new King(color, row, col);
						break;
					case 'p':
						board[row][col] = new Pawn(color, row, col);
						break;
				}
				col++;
				// if (col == 8) {
				// 	col = 0;
				// 	row++;
				// }
			}
		}
	}

	public int[][] getMoves() {
		return moves;
	}

	public Piece[][] getBoard() {
		return board;
	}

	// public void calculateMoves()
}

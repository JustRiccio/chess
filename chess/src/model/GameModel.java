package model;

import java.util.ArrayList;

import control.GameController;

public class GameModel {
	private int[][] moves = new int[8][8];
	private Piece[][] board = new Piece[8][8];
	private static final String startingPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
//	private static final String startingPosition = "rnbqkbnr/pppppppp/8/4Kk2/8/8/PPPPPPPP/RNBQKBNR";
	private Piece oldPiece = null;
	
//	public static ArrayList<Piece> bPieces = null;
//	public static ArrayList<Piece> wPieces = null;
	public static ArrayList<Piece> pieces = null;
	
//	public static boolean wKing = false; // indicates if the white king is under attack
//	public static boolean bKing = false; // indicates if the black king is under attack

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

	public void resetMatrix(int[][] matrix) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	private void initBoard() {
		int col = 0;
		int row = 0;
//		bPieces = new ArrayList<>();
//		wPieces = new ArrayList<>();
		pieces = new ArrayList<>();
		
		for (int i = 0; i < startingPosition.length(); i++) {
			char c = startingPosition.charAt(i);

			if (c == '/') {
				col = 0;
				row++;
			} else if (c > '0' && c < '9') {
				col += c - '0';
			} else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				Boolean color = !(Character.isUpperCase(c));
				Piece piece = null;
				c = Character.toLowerCase(c);
				switch (c) {
				case 'r':
					piece = new Rook(color, row, col);
					break;
				case 'b':
					piece = new Bishop(color, row, col);
					break;
				case 'n':
					piece = new Knight(color, row, col);
					break;
				case 'q':
					piece = new Queen(color, row, col);
					break;
				case 'k':
					piece = new King(color, row, col);
					break;
				case 'p':
					piece = new Pawn(color, row, col);
					break;
				}
				board[row][col] = piece;
				pieces.add(piece);
				
//				if (color)
//					bPieces.add(piece);
//				else wPieces.add(piece);
				
				col++;
			}
		}
	}

	public void calculateMoves(int row, int col) {
		Piece piece = board[row][col];

		if (piece != null && moves[row][col] == 0) {
			oldPiece = piece;
			resetMatrix(moves);
			piece.setRow(row);
			piece.setCol(col);
			
			if (piece.color == GameController.TURN)
				piece.calculateMoves(moves, board, row, col);
			else
				resetMatrix(moves);
		} else {
			if (moves[row][col] == 0) {
				resetMatrix(moves);
			} else if (oldPiece != null) {

				// movement
				if (moves[row][col] > 0) {
					// remove the piece from the ArrayList, since I took it
					if (moves[row][col] > 1) {
						pieces.remove(oldPiece);
//						if (GameController.TURN)
//							wPieces.remove(board[oldPiece.getRow()][oldPiece.getCol()]);
//						else
//							bPieces.remove(board[oldPiece.getRow()][oldPiece.getCol()]);
					}
					
					board[oldPiece.getRow()][oldPiece.getCol()] = null;
					board[row][col] = oldPiece;

					// pawn promotion
					if (oldPiece instanceof Pawn) {
						Queen queen = null;
						
						// white promotion
						if (!oldPiece.color && row == 0)
							queen = new Queen(false, row, col);
						// black promotion
						if (oldPiece.color && row == 7)
							queen = new Queen(true, row, col);
						
						pieces.remove(oldPiece);
						pieces.add(queen);
					}

					oldPiece.setMoved(true);

					oldPiece = null;
					resetMatrix(moves);
					
					int cont = 0;
					for (int i = 0; i < pieces.size(); i++) {
						if (pieces.get(i) instanceof King)
							cont++;
					}
					
					if (cont == 1) {
						GameController.PLAYING = false;
						return;
					}
					
					GameController.TURN = !GameController.TURN;
				}
			}
		}
		

	}
	
//	public Piece findKing(boolean color) {
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				Piece piece = board[i][j];
//				if (piece != null && piece instanceof King && piece.color == color)
//					return piece;
//			}
//		}
//		
//		return null;
//	}

	public int[][] getMoves() {
		return moves;
	}

	public Piece[][] getBoard() {
		return board;
	}
}

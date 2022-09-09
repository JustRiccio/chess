package model;

import java.net.URL;

import javax.swing.ImageIcon;

public class Piece {
	protected boolean color; // 0 = white, 1 = black
	protected ImageIcon icon;
	protected int row;
	protected int col;

	// I need to save only the grade of the move
	protected Piece[][] tmpBoard = new Piece[8][8];

	public Piece(boolean color, String name, int row, int col) {
		this.color = color;
		this.row = row;
		this.col = col;

		name = color ? "b" + name : "w" + name;
		name += ".png";
		URL imgUrl = getClass().getResource("/" + name);
		icon = new ImageIcon(imgUrl);
	}

	protected void copyBoard(Piece[][] from, Piece[][] to) {
		for (int i = 0; i < from[0].length; i++) {
			for (int j = 0; j < from[0].length; j++) {
				to[i][j] = from[i][j];
			}
		}
	}

//	public boolean simulateMove(Piece[][] board, int currentRow, int currentCol, int nextRow, int nextCol) {
//		boolean flag = true;
//		board[currentRow][currentCol] = null;
//		Piece prevPiece = board[nextRow][nextCol];
//		board[nextRow][nextCol] = this;
//
//		// check in wPieces, since it's black's turn
//		if (color) {
//			// calculate every move for every white piece
//			for (int i = 0; i < GameModel.wPieces.size(); i++) {
//				Piece piece = GameModel.wPieces.get(i);
//				piece.calculateMoves(null, board, piece.getRow(), piece.getCol());
//			}
//
//			// check if any white piece can take the black king
//			for (int i = 0; i < GameModel.wPieces.size(); i++) {
//				Piece piece = GameModel.wPieces.get(i);
//				for (int j = 0; j < piece.getMoves().size(); j++) {
//					if (piece.getMoves().get(j) == 3) {
//						flag = false;
//						break;
//					}
//				}
//			}
//		}
//
//		// check in bPieces, since it's white's turn
//		else {
//			// calculate every move for every black piece
//			for (int i = 0; i < GameModel.bPieces.size(); i++) {
//				Piece piece = GameModel.bPieces.get(i);
//				piece.calculateMoves(null, board, piece.getRow(), piece.getCol());
//			}
//
//			// check if any black piece can take the white king
//			for (int i = 0; i < GameModel.bPieces.size(); i++) {
//				Piece piece = GameModel.bPieces.get(i);
//				for (int j = 0; j < piece.getMoves().size(); j++) {
//					if (piece.getMoves().get(j) == 3) {
//						flag = false;
//					}
//				}
//			}
//		}
//
//		board[currentRow][currentCol] = this;
//		board[nextRow][nextCol] = prevPiece;
//		return flag;
//	}
	
	public Piece findKing(Piece[][] board, boolean color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = board[i][j];
				if (piece != null && piece instanceof King && piece.color == color)
					return piece;
			}
		}
		
		return null;
	}

	public void calculateMoves(int[][] moves, Piece[][] board, int row, int col) {

	}

	protected boolean isValid(int i, int j) {
		return i >= 0 && i < 8 && j >= 0 && j < 8;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setMoved(boolean moved) {

	}

}
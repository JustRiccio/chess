package model;

import javax.swing.ImageIcon;

public class Piece {
    protected boolean color; // 0 = white, 1 = black
    protected ImageIcon icon;
    protected int row;
    protected int col;

    public Piece(boolean color, String name, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
        name = color ? "b" + name : "w" + name;
        name += ".png";

        icon = new ImageIcon(name);
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

    

}
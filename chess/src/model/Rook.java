package model;

public class Rook extends Piece{
    private boolean moved; // if set to false, allows to castle
    private static final String imgName = "Rook";

    public Rook(boolean color, int r, int c)
    {
        super(color, imgName, r, c);
        moved = false;
    }
}


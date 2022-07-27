package model;

public class King extends Piece{
    private boolean moved; // if set to false, allows to castle
    private static final String imgName = "King";

    public King(boolean color, int r, int c)
    {
        super(color, imgName, r, c);
        moved = false;
    }
}

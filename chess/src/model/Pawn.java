package model;

public class Pawn extends Piece{
    private boolean moved; // if set to false, allows to move up to 2 cells on the first move, otherwise move normally
    private static final String imgName = "Pawn";

    public Pawn(boolean color, int r, int c)
    {
        super(color, imgName, r, c);
        moved = false;
    }
}

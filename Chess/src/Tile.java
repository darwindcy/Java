//File: Tile.java
//Description: Tiles for the Board class
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Checkers                               Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

public class Tile {
    private int x;
    private int y;
    private Piece current_piece;

    public Tile(int x, int y){
        this.current_piece = null;
        this.x = x;
        this.y = y;
    }

    public void put_piece(Piece current_piece){
        this.current_piece = current_piece;
        current_piece.setX(x);
        current_piece.setY(y);
    }

    public void remove_piece(){
        this.current_piece = null;
    }

    public boolean findIfOccupied(){
        if (this.current_piece != null)
            return true;
        else
            return false;
    }

    public Piece getCurrent_piece() {
        return current_piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        if(this.current_piece != null)
            return String.valueOf(this.current_piece.getSymbol());
        else
            return " ";
    }

}

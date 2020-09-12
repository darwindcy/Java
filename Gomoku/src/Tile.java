//File: Tile.java
//Description: Tile file for the game Gomoku
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Gomoku                               Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

public class Tile {
    boolean occupied;
    char occupied_by;

    public Tile(){
        this.occupied = false;
        this.occupied_by = '_';
    }

    public Tile(char symbol){
        this.occupied_by = symbol;
        this.occupied = true;
    }
    public boolean isEquals(Tile aTile){
        if (this.occupied_by == aTile.occupied_by &&
            this.occupied == aTile.occupied)
            return true;
        else
            return false;
    }

    public String toString(){
        return String.valueOf(occupied_by);
    }

}

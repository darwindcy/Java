//File: Board.java
//Description: Board file for the game Gomoku
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Gomoku                               Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//-------------------------------------------------------------------------------------------------

public class Board {
    Tile[][] tiles;
    private int tileRows;
    private int tileCols;

    public Board(int numRows, int numCols){
        this.tiles = new Tile[numRows + 1][numCols + 1];
        this.tileRows = numRows + 1;
        this.tileCols = numCols + 1;
    }

    public void print_board(){
        for(int i = 0; i < this.tileRows ; i++){
            for(int j = 0; j < this.tileCols ; j++){
                if(j == 0 && i != 0) {
                    System.out.print(" " + (int) (this.tiles[i][j].toString()).charAt(0) + " ");
                    if (i < 10)
                        System.out.print(" ");
                } else {
                    System.out.print(" " + this.tiles[i][j].toString() + " ");
                    if(i == 0 && j == 0)
                        System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int get_no_of_rows(){
        return tileRows;
    }
    public int get_no_of_cols(){
        return tileCols;
    }
}

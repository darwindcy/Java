//File: Board.java
//Description:
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Checkers                             Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

public class Board {
    Tile[][] tiles;
    private int tileRows;
    private int tileCols;

    public Board(int numRows, int numCols) {
        this.tiles = new Tile[numRows][numCols];
        this.tileRows = numRows;
        this.tileCols = numCols;
    }

    public void build_board() {
        for (int i = 0; i < this.tileRows; i++)
            for (int j = 0; j < this.tileCols; j++) {
                this.tiles[i][j] = new Tile(i, j);
            }
    }

    public void print_board() {
        print_row_header_alphabets();
        for (int i = 0; i < this.tileCols; i++) {
            print_horizontal_line();

            if (i + 1 < 10)
                System.out.print(i + 1 + "  ");
            else
                System.out.print(i + 1 + " ");

            for (int j = 0; j < this.tileRows; j++) {
                System.out.print("| " + this.tiles[j][i].toString() + " ");
            }
            System.out.println("|");
        }
        print_horizontal_line();
    }

    public void print_row_header_alphabets() {
        System.out.print("  ");
        for (int i = 0; i < this.tileRows; i++) {
            System.out.print("   " + (char) (i + 65));
        }
        System.out.println();
    }

    public void print_horizontal_line() {
        System.out.print("   ");
        for (int j = 0; j < this.tileRows; j++) {
            System.out.print("----");
        }
        System.out.println();
    }

    public void move(Tile fromTile, Tile toTile) {
        toTile.put_piece(fromTile.getCurrent_piece());
        fromTile.remove_piece();
    }

    public int get_no_of_rows() {
        return tileRows;
    }

    public int get_no_of_cols() {
        return tileCols;
    }
}

//File: Piece.java
//Description:
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Checkers                             Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

public class Piece {
    private char status;
    private char symbol;

    public Piece(char symbol){
        this.status = 'N';
        this.symbol = symbol;
    }

    public boolean opposingPiece(Piece piece1){
        try {
            if (piece1.symbol == 'D' && this.symbol == 'L')
                return true;
            else if (piece1.symbol == 'L' && this.symbol == 'D')
                return true;
            else
                return false;
        }catch (NullPointerException error){
            return false;
        }
    }

    public void upStatus() {
        this.status = 'K';
    }

    public char getSymbol() {
        return symbol;
    }

    public char getStatus(){
        return status;
    }

}

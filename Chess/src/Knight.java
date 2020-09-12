import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(char color) {
        super("night", color);
    }

    @Override
    public ArrayList<Move> moveList(Chess chessBoard) {

        ArrayList<Move> Moves= new ArrayList<Move>();

        int currentX = this.getX();
        int currentY = this.getY();
        Tile currentTile = chessBoard.tiles[currentX][currentY];

            addMoveIfValid(Moves, currentTile, chessBoard, currentX + 1, currentY - 2);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX + 2, currentY - 1);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX + 1, currentY + 2);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX + 2, currentY + 1);

            addMoveIfValid(Moves, currentTile, chessBoard, currentX - 1 ,currentY + 2);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX - 2, currentY + 1);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX - 1, currentY - 2);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX - 2, currentY - 1);

        return Moves;
    }
    private void addMoveIfValid(ArrayList<Move> allMoves, Tile fromTile,
                                Chess chessBoard, int intendedX, int intendedY){
        if((intendedX >=0 && intendedX < chessBoard.get_no_of_cols()) &&
                (intendedY >= 0 && intendedY < chessBoard.get_no_of_rows())) {
            if (!chessBoard.tiles[intendedX][intendedY].findIfOccupied())
                allMoves.add(new Move(fromTile, chessBoard.tiles[intendedX][intendedY]));
            else {
                if (this.getColor() != chessBoard.tiles[intendedX][intendedY].getCurrent_piece().getColor())
                    allMoves.add(new Move(fromTile, chessBoard.tiles[intendedX][intendedY]));
            }
        }
    }
}


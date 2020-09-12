import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {

    private Icon pawnIcon;

    public Pawn(char color){
        super("pawn", color);
        if(color == 'B')
            this.pawnIcon = new ImageIcon("pieces/pawn_dark.png");
        else
            this.pawnIcon = new ImageIcon("pieces/pawn_light.png");
    }

    @Override
    public Icon getIcon() {
        return pawnIcon;
    }


    @Override
    public ArrayList<Move> moveList(Chess chessBoard) {

        ArrayList<Move> Moves= new ArrayList<Move>();

        int currentX = this.getX();
        int currentY = this.getY();
        Tile currentTile = chessBoard.tiles[currentX][currentY];

        if(this.getColor() == 'W'){
            if(currentY == 6 && !chessBoard.tiles[currentX][currentY - 1].findIfOccupied()
                    && !chessBoard.tiles[currentX][currentY - 2].findIfOccupied()) {
                Moves.add(new Move(currentTile, chessBoard.tiles[currentX][currentY - 2]));
            }
            if(currentY - 1 >= 0 && !chessBoard.tiles[currentX][currentY - 1].findIfOccupied())
                Moves.add(new Move(currentTile, chessBoard.tiles[currentX][currentY - 1]));

            addMoveIfValid(Moves, currentTile, chessBoard, currentX - 1, currentY - 1);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX + 1, currentY - 1);
        } else if(this.getColor() == 'B'){
            if(currentY == 1 && !chessBoard.tiles[currentX][currentY + 1].findIfOccupied() &&
                    !chessBoard.tiles[currentX][currentY + 2].findIfOccupied()) {
                Moves.add(new Move(currentTile, chessBoard.tiles[currentX][currentY + 2]));
            }
            if(currentY + 1 < chessBoard.get_no_of_rows() &&
                    !chessBoard.tiles[currentX][currentY + 1].findIfOccupied())
                Moves.add(new Move(currentTile, chessBoard.tiles[currentX][currentY + 1]));
            addMoveIfValid(Moves, currentTile, chessBoard, currentX - 1, currentY + 1);
            addMoveIfValid(Moves, currentTile, chessBoard, currentX + 1, currentY + 1);
        }
        return Moves;
    }
    private void addMoveIfValid(ArrayList<Move> allMoves, Tile fromTile,
                                Chess chessBoard, int intendedX, int intendedY){
        if((intendedX >=0 && intendedX < chessBoard.get_no_of_cols()) &&
                (intendedY >= 0 && intendedY < chessBoard.get_no_of_rows())) {
            if (chessBoard.tiles[intendedX][intendedY].findIfOccupied()){
                if (this.getColor() != chessBoard.tiles[intendedX][intendedY].getCurrent_piece().getColor())
                    allMoves.add(new Move(fromTile, chessBoard.tiles[intendedX][intendedY]));
            }
        }
    }
}

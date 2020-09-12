import javax.swing.*;
import java.util.ArrayList;

public class Bishop extends Piece {
    private Icon bishopIcon;

    public Bishop(char color) {
        super("bishop", color);
        if(color == 'B')
            this.bishopIcon = new ImageIcon("pieces/bishop_dark.png");
        else
            this.bishopIcon = new ImageIcon("pieces/bishop_light.png");
    }
    @Override
    public Icon getIcon() {
        return bishopIcon;
    }


    @Override
    public ArrayList<Move> moveList(Chess chessBoard) {

        ArrayList<Move> Moves = new ArrayList<Move>();


        int currentX = this.getX();
        int currentY = this.getY();
        Tile currentTile = chessBoard.tiles[currentX][currentY];

        for (int i = 1; i < chessBoard.get_no_of_cols(); i++) {
            if ((currentX+i < chessBoard.get_no_of_cols()) && (currentY + i < chessBoard.get_no_of_cols())){
                if (!chessBoard.tiles[currentX + i][currentY + i].findIfOccupied()) {
                    Moves.add(new Move(currentTile, chessBoard.tiles[currentX + i][currentY + i]));
                } else {
                    if (chessBoard.tiles[currentX + i][currentY + i].getCurrent_piece().getColor() == this.getColor()) {
                        break;
                    } else {
                        Moves.add(new Move(currentTile, chessBoard.tiles[currentX + i][currentY + i]));
                        break;
                    }
                }
            }
        }

        for (int i = 1; i < chessBoard.get_no_of_cols(); i++) {
            if ((currentX - i >= 0) && (currentY - i >= 0)) {
                if (!chessBoard.tiles[currentX - i][currentY - i].findIfOccupied()) {
                    Moves.add(new Move(currentTile, chessBoard.tiles[currentX - i][currentY - i]));
                } else {
                    if (chessBoard.tiles[currentX - i][currentY - i].getCurrent_piece().getColor() == this.getColor()) {
                        break;
                    } else {
                        Moves.add(new Move(currentTile, chessBoard.tiles[currentX - i][currentY - i]));
                        break;
                    }
                }
            }
        }

        for (int i = 1; i < chessBoard.get_no_of_cols(); i++) {
            if ((currentX + i < chessBoard.get_no_of_cols()) && (currentY - i >= 0)) {
                if (!chessBoard.tiles[currentX + i][currentY - i].findIfOccupied()) {
                    Moves.add(new Move(currentTile, chessBoard.tiles[currentX + i][currentY - i]));
                } else {
                    if (chessBoard.tiles[currentX + i][currentY - i].getCurrent_piece().getColor() == this.getColor()) {
                        break;
                    } else {
                        Moves.add(new Move(currentTile, chessBoard.tiles[currentX + i][currentY - i]));
                        break;
                    }
                }
            }
        }
        for (int i = 1; i < chessBoard.get_no_of_cols(); i++) {
            if ((currentX - i >= 0) && (currentY + i < chessBoard.get_no_of_cols())) {
                if (!chessBoard.tiles[currentX - i][currentY + i].findIfOccupied()) {
                    Moves.add(new Move(currentTile, chessBoard.tiles[currentX - i][currentY + i]));
                } else {
                    if (chessBoard.tiles[currentX - i][currentY + i].getCurrent_piece().getColor() == this.getColor()) {
                        break;
                    } else {
                        Moves.add(new Move(currentTile, chessBoard.tiles[currentX - i][currentY + i]));
                        break;
                    }
                }
            }
        }

        return Moves;
    }
}

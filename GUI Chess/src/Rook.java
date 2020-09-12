import javax.swing.*;
import java.util.ArrayList;

public class Rook extends Piece {
    private Icon rookIcon;
    public Rook(char color) {
        super("rook", color);
        if(color == 'B')
            this.rookIcon = new ImageIcon("pieces/rook_dark.png");
        else
            this.rookIcon = new ImageIcon("pieces/rook_light.png");
    }
    @Override
    public Icon getIcon() {
        return rookIcon;
    }

    @Override
    public ArrayList<Move> moveList(Chess chessBoard) {

        ArrayList<Move> Moves = new ArrayList<Move>();

        int currentX = this.getX();
        int currentY = this.getY();
        Tile currentTile = chessBoard.tiles[currentX][currentY];

        for (int i = currentY + 1; i < chessBoard.get_no_of_rows(); i++) {
            if(!chessBoard.tiles[currentX][i].findIfOccupied()){
                Moves.add(new Move(currentTile, chessBoard.tiles[currentX][i]));
            } else {
                if(chessBoard.tiles[currentX][i].getCurrent_piece().getColor() == this.getColor()) {
                    break;
                } else {
                    Moves.add(new Move(currentTile, chessBoard.tiles[currentX][i]));
                    break;
                }
            }
        }
        for(int i = currentY - 1; i >= 0; i--){
            if(!chessBoard.tiles[currentX][i].findIfOccupied()){
                Moves.add(new Move(currentTile, chessBoard.tiles[currentX][i]));
            } else {
                if(chessBoard.tiles[currentX][i].getCurrent_piece().getColor() == this.getColor()) {
                    break;
                } else {
                    Moves.add(new Move(currentTile, chessBoard.tiles[currentX][i]));
                    break;
                }
            }
        }
        for(int j = currentX + 1; j < chessBoard.get_no_of_cols(); j++){
            if(!chessBoard.tiles[j][currentY].findIfOccupied()){
                Moves.add(new Move(currentTile, chessBoard.tiles[j][currentY]));
            } else {
                if(chessBoard.tiles[j][currentY].getCurrent_piece().getColor() == this.getColor()) {
                    break;
                } else {
                    Moves.add(new Move(currentTile, chessBoard.tiles[j][currentY]));
                    break;
                }
            }
        }
        for(int j = currentX - 1; j >= 0; j--){
            if(!chessBoard.tiles[j][currentY].findIfOccupied()){
                Moves.add(new Move(currentTile, chessBoard.tiles[j][currentY]));
            } else {
                if(chessBoard.tiles[j][currentY].getCurrent_piece().getColor() == this.getColor()) {
                    break;
                } else {
                    Moves.add(new Move(currentTile, chessBoard.tiles[j][currentY]));
                    break;
                }
            }
        }
        return Moves;
    }

}

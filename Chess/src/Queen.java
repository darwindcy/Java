import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(char color) {
        super("queen", color);
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

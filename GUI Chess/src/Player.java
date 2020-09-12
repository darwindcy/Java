import java.util.ArrayList;

public class Player {
    private char playerChar;
    private boolean inCheck;

    public Player(char pieceColor) {
        if (pieceColor == 'B' || pieceColor == 'W')
            this.playerChar = pieceColor;
        else
            System.err.println("Player can only choose between Black and White Pieces");
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public String playerName() {
        if (playerChar == 'B')
            return "Black";
        else
            return "White";
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    public void printCheck() {
        if (inCheck)
            System.out.println("You are in CHECK!!!");
    }

    public ArrayList<Move> moveGenerator(Chess chessBoard) {
        ArrayList<Move> allPossibleMoves = new ArrayList<>();

        for (int i = 0; i < chessBoard.get_no_of_rows(); i++) {
            for (int j = 0; j < chessBoard.get_no_of_cols(); j++) {
                if (chessBoard.tiles[i][j].findIfOccupied()) {
                    if (chessBoard.tiles[i][j].getCurrent_piece().getColor() == playerChar) {
                        allPossibleMoves.addAll(chessBoard.tiles[i][j].getCurrent_piece().moveList(chessBoard));
                    }
                }
            }
        }
        if(playerChar == 'B') {
            if (!inCheck && checkLeftCastleBlack(chessBoard))
                allPossibleMoves.add(new Move(chessBoard.tiles[4][0], chessBoard.tiles[2][0]));
            if (!inCheck && checkRightCastleBlack(chessBoard))
                allPossibleMoves.add(new Move(chessBoard.tiles[4][0], chessBoard.tiles[6][0]));
        } else if(playerChar == 'W') {
            if (!inCheck && checkLeftCastleWhite(chessBoard))
                allPossibleMoves.add(new Move(chessBoard.tiles[4][7], chessBoard.tiles[2][7]));
            if (!inCheck && checkRightCastleWhite(chessBoard))
                allPossibleMoves.add(new Move(chessBoard.tiles[4][7], chessBoard.tiles[6][7]));
        }
        return allPossibleMoves;
    }

    private boolean checkLeftCastleWhite(Chess chessBoard) {
        return castleCheck(chessBoard, chessBoard.tiles[4][7], chessBoard.tiles[0][7]);
    }

    private boolean checkRightCastleWhite(Chess chessBoard) {
        return castleCheck(chessBoard, chessBoard.tiles[4][7], chessBoard.tiles[7][7]);
    }

    private boolean checkLeftCastleBlack(Chess chessBoard) {
        return castleCheck(chessBoard, chessBoard.tiles[4][0], chessBoard.tiles[0][0]);
    }

    private boolean checkRightCastleBlack(Chess chessBoard) {
        return castleCheck(chessBoard, chessBoard.tiles[4][0], chessBoard.tiles[7][0]);
    }

    private boolean castleCheck(Chess chessBoard, Tile kingLocation, Tile rookLocation) {
        if (kingLocation.findIfOccupied() && rookLocation.findIfOccupied()) {
            if (kingLocation.getCurrent_piece().getSymbol() == 'K' &&
                    rookLocation.getCurrent_piece().getSymbol() == 'R') {

                King aKing = (King) kingLocation.getCurrent_piece();
                Rook aRook = (Rook) rookLocation.getCurrent_piece();

                if (!aKing.findIfMoved() && !aRook.findIfMoved()) {
                    if(kingLocation.getX() < rookLocation.getX()) {
                        return checkEmpyInBetween(chessBoard, kingLocation, rookLocation);
                    } else
                        return checkEmpyInBetween(chessBoard, rookLocation, kingLocation);
                }
            }
        }
        return false;
    }
    public boolean checkEmpyInBetween(Chess chessBoard, Tile leftTile, Tile rightTile){

        int y = leftTile.getY();

        for(int i = leftTile.getX() + 1; i < rightTile.getX(); i++){
            if(chessBoard.tiles[i][y].findIfOccupied()) {
                return false;
            }
        }
        return true;
    }

    public void printPlayerTurn() {
        System.out.println(playerName() + " piece Player turn to Move.");
    }
}

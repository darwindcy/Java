import java.util.ArrayList;

public class Chess extends ChessBoard {

    ArrayList<Tile> tilesClicked;

    ChessBoard gameBoard = new ChessBoard();

    Tile fromTile;
    Tile toTile;
    private int x;
    private int y;

    private int mouseClickTracker = 0;

    public void playGame() {

        gameBoard.printBoard();

    }

    public void aClick(int aX, int aY) {

        x = aX;
        y = aY;
        action();
    }

    public void action() {
        if (gameBoard.tiles[x][y] != null) {

            addMoveToMoveList(gameBoard.tiles[x][y]);

            if (mouseClickTracker == 0) {
                fromTile = gameBoard.tiles[x][y];
                System.out.println("fromTile Created");
                increaseMouseClicks();
            } else {
                toTile = gameBoard.tiles[x][y];
                System.out.println("toTile Created");
                createMove();
                System.out.println("Move is created");
                mouseClickTracker = 0;
            }

        } else {
            System.out.println("Tile was empty");
        }
    }

    public Move createMove() {
        Move aMove = new Move(fromTile, toTile);
        return aMove;
    }

    private int getMouseClickTracker() {
        return mouseClickTracker;
    }

    private void increaseMouseClicks() {
        mouseClickTracker++;
    }

    private boolean checkForMove() {
        if (mouseClickTracker % 2 == 0)
            return true;
        return false;
    }

    private void executeTrialMove(Move currentMove) {
        System.out.println("Executing Move");
        placePiece(currentMove.toTile, currentMove.fromTile.getCurrent_piece());
        removePiece(currentMove.fromTile);
    }

    private void removePiece(Tile removingTile) {

        removingTile.remove_piece();
    }

    private void placePiece(Tile placingTile, Piece aPiece) {

        placingTile.put_piece(aPiece);
    }


}

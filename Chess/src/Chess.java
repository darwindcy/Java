import java.util.ArrayList;
import java.util.Scanner;

public class Chess extends Board {

    public Chess() {
        super(8, 8);
    }

    public void playGame() {
        boolean winnerDecided = false;
        setUpBoardForPlay();
        Player playerWhite = new Player('W');
        Player playerBlack = new Player('B');
        Player currentPlayer = playerWhite;
        boolean inCheck = false;
        boolean oppositePlayerCheck = false;
        Move playerMove = null;

        while (true) {

            print_board();

            currentPlayer.printCheck();

            currentPlayer.printPlayerTurn();

            if (currentPlayer.moveGenerator(this).isEmpty()) {
                System.out.println("No More Moves available for player; Game Drawn");
                break;
            }

            if(currentPlayer.isInCheck()){
                if(!checkIfPlayerCanStillMove(currentPlayer, changePlayer(currentPlayer, playerBlack, playerWhite))) {
                    System.out.println("You don't have any possible Moves to save your King. ");
                    break;
                }
            }

            playerMove = getMove(currentPlayer);

            if (checkIfMoveLegal(changePlayer(currentPlayer, playerBlack, playerWhite), playerMove)) {
                executeMove(playerMove);
                oppositePlayerCheck = currentPlayer.isInCheck();
                inCheck = checkForCheck(currentPlayer);
                currentPlayer = changePlayer(currentPlayer, playerWhite, playerBlack);

                if (checkForCheck(currentPlayer) && oppositePlayerCheck) {
                    System.out.println("You were already in check, and You did not protect your King; " +
                            "So its Check Mate ");
                    winnerDecided = true;
                } else
                    currentPlayer.setInCheck(inCheck);
            } else {
                System.out.println("Move causes king to be in Check or not be saved from Previous Check");
            }

            if (winnerDecided) {
                System.out.println("The winner is the Player with the " + currentPlayer.playerName() + " pieces.");
                break;
            }
        }
        System.out.println("Game Over!!!");
    }

    private boolean checkIfPlayerCanStillMove(Player player1, Player player2) {
        boolean canMove = true;

        for (Move aMove : player1.moveGenerator(this)) {

            Piece restoringPiece = null;
            if (aMove.toTile.findIfOccupied())
                restoringPiece = aMove.toTile.getCurrent_piece();

            executeTrialMove(aMove);

            for (Move eachMove : player2.moveGenerator(this)) {
                if (eachMove.toTile.findIfOccupied()) {
                    if (eachMove.toTile.getCurrent_piece().getSymbol() == 'K') {
                        canMove = false;
                    } else {
                        canMove = true;
                    }
                }
            }
            reverseMove(aMove, restoringPiece);
            if (canMove)
                return true;
        }
        return canMove;
    }

    private boolean checkIfMoveLegal(Player player2, Move currentMove) {
        Piece restoringPiece = null;
        if (currentMove.toTile.findIfOccupied())
            restoringPiece = currentMove.toTile.getCurrent_piece();

        executeTrialMove(currentMove);

        for (Move aMove : player2.moveGenerator(this)) {
            if (aMove.toTile.findIfOccupied())
                if (aMove.toTile.getCurrent_piece().getSymbol() == 'K') {
                    reverseMove(currentMove, restoringPiece);
                    return false;
                }
        }
        reverseMove(currentMove, restoringPiece);
        return true;
    }

    private void executeTrialMove(Move currentMove) {
        Piece currentPiece = currentMove.fromTile.getCurrent_piece();
        Tile fromTile = currentMove.fromTile;
        Tile toTile = currentMove.toTile;

        removePiece(toTile);
        removePiece(fromTile);
        placePiece(toTile, currentPiece);
    }

    private void reverseMove(Move moveMade, Piece removedPiece) {
        Move reverseMove = new Move(moveMade.toTile, moveMade.fromTile);
        executeMove(reverseMove);
        if (removedPiece != null) {
            this.tiles[moveMade.toTile.getX()][moveMade.toTile.getY()].put_piece(removedPiece);
        }
    }

    private boolean checkForCheck(Player currentPlayer) {
        ArrayList<Move> allMoves = currentPlayer.moveGenerator(this);
        for (Move aMove : allMoves) {
            if (aMove.toTile.findIfOccupied())
                if (aMove.toTile.getCurrent_piece().getSymbol() == 'K') {
                    return true;
                }
        }
        return false;
    }

    private void executeMove(Move currentMove) {
        Piece currentPiece = currentMove.fromTile.getCurrent_piece();
        Tile fromTile = currentMove.fromTile;
        Tile toTile = currentMove.toTile;

        currentPiece.markMoved();

        if (currentPiece.getSymbol() == 'K') {
            if (currentPiece.getColor() == 'W') {
                if (fromTile.getX() == 4 && toTile.getX() == 6) {
                    executeMove(new Move(this.tiles[7][7], this.tiles[5][7]));
                    System.out.println("White Castled");
                }
                if (fromTile.getX() == 4 && toTile.getX() == 2) {
                    executeMove(new Move(this.tiles[0][7], this.tiles[3][7]));
                    System.out.println("White Castled");
                }

            } else if (currentPiece.getColor() == 'B') {
                if (fromTile.getX() == 4 && toTile.getX() == 6) {
                    executeMove(new Move(this.tiles[7][0], this.tiles[5][0]));
                    System.out.println("Black Castled");
                }
                if (fromTile.getX() == 4 && toTile.getX() == 2) {
                    executeMove(new Move(this.tiles[0][0], this.tiles[3][0]));
                    System.out.println("Black Castled");
                }
            }
        }

        removePiece(toTile);
        removePiece(fromTile);

        if (currentPiece.getSymbol() == 'P' && currentPiece.getColor() == 'W' &&
                toTile.getY() == 0) {
            currentPiece.upgradePiece();
            System.out.println("Pawn upgraded to Queen Since it reached the Last Rank");
        }
        if (currentPiece.getSymbol() == 'P' && currentPiece.getColor() == 'B' &&
                toTile.getY() == get_no_of_rows() - 1) {
            ;
            currentPiece.upgradePiece();
            System.out.println("Pawn upgraded to Queen Since it reached the Last Rank");
        }

        placePiece(toTile, currentPiece);
    }

    private void removePiece(Tile removingTile) {
        if (tiles[removingTile.getX()][removingTile.getY()].findIfOccupied())
            this.tiles[removingTile.getX()][removingTile.getY()].remove_piece();
    }

    private void placePiece(Tile placingTile, Piece aPiece) {
        if (!tiles[placingTile.getX()][placingTile.getY()].findIfOccupied())
            this.tiles[placingTile.getX()][placingTile.getY()].put_piece(aPiece);
    }

    private Player changePlayer(Player currPlayer, Player player1, Player player2) {
        if (currPlayer == player1)
            return player2;
        else
            return player1;
    }

    private void setUpBoardForPlay() {
        build_board();
        buildChessBoard();
    }

    private void buildChessBoard() {
        insert_all_pieces('B');
        insert_all_pieces('W');
    }

    private void insert_all_pieces(char pieceColor) {
        int startTile;
        int endTile;
        if (pieceColor == 'W') {
            startTile = this.get_no_of_rows() - 1;
            endTile = startTile - 1;
        } else {
            startTile = 0;
            endTile = startTile + 1;
        }

        insert_pawns(endTile, pieceColor);
        insert_rooks(startTile, pieceColor);
        insert_bishops(startTile, pieceColor);
        insert_knights(startTile, pieceColor);
        insert_king(startTile, pieceColor);
        insert_queen(startTile, pieceColor);
    }

    private void insert_king(int rowNum, char pieceColor) {
        Piece aKing = new King(pieceColor);
        this.tiles[4][rowNum].put_piece(aKing);
    }

    private void insert_queen(int rowNum, char pieceColor) {
        Piece aQueen = new Queen(pieceColor);
        this.tiles[3][rowNum].put_piece(aQueen);
    }

    private void insert_knights(int rowNum, char pieceColor) {
        Piece aKnight = new Knight(pieceColor);
        Piece bKnight = new Knight(pieceColor);
        this.tiles[1][rowNum].put_piece(aKnight);
        this.tiles[this.get_no_of_cols() - 2][rowNum].put_piece(bKnight);
    }

    private void insert_bishops(int rowNum, char pieceColor) {
        Piece aBishop = new Bishop(pieceColor);
        Piece bBishop = new Bishop(pieceColor);
        this.tiles[2][rowNum].put_piece(aBishop);
        this.tiles[this.get_no_of_cols() - 3][rowNum].put_piece(bBishop);
    }

    private void insert_rooks(int rowNum, char pieceColor) {
        Piece aRook = new Rook(pieceColor);
        Piece bRook = new Rook(pieceColor);
        this.tiles[0][rowNum].put_piece(aRook);
        this.tiles[this.get_no_of_cols() - 1][rowNum].put_piece(bRook);
    }

    private void insert_pawns(int rowNum, char pieceColor) {
        for (int i = 0; i < this.get_no_of_cols(); i++)
            this.tiles[i][rowNum].put_piece(new Pawn(pieceColor));
    }

    private Move getMove(Player currentPlayer) {
        Scanner S1 = new Scanner(System.in);
        boolean valid_move = false;
        String inputLine;
        do {
            System.out.print("Enter your move(<FROM> <TO>): ");
            inputLine = S1.nextLine();
            inputLine = inputLine.toUpperCase();

            if (check_input_pattern(inputLine)) {
                String[] splitted = inputLine.split("\\s+", 2);
                System.out.println("Your move is from Tile " + splitted[0] + " to Tile " + splitted[1]);

                Tile fromTile = getFromTile(splitted[0]);
                Tile toTile = getToTile(splitted[1]);
                if (fromTile == null || toTile == null) {
                    System.out.println("Invalid Move, Please try again.");
                    valid_move = false;
                } else {
                    Move playerMove = new Move(fromTile, toTile);
                    if (checkValidPlayerMove(currentPlayer.moveGenerator(this), playerMove)) {
                        return playerMove;
                    }
                }
            }
        } while (!valid_move);
        return null;
    }

    private boolean checkValidPlayerMove(ArrayList<Move> allPossibleMoves, Move moveChosen) {

        if (allPossibleMoves.contains(moveChosen))
            return true;
        else
            System.out.println("Illegal Move, Not allowed");
        return false;
    }

    private Tile getFromTile(String fromTileString) {
        int fromX = (int) fromTileString.charAt(0) - 'A';
        int fromY = (int) fromTileString.charAt(1) - '0' - 1;
        if (valid_coordinate_check(fromX, fromY))
            return this.tiles[fromX][fromY];
        else
            return null;
    }

    private Tile getToTile(String toTileString) {
        int toX = (int) toTileString.charAt(0) - 'A';
        int toY = (int) toTileString.charAt(1) - '0' - 1;

        if (valid_coordinate_check(toX, toY))
            return this.tiles[toX][toY];
        else
            return null;
    }

    private boolean check_input_pattern(String inputString) {
        if (inputString.matches("[A-H]\\d [A-H]\\d")) {
            return true;
        }
        System.out.println("Input Pattern is not correct");
        return false;
    }

    private boolean valid_coordinate_check(int x, int y) {
        if (x >= 0 && y >= 0 &&
                x < this.get_no_of_cols() && y < this.get_no_of_rows())
            return true;
        return false;
    }
}

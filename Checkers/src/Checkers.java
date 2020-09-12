//File: Checkers.java
//Description: Checkers file for the Game
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Checkers                             Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

import java.util.Scanner;

public class Checkers extends Board{
    Piece light_pieces = new Piece('L');
    Piece dark_pieces = new Piece('D');
    boolean takingMove = false;
    boolean movePreserved = false;
    boolean validCheck = false;
    int forcedX = 0;
    int forcedY = 0;

    public Checkers(int num_rows, int num_cols){
        super(num_rows, num_cols);
    }

    public void play_game(){

        this.build_board();
        this.build_checkers_board();

        Player currentPlayer = new Player('L');

        do {
            this.print_board();
            currentPlayer.printPlayerTurn();

            System.out.println("Remaing Light pieces: " + get_no_of_Pieces('L'));
            System.out.println("Remaining Dark pieces: " + get_no_of_Pieces('D'));
            System.out.println();

            String[] splitted = this.getMove(currentPlayer).split("\\s+", 2);

            int a = (int) splitted[0].charAt(0) - 65;
            int b = splitted[0].charAt(1) - '0' - 1;

            int x = (int) splitted[1].charAt(0) - 65;
            int y = splitted[1].charAt(1) - '0' - 1;

            if(movePreserved && a == forcedX && y == forcedY)
                validCheck = true;
            else if(movePreserved == false)
                validCheck = true;
            else {
                validCheck = false;
                System.out.println("Invalid Move \nThere is a forced move on the board");
            }

            if(check_if_taking_move(a,b,x,y) && validCheck){
                this.tiles[get_center_xcoord(a,b,x,y)][get_center_ycoord(a,b,x,y)].remove_piece();
                takingMove = true;
            }

            if (validCheck)
                this.move(this.tiles[a][b], this.tiles[x][y]);

            if(takingMove && check_if_move_preserved(x,y)) {
                movePreserved = true;
                forcedX = x;
                forcedY = y;
            }

            if (get_no_of_Pieces('L') == 0 ||
                    get_no_of_Pieces('D') == 0) {
                this.print_board();
                break;
            }
            if(!movePreserved)
                currentPlayer.switchPlayer(currentPlayer);

        }while (true);

        if(currentPlayer.getPlayerChar() == 'D')
            System.out.println("The player with the dark pieces is the winner \n " +
                    "0 Light pieces remaining");
        else if(currentPlayer.getPlayerChar() == 'L')
            System.out.println("The player with the Light pieces is the winner \n " +
                    "0 Dark pieces remaining");
        else
            System.out.println("Error, Winner not determined");
    }

    public void build_checkers_board(){
      insert_light_pieces();
      insert_dark_pieces();
      make_squares_unavailable();
    }

    public void make_squares_unavailable(){
        Piece unavailableSymbol = new Piece('-');
        for(int j = 0; j < this.get_no_of_cols(); j++){
            for(int i = 0; i < this.get_no_of_rows(); i = i + 2){
                if(j % 2 == 0) {
                    this.tiles[i][j].put_piece(unavailableSymbol);
                } else {
                    this.tiles[i+1][j].put_piece(unavailableSymbol);
                }
            }
        }
    }

    public void insert_light_pieces(){
        insert_pieces(this.get_no_of_rows(), 6, light_pieces);
    }
    public void insert_dark_pieces(){
        insert_pieces(((this.get_no_of_rows())/2 - 1), 1, dark_pieces);
    }

    public void insert_pieces(int start_row, int end_row, Piece current_piece){
        for(int j = start_row; j >= end_row; j--){
            for(int i = 0; i < this.get_no_of_cols(); i = i + 2){
                //System.out.print(j);
                if(j % 2 == 0) {
                    this.tiles[i][j-1].put_piece(current_piece);
                } else {
                    this.tiles[i+1][j-1].put_piece(current_piece);
                }
            }
        }
    }

    public String getMove(Player currentPlayer){
        Scanner S1 = new Scanner(System.in);
        boolean valid_move = false;
        String inputLine;
        do{
            System.out.print("Enter your move(<FROM> <TO>): ");
            inputLine = S1.nextLine();
            inputLine = inputLine.toUpperCase();

            if(check_input_pattern(inputLine)) {
                String[] splitted = inputLine.split("\\s+", 2);
                System.out.println("Your move is from Tile " + splitted[0] + " to Tile " + splitted[1]);
                if (check_move_validity(currentPlayer, splitted[0], splitted[1])){
                    //System.out.println("Your move is Valid");
                    return inputLine;
            } else
                System.out.println("Your move was Invalid");
            }
        }while(!valid_move);
        return "No acceptable move";
    }

    public boolean check_move_validity(Player currentPlayer, String fromTile, String toTile) {

        int a = (int) fromTile.charAt(0) - 65;
        int b = fromTile.charAt(1) - '0' - 1;

        int x = (int) toTile.charAt(0) - 65;
        int y = toTile.charAt(1) - '0' - 1;

        boolean checkForcedAvailable = check_for_forced_move(currentPlayer);

        if (check_valid_move_in_fromTile(a, b) &&
                check_valid_move_in_toTile(x, y) &&
                check_valid_diagonal_move(a, b, x, y, checkForcedAvailable) &&
                check_if_player_moved_own_piece(currentPlayer, a, b) &&
                check_if_forward_move(currentPlayer, a, b, x, y)) {

            if (currentPlayer.getPlayerChar() == 'L' && y == 0 ||
                currentPlayer.getPlayerChar() == 'D' && y == this.get_no_of_rows() - 1)
                tiles[a][b].getCurrent_piece().upStatus();
            return true;
        }
        return false;
    }

    public boolean check_valid_move_in_toTile(int x, int y){
        if(x >= 0 && x <= this.get_no_of_cols() &&
           y >= 0 && y <= this.get_no_of_rows() &&
           !this.tiles[x][y].findIfOccupied()) {
            return true;
        } else {
            System.out.println("Invalid input in <To> Tile");
            return false;
        }
    }
    public boolean check_valid_move_in_fromTile(int a, int b){
        if(a >= 0 && a <= this.get_no_of_cols() &&
            b >= 0 && b <= this.get_no_of_rows() &&
            this.tiles[a][b].findIfOccupied()) {
            return true;
        } else {
            System.out.println("Invalid input in <From> Tile");
            return false;
        }
    }

    public boolean check_if_player_moved_own_piece(Player currentPlayer, int a, int b){

        if(this.tiles[a][b].getCurrent_piece().getSymbol() == currentPlayer.getPlayerChar())
            return true;
        else {
            System.out.println("It is not your turn to move");
            return false;
        }
    }

    public boolean check_input_pattern(String inputString){
        if(inputString.matches("[A-J]\\d [A-J]\\d")) {
            return true;
        }
        System.out.println("Input Pattern is not correct");
        return false;
    }

    public boolean check_valid_diagonal_move(int a, int b, int x, int y, boolean forcedCaptureAvailable){
        try {
            if (Math.abs((y - b) / (x - a)) == 1) {
                if(Math.abs(y - b) == 2) {
                    return check_if_taking_move(a, b, x, y);
                } else if(Math.abs(y-b) == 1) {
                    if(forcedCaptureAvailable) {
                        System.out.println("There is a Forced capture available");
                        return false;
                    }
                    return true;
                } else {
                    System.out.println("Piece cannot move that far");
                    return false;
                }
            } else {
                System.out.println("Your move was not diagonal");
                return false;
            }
        } catch (ArithmeticException error) {
            return false;
        }
    }
    public boolean check_for_forced_move(Player currentPlayer){
        boolean forcedMove = false;

        for(int j = 0; j < this.get_no_of_cols(); j++){
            for(int i = 0; i < this.get_no_of_rows(); i++){
                if(this.tiles[i][j].getCurrent_piece() != null) {
                    if (this.tiles[i][j].getCurrent_piece().getSymbol() == currentPlayer.getPlayerChar()) {
                        if (this.tiles[i][j].getCurrent_piece().getStatus() == 'K') {
                            if (check_left_right_forward(i, j) || check_left_right_backward(i, j)) {
                                forcedMove = true;
                                break;
                            }
                        } else {
                            if (check_left_right_forward(i, j)) {
                                //System.out.println("Forced capture move found \n \n \n \n");
                                forcedMove = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return forcedMove;
    }

    public boolean check_if_taking_move(int a, int b, int x, int y) {
        int center_xCoord = get_center_xcoord(a, b, x, y);
        int center_yCoord = get_center_ycoord(a, b, x, y);

        if (this.tiles[a][b].getCurrent_piece().
                opposingPiece(this.tiles[center_xCoord][center_yCoord].getCurrent_piece()) &&
                this.tiles[x][y].getCurrent_piece() == null){
            return true;
        } else
            return false;

    }
    public int get_center_xcoord(int a, int b, int x, int y){
        if (a < x && b < y)
            return a + 1;
         else if (a > x && b < y)
            return a - 1;
         else if (a > x && b > y)
            return a - 1;
         else if (a < x && b > y)
            return a + 1;
         return 0;
    }
    public int get_center_ycoord(int a, int b, int x, int y){
        if (a < x && b < y)
            return b + 1;
        else if (a > x && b < y)
            return b + 1;
         else if (a > x && b > y)
            return b - 1;
         else if (a < x && b > y)
               return  b - 1;
        return 0;
    }

    public boolean check_if_move_preserved(int a, int b){
        if(this.tiles[a][b].getCurrent_piece() != null &&
                this.tiles[a][b].getCurrent_piece().getStatus() == 'K'){
            return (check_left_right_forward(a,b) || check_left_right_backward(a,b));
        } else
            return check_left_right_forward(a, b);
    }

    public boolean check_left_right_forward(int x, int y){
        if(tiles[x][y].getCurrent_piece() == null)
            return false;

        if(tiles[x][y].getCurrent_piece().getSymbol() == 'L'){
            return (check_next_tile_LF_RF_LB_RB(x, y, x-2, y-2, x-1, y -1)||
                    check_next_tile_LF_RF_LB_RB(x, y, x+2, y-2, x+1, y-1));
        } else if(tiles[x][y].getCurrent_piece().getSymbol() == 'D'){
            return (check_next_tile_LF_RF_LB_RB(x, y, x-2, y+2, x-1, y+1) ||
                    check_next_tile_LF_RF_LB_RB(x, y, x+2, y+2, x+1, y+1));
        }
        return false;
    }

    public boolean check_left_right_backward(int x, int y){

        if(tiles[x][y].getCurrent_piece().getSymbol() == 'L'){
            return (check_next_tile_LF_RF_LB_RB(x, y, x-2, y+2, x-1, y+1)||
                    check_next_tile_LF_RF_LB_RB(x, y, x+2, y+2, x+1, y+1));
        } else if(tiles[x][y].getCurrent_piece().getSymbol() == 'D'){
            return (check_next_tile_LF_RF_LB_RB(x, y, x+2, y+2, x+1, y+1) ||
                    check_next_tile_LF_RF_LB_RB(x, y, x-2, y-2, x-1, y-1));
        }

        return false;
    }

    public boolean check_next_tile_LF_RF_LB_RB(int x, int y,
                                               int finalX, int finalY,
                                               int centerX, int centerY){
        if(checkValid_X(x) && checkValid_Y(y) &&
                checkValid_X(finalX) && checkValid_Y(finalY) &&
                checkValid_X(centerX) && checkValid_Y(centerY) &&
                tiles[x][y].getCurrent_piece() != null &&
                tiles[centerX][centerY].getCurrent_piece() != null) {

            if (tiles[x][y].getCurrent_piece().opposingPiece(tiles[centerX][centerY].getCurrent_piece()) &&
                    tiles[finalX][finalY].getCurrent_piece() == null)
                return true;
        }
        return false;
    }

    public boolean check_if_forward_move(Player currentPlayer, int a, int b, int x, int y){
        if(this.tiles[a][b].getCurrent_piece().getStatus() != 'K') {
            if (currentPlayer.getPlayerChar() == 'L') {
                if (b - y > 0) {
                    System.out.println("Forward move for Light Piece");
                    return true;
                } else {
                    System.out.println("Backward move for Light pieces");
                    return false;
                }
            } else {
                if (y - b > 0) {
                    System.out.println("Forward move for Dark Piece");
                    return true;
                } else {
                    System.out.println("Backward move for Dark pieces");
                    return false;
                }
            }
        } else
            return true;
    }

    public boolean checkValid_X(int x){
        if(x < this.get_no_of_rows() && x >=0)
            return true;
        else
            return false;
    }
    public boolean checkValid_Y(int y){
        if(y < this.get_no_of_cols() && y >=0)
            return true;
        else
            return false;
    }
    public int get_no_of_Pieces(char currChar){
        int totalPieces = 0;
        for(int i = 0; i < this.get_no_of_cols(); i++){
            for(int j = 0; j < this.get_no_of_rows(); j++) {
                if(this.tiles[i][j].getCurrent_piece() != null) {
                    if (this.tiles[i][j].getCurrent_piece().getSymbol() == currChar)
                        totalPieces++;
                }
            }
        }
        return totalPieces;
    }
}

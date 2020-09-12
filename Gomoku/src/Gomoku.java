//File: Gomoku.java
//Description: Gomoku file for the game Gomoku
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Gomoku                               Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

import java.util.Scanner;

public class Gomoku {

    Board game_board;

    public Gomoku(){
        game_board = build_board_for_gomoku();
        System.out.println("This is the Fun game of Gomoku, " +
                "Play as much as your bains let you. Best of Luck");
        System.out.println("The player who goes first is 'X' and the player who goes" +
                " second is 'O'\n");
        System.out.println("The current Player symbol is shown on the top left (0,0) Tile\n");
        play_game();
    }

    public void play_game() {
        int current_player = 1;
        char player_symbol = 'X';

        while (true) {
            game_board.tiles[0][0].occupied_by = player_symbol;
            game_board.print_board();

            String current_move = get_move(current_player);
            char chosen_char = Character.toUpperCase(current_move.charAt(0));
            int chosen_int = Integer.valueOf(current_move.substring(1));
            game_board.tiles[chosen_int][(int) (chosen_char - 65 + 1)].occupied_by = player_symbol;
            game_board.tiles[chosen_int][(int) (chosen_char - 65 + 1)].occupied = true;

            if (check_for_win(chosen_int, (int) (chosen_char - 65 + 1), game_board)) {
                game_board.print_board();
                display_winner(current_player);
                break;
            }

            if (current_player == 1) {
                current_player = 2;
                player_symbol = 'O';

            } else {
                current_player = 1;
                player_symbol = 'X';
            }
        }
    }

    public void display_winner(int player) {
        if (player == 1)
            System.out.println("Player 1 Wins !!!");
        else if (player == 2)
            System.out.println("Player 2 Wins !!!");
        else
            System.out.println("Its a Draw :( !");
        System.out.println("Congratulations !!");
    }

    public Board build_board_for_gomoku() {
        Board board1 = new Board(19, 19);
        int row_index;
        int col_index;
        int num_of_rows = board1.get_no_of_rows();
        int num_of_cols = board1.get_no_of_cols();
        board1.tiles[0][0] = new Tile();
        for (int i = 1; i < num_of_rows; i++) {
            board1.tiles[i][0] = new Tile((char) i);
        }
        for (int j = 1; j < num_of_cols; j++) {
            board1.tiles[0][j] = new Tile((char) ('A' + j - 1));
        }

        for (row_index = 1; row_index < num_of_rows; row_index++) {
            for (col_index = 1; col_index < num_of_cols; col_index++) {
                board1.tiles[row_index][col_index] = new Tile();
            }
        }

        return board1;
    }

    public String get_move(int current_player) {
        Scanner S = new Scanner(System.in);
        int chosen_int;
        char chosen_char;
        String square_choosen;
        int number_of_rows = game_board.get_no_of_rows();
        int number_of_cols = game_board.get_no_of_cols();
        boolean valid_choice;
        //print_board(gomoku);
        do {
            valid_choice = false;
            System.out.print("Player " + current_player + "'s turn to move. Choose your Square: ");

            square_choosen = S.nextLine();
            try {
                chosen_char = Character.toUpperCase(square_choosen.charAt(0));
                chosen_int = Integer.valueOf(square_choosen.substring(1));
                System.out.println("You chose Row " + chosen_int + " in Column " + chosen_char);
                if (chosen_int < number_of_rows && (int) chosen_char < number_of_cols + 'A' - 1 &&
                        chosen_int >= 0 &&
                        (int) chosen_char >= 'A' &&
                        !game_board.tiles[chosen_int][(int) chosen_char - 'A' + 1].occupied)
                    valid_choice = true;
                else {
                    if (chosen_int >= number_of_rows || (int) chosen_char >= number_of_cols + 'A' - 1 ||
                            chosen_int < 0 || (int) chosen_char < 'A') {
                        System.out.println("Invalid Square Chosen; Try Again");
                    } else if (game_board.tiles[chosen_int][(int) chosen_char - 'A' + 1].occupied) {
                        System.out.println("Square Already Occupied; Try Again");
                    } else {
                        System.out.println("Bad Input");
                    }
                }
            } catch (NumberFormatException error) {
                System.out.println("Input should be <Column Alphabet><Row Number>. For e.g 'A15'");
            }

        } while (!valid_choice);
        return square_choosen;
    }

    public boolean check_for_win(int row_index, int col_index,
                                        Board current_board) {
        if (row_check(row_index, col_index, current_board) ||
                col_check(row_index, col_index, current_board) ||
                left_diagonal_check(row_index, col_index, current_board) ||
                right_diagonal_check(row_index, col_index, current_board))
            return true;
        else
            return false;
    }

    public boolean row_check(int row_index, int col_index, Board current_board) {
        for (int i = 0; i < 5; i++) {
            if (col_index - i >= 0 && col_index - i + 5 <= current_board.get_no_of_cols())
                if (check_five_tiles_in_a_row(row_index, col_index - i, current_board))
                    return true;
        }
        return false;
    }

    public boolean col_check(int row_index, int col_index, Board current_board) {
        for (int i = 0; i < 5; i++) {
            if (row_index - i >= 0 && row_index - i + 5 <= current_board.get_no_of_rows())
                if (check_five_tiles_in_a_column(row_index - i, col_index, current_board))
                    return true;
        }
        return false;
    }

    public boolean right_diagonal_check(int row_index, int col_index, Board current_board) {
        for (int i = 0; i < 5; i++) {
            if (row_index - i >= 0 && col_index - i >= 0 &&
                    row_index - i + 5 <= current_board.get_no_of_rows() &&
                    col_index - i + 5 <= current_board.get_no_of_cols())
                if (check_five_tiles_right_diagonal(row_index - i,
                        col_index - i, current_board))
                    return true;
        }
        return false;
    }

    public boolean left_diagonal_check(int row_index, int col_index, Board current_board) {
        for (int i = 0; i < 5; i++) {
            if (col_index - i >= 0 && col_index - i + 5 <= current_board.get_no_of_cols() &&
                    row_index + i <= current_board.get_no_of_rows() - 1 && row_index + i - 5 >= 0)
                if (check_five_tiles_left_diagonal(row_index + i,
                        col_index - i, current_board))
                    return true;
        }
        return false;
    }


    public boolean check_five_tiles_in_a_row(int starting_row_index, int starting_col_index,
                                                    Board current_board) {
        return check_five_tiles(current_board.tiles[starting_row_index][starting_col_index],
                current_board.tiles[starting_row_index][starting_col_index + 1],
                current_board.tiles[starting_row_index][starting_col_index + 2],
                current_board.tiles[starting_row_index][starting_col_index + 3],
                current_board.tiles[starting_row_index][starting_col_index + 4]);
    }

    public boolean check_five_tiles_in_a_column(int starting_row_index, int starting_col_index,
                                                       Board current_board) {

        return check_five_tiles(current_board.tiles[starting_row_index][starting_col_index],
                current_board.tiles[starting_row_index + 1][starting_col_index],
                current_board.tiles[starting_row_index + 2][starting_col_index],
                current_board.tiles[starting_row_index + 3][starting_col_index],
                current_board.tiles[starting_row_index + 4][starting_col_index]);
    }


    public boolean check_five_tiles_right_diagonal(int starting_row_index, int starting_col_index, Board current_board) {
        return check_five_tiles(current_board.tiles[starting_row_index][starting_col_index],
                current_board.tiles[starting_row_index + 1][starting_col_index + 1],
                current_board.tiles[starting_row_index + 2][starting_col_index + 2],
                current_board.tiles[starting_row_index + 3][starting_col_index + 3],
                current_board.tiles[starting_row_index + 4][starting_col_index + 4]);
    }


    public boolean check_five_tiles_left_diagonal(int starting_row_index, int starting_col_index, Board current_board) {

        return check_five_tiles(current_board.tiles[starting_row_index][starting_col_index],
                current_board.tiles[starting_row_index - 1][starting_col_index + 1],
                current_board.tiles[starting_row_index - 2][starting_col_index + 2],
                current_board.tiles[starting_row_index - 3][starting_col_index + 3],
                current_board.tiles[starting_row_index - 4][starting_col_index + 4]);

    }

    public boolean check_five_tiles(Tile T1, Tile T2, Tile T3, Tile T4, Tile T5) {
        if (T1.isEquals(T2) &&
                T2.isEquals(T3) &&
                T3.isEquals(T4) &&
                T4.isEquals(T5))
            return true;
        else
            return false;
    }

}

//File: Player.java
//Description:
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Checkers                             Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

public class Player {
    private char playerChar;

    public Player(char playerPice){
        if(playerPice == 'D' || playerPice == 'L')
            this.playerChar = playerPice;
        else
            System.out.println("Only player with Light or Dark pieces are allowed");
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public void switchPlayer(Player currentPlayer){
        if(currentPlayer.playerChar == 'L')
            currentPlayer.playerChar = 'D';
        else
            currentPlayer.playerChar = 'L';
    }

    public void printPlayerTurn(){
        if (playerChar == 'L')
            System.out.println("Light Pieces turn to move");
        else
            System.out.println("Dark Pieces turn to move");
    }

}

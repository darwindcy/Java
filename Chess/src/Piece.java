//File: Piece.java
//Description:
//-------------------------------------------------------------------------------------------------
//Class: CS290                                     Instructor: Dr. Don Roberts
//Assignment: Chess                                Date Assigned:
//Programmer: Darwin yadav                         Date Completed:
//--------------------------------------------------------------------------------------------------

import java.util.ArrayList;

public class Piece implements Moves{
    private String name;
    private char color;
    private char symbol;
    private int x;
    private int y;
    private boolean moved = false;


    public Piece(int locationX, int locationY){
        this.name = null;
        this.symbol = ' ';
        this.x = locationX;
        this.y = locationY;
    }
    public Piece nullPiece(){
        Piece nPiece = null;
        return nPiece;
    }

    public Piece(String name, char color){
        this.name = name.toLowerCase();
        this.symbol = Character.toUpperCase(name.charAt(0));
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public char getColor() {
        return color;
    }

    public char getSymbol() {
        return symbol;
    }
    public void upgradePiece(){
        this.name = "Queen";
        this.symbol = 'Q';
    }
    public void markMoved() {
        if(!this.moved) {
            this.moved = true;
        }
    }
    public boolean findIfMoved(){
        return moved;
    }


    public ArrayList<Move> moveList(Chess chessBoard) {
        return null;
    }
}


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Piece {

    private String name;
    private char color;
    private char symbol;
    private int x;
    private int y;
    private boolean moved = false;
    private Icon icon;

    public Piece(String name, char color) {
        this.name = name.toLowerCase();
        this.symbol = Character.toUpperCase(name.charAt(0));
        this.color = color;
        this.icon = null;
    }
    public void removeIcon(){
        this.icon = null;
    }

    public void move(Tile t1, Tile t2){
        t2.put_piece(t1.getCurrent_piece());
        t1.remove_piece();
    }

    public Icon getIcon() {
        return icon;
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

    public void upgradePiece() {
        this.name = "Queen";
        this.symbol = 'Q';
    }

    public void markMoved() {
        if (!this.moved) {
            this.moved = true;
        }
    }

    public boolean findIfMoved() {
        return moved;
    }
    public ArrayList<Move> moveList(Chess chessBoard) {
        return null;
    }
}

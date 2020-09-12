import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tile{
    private int x;
    private int y;
    private Piece current_piece;
    private JButton button;

    private int totalClicks = 0;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.button = new JButton();
        this.button.setPreferredSize(new Dimension(90,  90));

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Chess().aClick(x, y);
            }
        });
    }

    public Piece sendClickedPiece(Piece aPiece){
        return aPiece;
    }

    public JButton getButton() {
        return button;
    }

    public boolean findIfOccupied(){
        if (this.current_piece != null)
            return true;
        else
            return false;
    }

    public void put_piece(Piece current_piece){
        this.current_piece = current_piece;
        this.button.setIcon(current_piece.getIcon());
        current_piece.setX(x);
        current_piece.setY(y);
    }

    public void remove_piece(){
        if(this.current_piece != null) {
            if(this.button.getIcon() != null)
                this.button.setIcon(null);
            this.current_piece = null;
        }
    }

    public Piece getCurrent_piece() {
        return current_piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLocation(){
        return x + ", " + y;
    }
}

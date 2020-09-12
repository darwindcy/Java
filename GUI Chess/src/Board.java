import com.sun.deploy.util.BlackList;

import javax.swing.*;
import java.awt.*;

public class Board {

    JFrame window;
    JPanel chessWindow;
    JPanel moveListWindow;
    Container contents;
    Tile[][] tiles;
    private int tileRows;
    private int tileCols;

    public Board(int numRows, int numCols) {
        this.tiles = new Tile[numRows][numCols];
        this.tileRows = numRows;
        this.tileCols = numCols;
        setUpBoard();
    }

    public int get_no_of_rows() {
        return tileRows;
    }

    public int get_no_of_cols() {
        return tileCols;
    }

    private void setUpBoard(){
        build_empty_board();
        setUpFrame();
    }
    public void printBoard(){
        window.setVisible(true);
    }

    public void refresh(){
        window.revalidate();
    }

    private void setUpFrame() {

        window = new JFrame();
        window.setLayout(null);
        window.setBounds(100, 100, 1200, 900);
        window.setMinimumSize(new Dimension(300, 300));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chessWindow = new JPanel();
        chessWindow.setBounds(25, 25, 800, 800);
        chessWindow.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        moveListWindow = new JPanel();

        moveListWindow.setLayout(new GridLayout());
        moveListWindow.setBackground(Color.GRAY);
        moveListWindow.setBounds(825, 25, 300, 800);
        moveListWindow.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        window.add(chessWindow);
        window.add(moveListWindow);

        addBoardToFrame();
    }

    public void addMoveToMoveList(Tile t1){
        moveListWindow.add(new JLabel((t1.getX() + 65) + "," + t1.getY()));
        printBoard();
    }

    private void build_empty_board() {
        for (int i = 0; i < this.tileRows; i++)
            for (int j = 0; j < this.tileCols; j++) {
                this.tiles[i][j] = new Tile(i, j);
                if (i % 2 == 0) {
                    if (j % 2 != 0)
                        this.tiles[i][j].getButton().setBackground(Color.DARK_GRAY);
                    else
                        this.tiles[i][j].getButton().setBackground(Color.WHITE);
                } else {
                    if (j % 2 != 0)
                        this.tiles[i][j].getButton().setBackground(Color.WHITE);
                    else
                        this.tiles[i][j].getButton().setBackground(Color.DARK_GRAY);
                }
            }

    }
    private void addBoardToFrame(){
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                chessWindow.add(tiles[i][j].getButton());
            }
        }

    }

}

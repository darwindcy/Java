public class ChessBoard extends Board {
    public ChessBoard() {
        super(8, 8);
        setUpBoardForPlay();
    }
    private void setUpBoardForPlay(){
        addPiecesToBoard();
    }

    private void addPiecesToBoard(){
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
        this.tiles[rowNum][4].put_piece(new King(pieceColor));
    }
    private void insert_queen(int rowNum, char pieceColor) {
        this.tiles[rowNum][3].put_piece(new Queen(pieceColor));
    }
    private void insert_knights(int rowNum, char pieceColor) {
        this.tiles[rowNum][1].put_piece(new Knight(pieceColor));
        this.tiles[rowNum][this.get_no_of_cols() - 2].put_piece(new Knight(pieceColor));
    }
    private void insert_bishops(int rowNum, char pieceColor) {
        this.tiles[rowNum][2].put_piece(new Bishop(pieceColor));
        this.tiles[rowNum][this.get_no_of_cols() - 3].put_piece(new Bishop(pieceColor));
    }
    private void insert_rooks(int rowNum, char pieceColor) {
        this.tiles[rowNum][0].put_piece(new Rook(pieceColor));
        this.tiles[rowNum][this.get_no_of_cols() - 1].put_piece(new Rook(pieceColor));
    }
    private void insert_pawns(int rowNum, char pieceColor) {
        for (int i = 0; i < this.get_no_of_cols(); i++)
            this.tiles[rowNum][i].put_piece(new Pawn(pieceColor));
    }
}

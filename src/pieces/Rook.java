package pieces;

import main.GamePanel;

public class Rook extends Piece{
    public Rook(int color, int col, int row){
        super(color, col, row);
        piece_type = 1;
        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-rook.png");
        }else{
            setPieceImage("./resources/pieces/b-rook.png");
        }
    }
}

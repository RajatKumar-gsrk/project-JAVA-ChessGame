package pieces;

import main.GamePanel;

public class Queen extends Piece{
    public Queen(int color, int col, int row){
        super(color, col, row);
        piece_type = 4;
        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-queen.png");
        }else{
            setPieceImage("./resources/pieces/b-queen.png");
        }
    }
}

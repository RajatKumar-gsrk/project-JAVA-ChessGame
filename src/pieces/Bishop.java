package pieces;

import main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row){
        super(color, col, row);
        piece_type = 3;
        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-bishop.png");
        }else{
            setPieceImage("./resources/pieces/b-bishop.png");
        }
    }
}

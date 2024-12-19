package pieces;

import main.GamePanel;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row){
        super(color, col, row);

        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-bishop.png");
        }else{
            setPieceImage("./resources/pieces/b-bishop.png");
        }
    }
}

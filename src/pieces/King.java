package pieces;

import main.GamePanel;

public class King extends Piece{
    public King(int color, int col, int row){
        super(color, col, row);

        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-king.png");
        }else{
            setPieceImage("./resources/pieces/b-king.png");
        }
    }
    
}

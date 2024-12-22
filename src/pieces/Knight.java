package pieces;

import main.GamePanel;

public class Knight extends Piece{
    public Knight(int color, int col, int row){
        super(color, col, row);
        piece_type = 2;
        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-knight.png");
        }else{
            setPieceImage("./resources/pieces/b-knight.png");
        }
    }
    
}

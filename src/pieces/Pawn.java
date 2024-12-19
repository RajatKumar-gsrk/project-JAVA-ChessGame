package pieces;

import main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row){
        super(color, col, row);

        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-pawn.png");
        }else{
            setPieceImage("./resources/pieces/b-pawn.png");
        }
    }
}

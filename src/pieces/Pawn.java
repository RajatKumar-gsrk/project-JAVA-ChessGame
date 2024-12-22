package pieces;

import main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row){
        super(color, col, row);
        piece_type = 0;
        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-pawn.png");
        }else{
            setPieceImage("./resources/pieces/b-pawn.png");
        }
    }

    public void getMoves(){
        moves.clear();
        int c = this.col;
        int r = this.row;

        if(r > 0 && color == GamePanel.LIGHT){ //white N
            moves.add(new int[]{c, r -1});
        }

        if(r < 7 && color == GamePanel.DARK){
            moves.add(new int[]{c, r + 1});
        }
    }
}

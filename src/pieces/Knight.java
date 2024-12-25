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

    public void getMoves(){
        moves.clear();
        int c = this.col;
        int r = this.row;

        if(r - 2 >= 0){//N
            if(c - 1 >= 0){
                moves.add(new int[]{c - 1, r - 2});
            }

            if(c + 1 <= 7){
                moves.add(new int[]{c + 1, r - 2});
            }
        }

        if(r + 2 <= 7){//S
            if(c - 1 >= 0){
                moves.add(new int[]{c - 1, r + 2});
            }

            if(c + 1 <= 7){
                moves.add(new int[]{c + 1, r + 2});
            }
        }

        if(c - 2 >= 0){//W
            if(r - 1 >= 0){
                moves.add(new int[]{c - 2, r - 1});
            }

            if(r + 1 <= 7){
                moves.add(new int[]{c - 2, r + 1});
            }
        }

        if(c + 2 <= 7){
            if(r - 1 >= 0){
                moves.add(new int[]{c + 2, r - 1});
            }

            if(r + 1 <= 7){
                moves.add(new int[]{c + 2, r + 1});
            }
        }
    }
    
}

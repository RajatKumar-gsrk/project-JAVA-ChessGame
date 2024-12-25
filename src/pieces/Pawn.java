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
            if(!hasMoved){
                moves.add(new int[]{c, r - 2});
            }
        }

        if(r < 7 && color == GamePanel.DARK){
            moves.add(new int[]{c, r + 1});
            if(!hasMoved){
                moves.add(new int[]{c, r + 2});
            }
        }
    }

    public void removeBlockedPath(){
        for(int[] blockedMove: blockedPath){
            int pos = blockedMove[2];
            int c = blockedMove[0];
            int r = blockedMove[1];

            if(pos == GamePanel.N){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] < r && moves.get(i)[0] == c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }

            if(pos == GamePanel.S){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] > r && moves.get(i)[0] == c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }
        }
    }
}

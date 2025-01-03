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

    public void getMoves(){
        moves.clear();
        int c = this.col;
        int r = this.row;

        int i = 1;
        while(c - i >= 0){//W
            moves.add(new int[]{c - i, r});
            i += 1;
        }

        i = 1;
        while(r - i >= 0){//N
            moves.add(new int[]{c, r - i});
            i += 1;
        }

        i = 1;
        while(c + i <= 7){//E
            moves.add(new int[]{c + i, r});
            i += 1;
        }

        i = 1;
        while(r + i <= 7){//S
            moves.add(new int[]{c, r + i});
            i += 1;
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

            if(pos == GamePanel.W){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] == r && moves.get(i)[0] < c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }

            if(pos == GamePanel.E){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] == r && moves.get(i)[0] > c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }
        }
    }
}

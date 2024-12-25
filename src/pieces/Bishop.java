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

    public void getMoves(){
        moves.clear();
        int c = this.col;
        int r = this.row;

        int i = 1;
        while(c - i >= 0 && r - i >= 0){//NW
            moves.add(new int[]{c - i, r - i});
            i += 1;
        }
        i = 1;
        while(c - i >= 0 && r + i <= 7){//SW
            moves.add(new int[]{c - i, r + i});
            i += 1;
        }
        i = 1;
        while(c + i <= 7 && r - i >= 0){//NE
            moves.add(new int[]{c + i, r - i});
            i += 1;
        }
        i = 1;
        while(c + i <= 7 && r + i <= 7){//SE
            moves.add(new int[]{c + i, r + i});
            i += 1;
        }

    }

    public void removeBlockedPath(){
        for(int[] blockedMove: blockedPath){
            int pos = blockedMove[2];
            int c = blockedMove[0];
            int r = blockedMove[1];

            if(pos == GamePanel.NE){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] < r && moves.get(i)[0] > c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }

            if(pos == GamePanel.NW){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] < r && moves.get(i)[0] < c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }

            if(pos == GamePanel.SW){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] > r && moves.get(i)[0] < c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }

            if(pos == GamePanel.SE){
                int i = 0;
                while(i < moves.size()){
                    if(moves.get(i)[1] > r && moves.get(i)[0] > c){
                        moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }
        }
    }
}

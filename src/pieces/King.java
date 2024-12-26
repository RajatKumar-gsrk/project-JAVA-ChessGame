package pieces;

import main.GamePanel;

public class King extends Piece{
    public King(int color, int col, int row){
        super(color, col, row);
        piece_type = 5;
        if(color == GamePanel.LIGHT){
            setPieceImage("./resources/pieces/w-king.png");
        }else{
            setPieceImage("./resources/pieces/b-king.png");
        }
    }
    public void getMoves(){
        moves.clear();
        int c = this.col;
        int r = this.row;
        if(c > 0){//W
            moves.add(new int[]{c - 1, r});
        }
        if(c<7){//R
            moves.add(new int[]{c + 1, r});
        }
        if(r > 0){//N
            moves.add(new int[]{c, r - 1});
        }
        if(r < 7){//S
            moves.add(new int[]{c, r + 1});
        }
        if(c > 0 && r > 0){
            moves.add(new int[]{c - 1, r - 1});
        }
        if(c > 0 && r < 7){
            moves.add(new int[]{c - 1, r + 1});
        }
        if(c < 7 && r > 0){
            moves.add(new int[]{c + 1, r - 1});
        }
        if(c < 7 && r < 7){
            moves.add(new int[]{c + 1, r + 1});
        }
    }
    
    public void removeBlockedPath(){
        for(int[] blockedMove: blockedPath){
            int i = 0;
            while(i < moves.size()){
                if(blockedMove[0] == moves.get(i)[0] && blockedMove[1] == moves.get(i)[1]){
                    moves.remove(i);
                    System.out.println("Type: "+ blockedMove[2]);
                }else{
                    i += 1;
                }
            }
        }
        blockedPath.clear();
    }
}

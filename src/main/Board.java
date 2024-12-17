package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {
    private static final Color LIGHT_COLOR = Color.WHITE;
    private static final Color DARK_COLOR = Color.BLUE;
    private static final int MAX_ROW_COL = 8;
    private static final int BLOCK_SIZE = 100;

    private static final int[] r1 = {0, 1, 0, 1, 0, 1, 0, 1};
    private static final int[] r2 = {1, 0, 1, 0, 1, 0, 1, 0}; 
    private static final int[][] chessBoard = {r1, r2, r1, r2, r1, r2, r1, r2};

    public void drawBoard(Graphics2D g){
        for(int row = 0; row < MAX_ROW_COL; row += 1){
            for(int col = 0; col < MAX_ROW_COL; col += 1){

                if(chessBoard[row][col] == 0){
                    g.setColor(LIGHT_COLOR);
                }else{
                    g.setColor(DARK_COLOR);
                }

                g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }
}

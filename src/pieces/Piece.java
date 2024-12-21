package pieces;

import main.Board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Piece {
    public BufferedImage pieceImage;
    public int x, y;//cordinates of piece
    public int col, row, prevCol, prevRow, startCol, startRow;
    public int color;
    public boolean isAlive = true;
    public boolean hasMoved = false;
    private static final int PIECE_PADDING = 15;
    private static final int DEAD_PIECE_SIZE = 30;
    private static final int DEAD_PIECE_PADDING = 10;
    private static final int V_PAD = 270;

    public Piece(int color, int col, int row){
        this.color = color;
        setCol(col);
        setRow(row);
        setX(this.col);
        setY(this.row);
        setPrevCol(col);
        setPrevRow(row);
        startCol = col;
        startRow = row;
    }

    protected void setPieceImage(String imagePath){
        try{

            pieceImage = ImageIO.read(new File(imagePath)); // image input output
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setX(int col){
        x = col * Board.BLOCK_SIZE;
    }

    public void setY(int row){
        y = row * Board.BLOCK_SIZE;
    }

    public void setCol(int col){
        this.col = col;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setPrevCol(int prevCol){
        this.prevCol = prevCol;
    }

    public void setPrevRow(int prevRow){
        this.prevRow = prevRow;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }

    public BufferedImage getPieceImage(){
        return pieceImage;
    }

    public void draw_piece(Graphics2D g){
        if(isAlive){
            g.drawImage(pieceImage, getX() + Board.BOARD_PADDING + PIECE_PADDING, getY() + PIECE_PADDING, Board.BLOCK_SIZE * 7 / 10, Board.BLOCK_SIZE * 7 / 10, null);
        }else{
            g.drawImage(pieceImage, startCol * DEAD_PIECE_SIZE + DEAD_PIECE_PADDING, startRow * DEAD_PIECE_SIZE + DEAD_PIECE_PADDING + V_PAD, DEAD_PIECE_SIZE, DEAD_PIECE_SIZE, null);
        }
    }
}

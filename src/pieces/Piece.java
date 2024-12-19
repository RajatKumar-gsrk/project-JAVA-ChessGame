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
    public int col, row, prevCol, prevRow;
    public int color;

    public Piece(int color, int col, int row){
        this.color = color;
        this.col = col;
        this.row = row;
        setX(this.col);
        setY(this.row);
        prevCol = col;
        prevRow = row;
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

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public BufferedImage getPieceImage(){
        return pieceImage;
    }

    public void draw_piece(Graphics2D g){
        g.drawImage(pieceImage, getX() + Board.BOARD_PADDING, getY(), Board.BLOCK_SIZE, Board.BLOCK_SIZE, null);
    }
}

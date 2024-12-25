package main;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class MouseHandler implements MouseInputListener { //use MouseInputAdapter if don't want to implement all methods

    public int x, y;
    public int col, row;
    public boolean clicked = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        clicked = true;
        x = e.getX();
        y = e.getY();
        setCol(x);
        setRow(y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void setCol(int x){
        this.col = (x - Board.BOARD_PADDING) / Board.BLOCK_SIZE;
    }

    public void setRow(int y){
        this.row = y / Board.BLOCK_SIZE;
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

}

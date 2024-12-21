package main;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class MouseHandler implements MouseInputListener { //use MouseInputAdapter if don't want to implement all methods

    public int x, y;
    public int col, row;
    public boolean pressed;
    public boolean clicked = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        setMouseCol();
        setMouseRow();
        clicked = !clicked;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        x = e.getX();
        y = e.getY();
        setMouseCol();
        setMouseRow();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        x = e.getX();
        y = e.getY();
        setMouseCol();
        setMouseRow();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void setMouseCol(){
        this.col = (getMouseX() - Board.BOARD_PADDING) / Board.BLOCK_SIZE;
    }

    public void setMouseRow(){
        this.row = getMouseY() / Board.BLOCK_SIZE;
    }

    public int getMouseX(){
        return x;
    }

    public int getMouseY(){
        return y;
    }

    public int getMouseCol(){
        return col;
    }

    public int getmouseRow(){
        return row;
    }

}

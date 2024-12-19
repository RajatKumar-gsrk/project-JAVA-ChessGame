package main;

import pieces.*;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    //fields
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 800;
    public static final int LIGHT = 0;
    public static final int DARK = 1; 
    private boolean running = true;
    private Graphics2D main_graphics;

    //entities
    private Board main_board = new Board();
    public ArrayList<Piece> Board_pieces = new ArrayList<Piece>();
    
    public GamePanel(){
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.decode("#708090"));
        resetPieces();
    }

    public void gameLoop(){
        while(running){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            updateGame();
            repaint();
        }
    }

    private void updateGame(){
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); // important to call super class method

        main_graphics = (Graphics2D) g;
        main_board.drawBoard(main_graphics);
        drawPiecesOnBoard();
    }

    public void resetPieces(){
        Board_pieces.clear();
        //LIGHT side
        Board_pieces.add(new Pawn(LIGHT, 0, 6));
        Board_pieces.add(new Pawn(LIGHT, 1, 6));
        Board_pieces.add(new Pawn(LIGHT, 2, 6));
        Board_pieces.add(new Pawn(LIGHT, 3, 6));
        Board_pieces.add(new Pawn(LIGHT, 4, 6));
        Board_pieces.add(new Pawn(LIGHT, 5, 6));
        Board_pieces.add(new Pawn(LIGHT, 6, 6));
        Board_pieces.add(new Pawn(LIGHT, 7, 6));

        Board_pieces.add(new Rook(LIGHT, 0, 7));
        Board_pieces.add(new Knight(LIGHT, 1, 7));
        Board_pieces.add(new Bishop(LIGHT, 2, 7));
        Board_pieces.add(new Queen(LIGHT, 3, 7));
        Board_pieces.add(new King(LIGHT, 4, 7));
        Board_pieces.add(new Bishop(LIGHT, 5, 7));
        Board_pieces.add(new Knight(LIGHT, 6, 7));
        Board_pieces.add(new Rook(LIGHT, 7, 7));

        //DARK side
        Board_pieces.add(new Pawn(DARK, 0, 1));
        Board_pieces.add(new Pawn(DARK, 1, 1));
        Board_pieces.add(new Pawn(DARK, 2, 1));
        Board_pieces.add(new Pawn(DARK, 3, 1));
        Board_pieces.add(new Pawn(DARK, 4, 1));
        Board_pieces.add(new Pawn(DARK, 5, 1));
        Board_pieces.add(new Pawn(DARK, 6, 1));
        Board_pieces.add(new Pawn(DARK, 7, 1));

        Board_pieces.add(new Rook(DARK, 0, 0));
        Board_pieces.add(new Knight(DARK, 1, 0));
        Board_pieces.add(new Bishop(DARK, 2, 0));
        Board_pieces.add(new Queen(DARK, 3, 0));
        Board_pieces.add(new King(DARK, 4, 0));
        Board_pieces.add(new Bishop(DARK, 5, 0));
        Board_pieces.add(new Knight(DARK, 6, 0));
        Board_pieces.add(new Rook(DARK, 7,0));
    }

    private void drawPiecesOnBoard(){
        for(int i = 0; i < Board_pieces.size();i += 1){
            Board_pieces.get(i).draw_piece(main_graphics);
        }
    }
}

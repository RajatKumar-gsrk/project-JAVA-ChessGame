package main;

import pieces.*;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public class GamePanel extends JPanel{
    //fields
    public static final int WIDTH = 1360;
    public static final int HEIGHT = 800;
    public static final int LIGHT = 0;
    public static final int DARK = 1; 
    private boolean running = true;
    private Graphics2D main_graphics;

    //entities
    private Board main_board = new Board();
    public ArrayList<Piece> White_pieces = new ArrayList<Piece>();
    public ArrayList<Piece> Black_pieces = new ArrayList<Piece>();
    public Piece activePiece;
    private MouseHandler main_mouse = new MouseHandler();
    
    public GamePanel(){
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.decode("#708090"));
        resetPieces();
        addMouseListener(main_mouse); // add both for MouseInputListener
        addMouseMotionListener(main_mouse);
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
        if(main_mouse.clicked && activePiece != null){
            updatePeiceLocation();
            activePiece = null;
        }
        if(main_mouse.clicked){
            checkActivePiece();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); // important to call super class method

        main_graphics = (Graphics2D) g;
        main_board.drawBoard(main_graphics);
        if(activePiece != null){
            drawGlowBlocks();
        }
        drawPieces();
    }

    public void resetPieces(){
        White_pieces.clear();
        //LIGHT side
        White_pieces.add(new Pawn(LIGHT, 0, 6));
        White_pieces.add(new Pawn(LIGHT, 1, 6));
        White_pieces.add(new Pawn(LIGHT, 2, 6));
        White_pieces.add(new Pawn(LIGHT, 3, 6));
        White_pieces.add(new Pawn(LIGHT, 4, 6));
        White_pieces.add(new Pawn(LIGHT, 5, 6));
        White_pieces.add(new Pawn(LIGHT, 6, 6));
        White_pieces.add(new Pawn(LIGHT, 7, 6));

        White_pieces.add(new Rook(LIGHT, 0, 7));
        White_pieces.add(new Knight(LIGHT, 1, 7));
        White_pieces.add(new Bishop(LIGHT, 2, 7));
        White_pieces.add(new Queen(LIGHT, 3, 7));
        White_pieces.add(new King(LIGHT, 4, 7));
        White_pieces.add(new Bishop(LIGHT, 5, 7));
        White_pieces.add(new Knight(LIGHT, 6, 7));
        White_pieces.add(new Rook(LIGHT, 7, 7));

        //DARK side
        Black_pieces.clear();
        Black_pieces.add(new Pawn(DARK, 0, 1));
        Black_pieces.add(new Pawn(DARK, 1, 1));
        Black_pieces.add(new Pawn(DARK, 2, 1));
        Black_pieces.add(new Pawn(DARK, 3, 1));
        Black_pieces.add(new Pawn(DARK, 4, 1));
        Black_pieces.add(new Pawn(DARK, 5, 1));
        Black_pieces.add(new Pawn(DARK, 6, 1));
        Black_pieces.add(new Pawn(DARK, 7, 1));

        Black_pieces.add(new Rook(DARK, 0, 0));
        Black_pieces.add(new Knight(DARK, 1, 0));
        Black_pieces.add(new Bishop(DARK, 2, 0));
        Black_pieces.add(new Queen(DARK, 3, 0));
        Black_pieces.add(new King(DARK, 4, 0));
        Black_pieces.add(new Bishop(DARK, 5, 0));
        Black_pieces.add(new Knight(DARK, 6, 0));
        Black_pieces.add(new Rook(DARK, 7,0));
    }

    private void drawPieces(){
        for(int i = 0; i < White_pieces.size();i += 1){
            White_pieces.get(i).draw_piece(main_graphics);
        }

        for(int i = 0; i < Black_pieces.size();i += 1){
            Black_pieces.get(i).draw_piece(main_graphics);
        }
    }

    public void checkActivePiece(){
        for(int i = 0; i < White_pieces.size(); i += 1){
            if(White_pieces.get(i).isAlive && White_pieces.get(i).getCol() == main_mouse.getMouseCol() && White_pieces.get(i).getRow() == main_mouse.getmouseRow()){
                activePiece =  White_pieces.get(i);
                //System.out.println(activePiece.getCol()+", "+ activePiece.getRow());
            }
        }
        for(int i = 0; i < Black_pieces.size(); i += 1){
            if(Black_pieces.get(i).isAlive && Black_pieces.get(i).getCol() == main_mouse.getMouseCol() && Black_pieces.get(i).getRow() == main_mouse.getmouseRow()){
                activePiece = Black_pieces.get(i);
            }
        }
        main_mouse.clicked = false;
    }

    public void updatePeiceLocation(){
       // System.out.println("updating active peice");
        if(validPosition()){
            activePiece.setCol(main_mouse.getMouseCol());
            activePiece.setRow(main_mouse.getmouseRow());
            activePiece.setX(activePiece.getCol());
            activePiece.setY(activePiece.getRow());
        }else{
            //print not valid
        }
        main_mouse.clicked = false;
    }

    private boolean validPosition(){
        if(activePiece.color == LIGHT){
            for(Piece w: White_pieces){
                if(w.getCol() == main_mouse.getMouseCol() && w.getRow() == main_mouse.getmouseRow()){
                    return false;
                }
            }
        }else if(activePiece.color == DARK){
            for(Piece b: Black_pieces){
                if(b.getCol() == main_mouse.getMouseCol() && b.getRow() == main_mouse.getmouseRow()){
                    return false;
                }
            }
        }
        return true;
    }

    private void drawGlowBlocks(){
        drawActiveBlock();
    }

    public void drawActiveBlock(){
        main_graphics.setColor(new Color(0, 255, 0, 100));
        Stroke default_stroke = main_graphics.getStroke();
        main_graphics.setStroke(new BasicStroke(8));
        main_graphics.drawRect(activePiece.getCol() * Board.BLOCK_SIZE + Board.BOARD_PADDING, activePiece.getRow() * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
        main_graphics.setStroke(default_stroke);
    }
}

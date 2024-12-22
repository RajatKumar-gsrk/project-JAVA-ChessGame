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
    public static final int PAWN = 0;
    public static final int ROOK= 1;
    public static final int KNIGHT = 2;
    public static final int BISHOP = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;  
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
        if(activePiece != null){
            updatePeiceLocation();
        }
        if(main_mouse.clicked){
            checkActivePiece();
            System.out.println("check active peice");
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
            if(White_pieces.get(i).isAlive && White_pieces.get(i).getCol() == main_mouse.getCol() && White_pieces.get(i).getRow() == main_mouse.getRow()){
                activePiece =  White_pieces.get(i);
            }
        }
        for(int i = 0; i < Black_pieces.size(); i += 1){
            if(Black_pieces.get(i).isAlive && Black_pieces.get(i).getCol() == main_mouse.getCol() && Black_pieces.get(i).getRow() == main_mouse.getRow()){
                activePiece = Black_pieces.get(i);
            }
        }
        main_mouse.clicked = false;
    }

    public void updatePeiceLocation(){
        activePiece.getMoves();
        removeCollisions();

        for(int[] move: activePiece.moves){
            if(main_mouse.getCol() == move[0] && main_mouse.getRow() == move[1]){
                //set new col, row and move piece tehre
                activePiece.setCol(move[0]);
                activePiece.setRow(move[1]);
                activePiece.setX(move[0]);
                activePiece.setY(move[1]);

                activePiece.setHasMoved();
                getKills();

                main_mouse.clicked = false;
                activePiece = null;
                break;
            }
        }

        if(activePiece != null && main_mouse.getCol() != activePiece.getCol() && main_mouse.getRow() != activePiece.getRow()){
            main_mouse.clicked = false;
            activePiece = null;
        }
    }

    private void removeCollisions(){
        if(activePiece.color == LIGHT){
            for(Piece w: White_pieces){
                int i = 0;
                while(i < activePiece.moves.size()){
                    if(w.getCol() == activePiece.moves.get(i)[0] && w.getRow() == activePiece.moves.get(i)[1]){
                        activePiece.moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }
        }else if(activePiece.color == DARK){
            for(Piece b: Black_pieces){
                int i = 0;
                while(i < activePiece.moves.size()){
                    if(b.getCol() == activePiece.moves.get(i)[0] && b.getRow() == activePiece.moves.get(i)[1]){
                        activePiece.moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }
        }
    }

    private void getKills(){
        if(activePiece.color == DARK){
            for(Piece w: White_pieces){
                if(w.getCol() == activePiece.getCol() && w.getRow() == activePiece.getRow()){
                    w.setCol(900 + Board.BOARD_PADDING);
                    w.setRow(900);
                    w.isAlive = false;
                    break;
                }
            }
        }else{
            for(Piece b: Black_pieces){
                if(b.getCol() == activePiece.getCol() && b.getRow() == activePiece.getRow()){
                    b.setCol(900 + Board.BOARD_PADDING);
                    b.setRow(900);
                    b.isAlive = false;
                    break;
                }
            }
        }
    }

    private void drawGlowBlocks(){
        glowActiveBlock();
        glowValidBlocks();
        glowKillBlocks();
    }

    public void glowActiveBlock(){
        main_graphics.setColor(Color.decode("#50C878"));
        main_graphics.fillRect(activePiece.getCol() * Board.BLOCK_SIZE + Board.BOARD_PADDING, activePiece.getRow() * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
    }

    public void glowValidBlocks(){
        Stroke default_stroke = main_graphics.getStroke();
        main_graphics.setStroke(new BasicStroke(5));
        for(int[] move: activePiece.moves){
            main_graphics.setColor(Color.decode("#50C878"));
            main_graphics.drawRect(move[0] * Board.BLOCK_SIZE + Board.BOARD_PADDING, move[1] * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
        }
        main_graphics.setStroke(default_stroke);
    }

    public void glowKillBlocks(){
        main_graphics.setColor(Color.decode("#D21F3C"));
        if(activePiece.color == LIGHT){
            for(int[] move: activePiece.moves){
                for(Piece b: Black_pieces){
                    if(b.getCol() == move[0] && b.getRow() == move[1]){
                        main_graphics.fillRect(move[0] * Board.BLOCK_SIZE + Board.BOARD_PADDING, move[1] * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
                        continue;
                    }
                }
            }
        }else{
            for(int[] move: activePiece.moves){
                for(Piece w: White_pieces){
                    if(w.getCol() == move[0] && w.getRow() == move[1]){
                        main_graphics.fillRect(move[0] * Board.BLOCK_SIZE + Board.BOARD_PADDING, move[1] * Board.BLOCK_SIZE, Board.BLOCK_SIZE, Board.BLOCK_SIZE);
                        continue;
                    }
                }
            }
        }
    }
}

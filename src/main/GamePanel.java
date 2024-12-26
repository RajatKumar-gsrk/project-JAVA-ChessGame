package main;

import pieces.*;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
    private boolean promoting = false;
    private Graphics2D main_graphics;
    private int currentTurn = LIGHT;
    public int promotionType = 10;//update type
    public int promotionPieceIndx = 10;//which one is to be updated
    public static final int N = 1, NE = 2, E = 3, SE = 4, S = 5, SW = 6, W = 7, NW = 8;

    //entities
    private Board main_board = new Board();
    public ArrayList<Piece> White_pieces = new ArrayList<Piece>();//stores current situation
    public ArrayList<Piece> Black_pieces = new ArrayList<Piece>();
    public ArrayList<Piece> prevW_pieces = new ArrayList<Piece>();//stores move history
    public ArrayList<Piece> prevB_pieces = new ArrayList<Piece>();
    public ArrayList<Piece> proW_pieces = new ArrayList<Piece>();//stores promotion type pieces
    public ArrayList<Piece> proB_pieces = new ArrayList<Piece>();
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

    public void resetPieces(){
        White_pieces.clear();
        //LIGHT side
        White_pieces.add(new Pawn(LIGHT, 0, 6)); //index 0
        White_pieces.add(new Pawn(LIGHT, 1, 6)); //index 1
        White_pieces.add(new Pawn(LIGHT, 2, 6)); //index 2
        White_pieces.add(new Pawn(LIGHT, 3, 6)); //index 3
        White_pieces.add(new Pawn(LIGHT, 4, 6)); //index 4
        White_pieces.add(new Pawn(LIGHT, 5, 6)); //index 5
        White_pieces.add(new Pawn(LIGHT, 6, 6)); //index 6
        White_pieces.add(new Pawn(LIGHT, 7, 6)); //index 7

        White_pieces.add(new Rook(LIGHT, 0, 7)); //index 8
        White_pieces.add(new Knight(LIGHT, 1, 7)); //index 9
        White_pieces.add(new Bishop(LIGHT, 2, 7)); //index 10
        White_pieces.add(new Queen(LIGHT, 3, 7)); //index 11
        White_pieces.add(new King(LIGHT, 4, 7)); //index 12
        White_pieces.add(new Bishop(LIGHT, 5, 7)); //index 13
        White_pieces.add(new Knight(LIGHT, 6, 7)); //index 14
        White_pieces.add(new Rook(LIGHT, 7, 7)); //index 15

        //LIGHT history
        prevW_pieces.clear();
        prevW_pieces.add(new Pawn(LIGHT, 0, 6)); //index 0
        prevW_pieces.add(new Pawn(LIGHT, 1, 6)); //index 1
        prevW_pieces.add(new Pawn(LIGHT, 2, 6)); //index 2
        prevW_pieces.add(new Pawn(LIGHT, 3, 6)); //index 3
        prevW_pieces.add(new Pawn(LIGHT, 4, 6)); //index 4
        prevW_pieces.add(new Pawn(LIGHT, 5, 6)); //index 5
        prevW_pieces.add(new Pawn(LIGHT, 6, 6)); //index 6
        prevW_pieces.add(new Pawn(LIGHT, 7, 6)); //index 7

        prevW_pieces.add(new Rook(LIGHT, 0, 7)); //index 8
        prevW_pieces.add(new Knight(LIGHT, 1, 7)); //index 9
        prevW_pieces.add(new Bishop(LIGHT, 2, 7)); //index 10
        prevW_pieces.add(new Queen(LIGHT, 3, 7)); //index 11
        prevW_pieces.add(new King(LIGHT, 4, 7)); //index 12
        prevW_pieces.add(new Bishop(LIGHT, 5, 7)); //index 13
        prevW_pieces.add(new Knight(LIGHT, 6, 7)); //index 14
        prevW_pieces.add(new Rook(LIGHT, 7, 7)); //index 15

        //DARK side
        Black_pieces.clear();
        Black_pieces.add(new Pawn(DARK, 0, 1)); //index 0
        Black_pieces.add(new Pawn(DARK, 1, 1)); //index 1
        Black_pieces.add(new Pawn(DARK, 2, 1)); //index 2
        Black_pieces.add(new Pawn(DARK, 3, 1)); //index 3
        Black_pieces.add(new Pawn(DARK, 4, 1)); //index 4
        Black_pieces.add(new Pawn(DARK, 5, 1)); //index 5
        Black_pieces.add(new Pawn(DARK, 6, 1)); //index 6
        Black_pieces.add(new Pawn(DARK, 7, 1)); //index 7

        Black_pieces.add(new Rook(DARK, 0, 0)); //index 8
        Black_pieces.add(new Knight(DARK, 1, 0)); //index 9
        Black_pieces.add(new Bishop(DARK, 2, 0)); //index 10
        Black_pieces.add(new Queen(DARK, 3, 0)); //index 11
        Black_pieces.add(new King(DARK, 4, 0)); //index 12
        Black_pieces.add(new Bishop(DARK, 5, 0)); //index 13
        Black_pieces.add(new Knight(DARK, 6, 0)); //index 14
        Black_pieces.add(new Rook(DARK, 7,0)); //index 15

        //DARK history
        prevB_pieces.clear();
        prevB_pieces.add(new Pawn(DARK, 0, 1)); //index 0
        prevB_pieces.add(new Pawn(DARK, 1, 1)); //index 1
        prevB_pieces.add(new Pawn(DARK, 2, 1)); //index 2
        prevB_pieces.add(new Pawn(DARK, 3, 1)); //index 3
        prevB_pieces.add(new Pawn(DARK, 4, 1)); //index 4
        prevB_pieces.add(new Pawn(DARK, 5, 1)); //index 5
        prevB_pieces.add(new Pawn(DARK, 6, 1)); //index 6
        prevB_pieces.add(new Pawn(DARK, 7, 1)); //index 7

        prevB_pieces.add(new Rook(DARK, 0, 0)); //index 8
        prevB_pieces.add(new Knight(DARK, 1, 0)); //index 9
        prevB_pieces.add(new Bishop(DARK, 2, 0)); //index 10
        prevB_pieces.add(new Queen(DARK, 3, 0)); //index 11
        prevB_pieces.add(new King(DARK, 4, 0)); //index 12
        prevB_pieces.add(new Bishop(DARK, 5, 0)); //index 13
        prevB_pieces.add(new Knight(DARK, 6, 0)); //index 14
        prevB_pieces.add(new Rook(DARK, 7,0)); //index 15

        //promotion Light
        proW_pieces.clear();
        proW_pieces.add(new Queen(LIGHT, 9, 2));
        proW_pieces.add(new Bishop(LIGHT, 9, 3));
        proW_pieces.add(new Knight(LIGHT, 9, 4));
        proW_pieces.add(new Rook(LIGHT, 9, 5));

        //promoting DARK
        proB_pieces.clear();
        proB_pieces.add(new Queen(DARK, 9, 2));
        proB_pieces.add(new Bishop(DARK, 9, 3));
        proB_pieces.add(new Knight(DARK, 9, 4));
        proB_pieces.add(new Rook(DARK, 9, 5));

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
            if(!promoting){
                updatePeiceLocation();
            }
        }
        if(promoting){
            promotePiece();
        }
        checkForPromotion();
        if(main_mouse.clicked){
            if(promoting){
                checkPromotedPiece();
            }else{
                checkActivePiece();
            }
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
        if(promoting){
            drawPromotion();
        }else{
            drawTurns();
        }
    }

    public void updateHistory(){
        if(currentTurn == LIGHT){
            
            for(int i = 0; i < 16; i += 1){
                prevB_pieces.get(i).setCol(Black_pieces.get(i).getCol());
                prevB_pieces.get(i).setRow(Black_pieces.get(i).getRow());
            }
        }else{

            for(int i = 0; i < 16; i += 1){
                prevW_pieces.get(i).setCol(White_pieces.get(i).getCol());
                prevW_pieces.get(i).setRow(White_pieces.get(i).getRow());
            }
        }
    }

    public void recoverHistory(){
        if(currentTurn == LIGHT){
            for(int i = 0; i < 16; i += 1){
                Black_pieces.get(i).setCol(prevB_pieces.get(i).getCol());
                Black_pieces.get(i).setRow(prevB_pieces.get(i).getRow());
            }
        }else{
            for(int i = 0; i < 16; i += 1){
                White_pieces.get(i).setCol(prevW_pieces.get(i).getCol());
                White_pieces.get(i).setRow(prevW_pieces.get(i).getRow());
            }
        }
    }

    public void promotePiece(){//replaces peice
        if(currentTurn == DARK){
            if(promotionType < 10 && promotionPieceIndx < 8){
                int col = White_pieces.get(promotionPieceIndx).getCol();
                if(promotionType == QUEEN){
                    White_pieces.set(promotionPieceIndx, new Queen(LIGHT, promotionPieceIndx, 6));
                    White_pieces.get(promotionPieceIndx).setCol(col);
                    White_pieces.get(promotionPieceIndx).setRow(0);
                }else if(promotionType == BISHOP){
                    White_pieces.set(promotionPieceIndx, new Bishop(LIGHT, promotionPieceIndx, 6));
                    White_pieces.get(promotionPieceIndx).setCol(col);
                    White_pieces.get(promotionPieceIndx).setRow(0);
                }else if(promotionType == KNIGHT){
                    White_pieces.set(promotionPieceIndx, new Knight(LIGHT, promotionPieceIndx, 6));
                    White_pieces.get(promotionPieceIndx).setCol(col);
                    White_pieces.get(promotionPieceIndx).setRow(0);
                }else if(promotionType == ROOK){
                    White_pieces.set(promotionPieceIndx, new Rook(LIGHT, promotionPieceIndx, 6));
                    White_pieces.get(promotionPieceIndx).setCol(col);
                    White_pieces.get(promotionPieceIndx).setRow(0);
                }

                promoting = false;
                promotionPieceIndx = 10;
                promotionType = 10;

                return;
            }
        }else{
            if(promotionType < 10 && promotionPieceIndx < 8){
                int col = Black_pieces.get(promotionPieceIndx).getCol();
                if(promotionType == QUEEN){
                    Black_pieces.set(promotionPieceIndx, new Queen(DARK, promotionPieceIndx, 1));
                    Black_pieces.get(promotionPieceIndx).setCol(col);
                    Black_pieces.get(promotionPieceIndx).setRow(7);
                }else if(promotionType == BISHOP){
                    Black_pieces.set(promotionPieceIndx, new Bishop(DARK, promotionPieceIndx, 1));
                    Black_pieces.get(promotionPieceIndx).setCol(col);
                    Black_pieces.get(promotionPieceIndx).setRow(7);
                }else if(promotionType == KNIGHT){
                    Black_pieces.set(promotionPieceIndx, new Knight(DARK, promotionPieceIndx,1));
                    Black_pieces.get(promotionPieceIndx).setCol(col);
                    Black_pieces.get(promotionPieceIndx).setRow(7);
                }else if(promotionType == ROOK){
                    Black_pieces.set(promotionPieceIndx, new Rook(DARK, promotionPieceIndx, 1));
                    Black_pieces.get(promotionPieceIndx).setCol(col);
                    Black_pieces.get(promotionPieceIndx).setRow(7);
                }
                promoting = false;
                promotionPieceIndx = 10;
                promotionType = 10;

                return;
            }
        }
    }

    private void checkPromotedPiece(){//check what is new peice type
        if(currentTurn == DARK){
            for(Piece w: proW_pieces){
                if(w.getCol() == main_mouse.getCol() && w.getRow() == main_mouse.getRow()){
                    promotionType = w.piece_type;
                }
            }
        }else{
            for(Piece b: proB_pieces){
                if(b.getCol() == main_mouse.getCol() && b.getRow() == main_mouse.getRow()){
                    promotionType = b.piece_type;
                }
            }
        }
    }

    public void checkActivePiece(){
        if(currentTurn == LIGHT){
            for(int i = 0; i < White_pieces.size(); i += 1){
                if(White_pieces.get(i).isAlive && White_pieces.get(i).getCol() == main_mouse.getCol() && White_pieces.get(i).getRow() == main_mouse.getRow()){
                    activePiece =  White_pieces.get(i);
                }
            }
        }else{
            for(int i = 0; i < Black_pieces.size(); i += 1){
                if(Black_pieces.get(i).isAlive && Black_pieces.get(i).getCol() == main_mouse.getCol() && Black_pieces.get(i).getRow() == main_mouse.getRow()){
                    activePiece = Black_pieces.get(i);
                }
            }
        }
        main_mouse.clicked = false;
    }

    public void updatePeiceLocation(){ //sequence of this funtion matters MOST
        activePiece.getMoves();

        if(activePiece.piece_type != KNIGHT){
            if(activePiece.piece_type != KING){
                addBlockedPath(activePiece);
            }else{
                kingIllegalMoves(activePiece);
            }
            activePiece.removeBlockedPath();
        }

        removeCollisions(activePiece);

        if(activePiece.piece_type == PAWN){
            pawnDiagonalKill(activePiece);
            pawnEnPaasant(activePiece);
        }else if(activePiece.piece_type == KING){
            addKingSpecialMoves();
        }
        

        for(int[] move: activePiece.moves){
            if(main_mouse.getCol() == move[0] && main_mouse.getRow() == move[1]){
                //set new col, row and move piece there
                if(activePiece.piece_type == KING){//moving rooks is castling
                    castlingMoveRook(move[0]);
                }else if(activePiece.piece_type == PAWN){
                    enPassantKill(move[0], move[1]);
                }
                activePiece.setCol(move[0]);
                activePiece.setRow(move[1]);
                getKills();

                updateHistory();

                activePiece.moves.clear();
                activePiece.blockedPath.clear();
                main_mouse.clicked = false;
                activePiece = null;
                changeColor();
                break;
            }
        }
        //cancel move
        if(activePiece != null && (main_mouse.getCol() != activePiece.getCol() || main_mouse.getRow() != activePiece.getRow())){
            main_mouse.clicked = false;
            activePiece = null;
        }
    }

    public void checkForPromotion(){
        if(currentTurn == DARK){
            for(int i = 0; i < 8; i += 1){
                if(White_pieces.get(i).getRow() == 0 && White_pieces.get(i).piece_type == PAWN){
                    promotionPieceIndx = i;
                    promoting = true;
                    return;
                }
            }
        }else{
            for(int i = 0; i < 8; i += 1){
                if(Black_pieces.get(i).getRow() == 7 && Black_pieces.get(i).piece_type == PAWN){
                    promotionPieceIndx = i;
                    promoting = true;
                    return;
                }
            }
        }
    }

    private void removeCollisions(Piece p){
        if(p.color == LIGHT || p.piece_type == PAWN){
            for(Piece w: White_pieces){
                if(!w.isAlive){
                    continue;
                }
                int i = 0;
                while(i < p.moves.size()){
                    if(w.getCol() == p.moves.get(i)[0] && w.getRow() == p.moves.get(i)[1]){
                        p.moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }
        }
        if(p.color == DARK || p.piece_type == PAWN){
            for(Piece b: Black_pieces){
                if(!b.isAlive){
                    continue;
                }
                int i = 0;
                while(i < p.moves.size()){
                    if(b.getCol() == p.moves.get(i)[0] && b.getRow() == p.moves.get(i)[1]){
                        p.moves.remove(i);
                    }else{
                        i += 1;
                    }
                }
            }
        }
    }

    public void pawnDiagonalKill(Piece p){
        int c = p.getCol();
        int r = p.getRow();

        if(p.color == DARK && r < 7){
            for(Piece w: White_pieces){
                if(w.getCol() == c - 1 && w.getRow() == r + 1){
                    p.moves.add(new int[]{c - 1, r + 1});
                }
                if(w.getCol() == c + 1 && w.getRow() == r + 1){
                    p.moves.add(new int[]{c + 1, r + 1});
                }
            }
        }else if(p.color == LIGHT && r > 0){
            for(Piece b: Black_pieces){
                if(b.getCol() == c - 1 && b.getRow() == r - 1){
                    p.moves.add(new int[]{c - 1, r - 1});
                }
                if(b.getCol() == c + 1 && b.getRow() == r - 1){
                    p.moves.add(new int[]{c + 1, r - 1});
                }
            }
        }
    }

    public void pawnEnPaasant(Piece p){
        if(currentTurn == LIGHT){
            for(int i = 0; i < 8; i += 1){
                if(Black_pieces.get(i).piece_type == PAWN && !prevB_pieces.get(i).hasMoved && prevB_pieces.get(i).getRow() == Black_pieces.get(i).getRow() - 2){
                    if(Black_pieces.get(i).getRow() == p.getRow()){
                        if(Black_pieces.get(i).getCol() == p.getCol() - 1){
                            p.moves.add(new int[]{p.getCol() - 1, p.getRow() - 1});
                        }else if(Black_pieces.get(i).getCol() == p.getCol() + 1){
                            p.moves.add(new int[]{p.getCol() + 1, p.getRow() - 1});
                        }
                    }
                }
            }
        }else{
            for(int i = 0; i < 8; i += 1){
                if(White_pieces.get(i).piece_type == PAWN && prevW_pieces.get(i).hasMoved && prevW_pieces.get(i).getRow() == White_pieces.get(i).getRow() + 2){
                    if(White_pieces.get(i).getRow() == p.getRow()){
                        if(White_pieces.get(i).getCol() == p.getCol() - 1){
                            p.moves.add(new int[]{p.getCol() - 1, p.getRow() + 1});
                        }else if(White_pieces.get(i).getCol() == p.getCol() + 1){
                            p.moves.add(new int[]{p.getCol() + 1, p.getRow() + 1});
                        }
                    }
                }
            }
        }
    }

    public void enPassantKill(int col, int row){
        if(currentTurn == DARK){
            for(Piece w: White_pieces){
                if(w.getCol() == col && w.getRow() == row){
                    return;
                }
            }
            for(int i = 0; i < 8; i += 1){
                if(White_pieces.get(i).getCol() == col && White_pieces.get(i).getRow() == row - 1){
                    White_pieces.get(i).isDead();
                    return;
                }
            }
        }else{
            for(Piece b: Black_pieces){
                if(b.getCol() == col && b.getRow() == row){
                    return;
                }
            }
            for(int i = 0; i < 8; i += 1){
                if(Black_pieces.get(i).getCol() == col && Black_pieces.get(i).getRow() == row + 1){
                    Black_pieces.get(i).isDead();
                    return;
                }
            }
        }
    }

    public void addKingSpecialMoves(){
        castlingKing();
    }

    public void castlingKing(){
        if(!activePiece.hasMoved){
            if(activePiece.color == LIGHT){
                if(!White_pieces.get(8).hasMoved && White_pieces.get(8).isAlive){
                    boolean pieceBetween = false;
                    for(Piece w: White_pieces){
                        if(pieceBetween || (w.getCol() == 1 && w.getRow() == 7) || (w.getCol() == 2 && w.getRow() == 7) || (w.getCol() == 3 && w.getRow() == 7)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    for(Piece b: Black_pieces){
                        if(pieceBetween || (b.getCol() == 1 && b.getRow() == 7) || (b.getCol() == 2 && b.getRow() == 7) || (b.getCol() == 3 && b.getRow() == 7)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    if(!pieceBetween){
                        activePiece.moves.add(new int[]{2, 7});
                    }
                }
                if(!White_pieces.get(15).hasMoved && White_pieces.get(15).isAlive){
                    boolean pieceBetween = false;
                    for(Piece w: White_pieces){
                        if(pieceBetween || (w.getCol() == 6 && w.getRow() == 7) || (w.getCol() == 5 && w.getRow() == 7)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    for(Piece b: Black_pieces){
                        if(pieceBetween || (b.getCol() == 6 && b.getRow() == 7) || (b.getCol() == 5 && b.getRow() == 7)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    if(!pieceBetween){
                        activePiece.moves.add(new int[]{6, 7});
                    }
                }
            }else{
                if(!Black_pieces.get(8).hasMoved && Black_pieces.get(8).isAlive){
                    boolean pieceBetween = false;
                    for(Piece w: White_pieces){
                        if(pieceBetween || (w.getCol() == 1 && w.getRow() == 0) || (w.getCol() == 2 && w.getRow() == 0) || (w.getCol() == 3 && w.getRow() == 0)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    for(Piece b: Black_pieces){
                        if(pieceBetween || (b.getCol() == 1 && b.getRow() == 0) || (b.getCol() == 2 && b.getRow() == 0) || (b.getCol() == 3 && b.getRow() == 0)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    if(!pieceBetween){
                        activePiece.moves.add(new int[]{2, 0});
                    }
                }
                if(!Black_pieces.get(15).hasMoved && Black_pieces.get(8).isAlive){
                    boolean pieceBetween = false;
                    for(Piece w: White_pieces){
                        if(pieceBetween || (w.getCol() == 6 && w.getRow() == 0) || (w.getCol() == 5 && w.getRow() == 0)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    for(Piece b: Black_pieces){
                        if(pieceBetween || (b.getCol() == 6 && b.getRow() == 0) || (b.getCol() == 5 && b.getRow() == 0)){
                            pieceBetween = true;
                            break;
                        }
                    }
                    if(!pieceBetween){
                        activePiece.moves.add(new int[]{6, 0});
                    }
                }
            }
        }
    }

    public void castlingMoveRook(int col){
        if(col == activePiece.getCol() - 2){
            if(activePiece.color == LIGHT){
                White_pieces.get(8).setCol(3);
                White_pieces.get(8).hasMoved = true;
            }else{
                Black_pieces.get(8).setCol(3);
                Black_pieces.get(8).hasMoved = true;
            }
        }else if(col == activePiece.getCol() + 2){
            if(activePiece.color == LIGHT){
                White_pieces.get(15).setCol(5);
                White_pieces.get(15).hasMoved = true;
            }else{
                Black_pieces.get(15).setCol(5);
                Black_pieces.get(15).hasMoved = true;
            }
        }
    }

    public void addBlockedPath(Piece p){
        p.blockedPath.clear();
        for(int[] move: p.moves){
            for(Piece w : White_pieces){
                if(!w.isAlive){
                    continue;
                }
                if(move[0] == w.getCol() && move[1] == w.getRow()){
                    int relativePos = determinePos(p, move[0], move[1]);
                    p.blockedPath.add(new int[]{move[0], move[1], relativePos});
                }
            }

            for(Piece b : Black_pieces){
                if(!b.isAlive){
                    continue;
                }
                if(move[0] == b.getCol() && move[1] == b.getRow()){
                    int relativePos = determinePos(p, move[0], move[1]);
                    p.blockedPath.add(new int[]{move[0], move[1], relativePos});
                }
            }
        }
    }

    public void addBlockedPathNoKing(Piece p){
        p.blockedPath.clear();
        for(int[] move: p.moves){
            for(Piece w : White_pieces){
                if(!w.isAlive || w.piece_type == KING){
                    continue;
                }
                if(move[0] == w.getCol() && move[1] == w.getRow()){
                    int relativePos = determinePos(p, move[0], move[1]);
                    p.blockedPath.add(new int[]{move[0], move[1], relativePos});
                }
            }

            for(Piece b : Black_pieces){
                if(!b.isAlive || b.piece_type == KING){
                    continue;
                }
                if(move[0] == b.getCol() && move[1] == b.getRow()){
                    int relativePos = determinePos(p, move[0], move[1]);
                    p.blockedPath.add(new int[]{move[0], move[1], relativePos});
                }
            }
        }
    }

    public void kingIllegalMoves(Piece p){
        if(p.color == DARK){
            for(int[] pMove: p.moves){
                Piece dummy = new King(DARK, pMove[0], pMove[1]);
                Black_pieces.add(dummy);
                for(Piece w: White_pieces){
                    if(w.piece_type == KING || !w.isAlive){
                        continue;
                    }
                    w.getMoves();
                    if(w.piece_type == PAWN){
                        removeCollisions(w);
                        pawnDiagonalKill(w);
                    }
                    addBlockedPathNoKing(w);
                    w.removeBlockedPath();
                    

                    for(int[] wMove: w.moves){
                        if(pMove[0] == wMove[0] && pMove[1] == wMove[1]){
                            System.out.println("piece location: "+ w.getCol() + ", " + w.getRow());
                            p.blockedPath.add(new int[]{pMove[0], pMove[1]});
                        }
                    }
                }
                Black_pieces.remove(16);
            }
        }else{
            for(int[] pMove: p.moves){
                Piece dummy = new King(LIGHT, pMove[0], pMove[1]);
                White_pieces.add(dummy);
                for(Piece w: Black_pieces){
                    if(w.piece_type == KING || !w.isAlive){
                        continue;
                    }
                    w.getMoves();
                    if(w.piece_type == PAWN){
                        removeCollisions(w);
                        pawnDiagonalKill(w);
                    }
                    addBlockedPathNoKing(w);
                    w.removeBlockedPath();

                    for(int[] wMove: w.moves){
                        if(pMove[0] == wMove[0] && pMove[1] == wMove[1]){
                            System.out.println("piece location: "+ w.getCol() + ", " + w.getRow());
                            p.blockedPath.add(new int[]{pMove[0], pMove[1], w.piece_type});
                        }
                    }
                }
                White_pieces.remove(16);
            }
        }
    }
    public int determinePos(Piece p, int c, int r){
        int x = p.getCol();
        int y = p.getRow();

        int pos = 0, up = 0, down = 0, left = 0, right = 0;
        if(r < y){
            up = 1;
        }
        else if(r > y){
            down = 1;
        }
        if(c < x){
            left = 1;
        }
        else if(c > x){
            right = 1;
        }

        if(up == 1 && left == 1){
            pos = NW;
        }
        else if(up == 1 && right == 1){
            pos = NE;
        }
        else if(down == 1 && left == 1){
            pos = SW;
        }
        else if(down == 1 && right == 1){
            pos = SE;
        }
        else if(up == 1){
            pos = N;
        }
        else if(down == 1){
            pos = S;
        }
        else if(left == 1){
            pos = W;
        }
        else if(right == 1){
            pos = E;
        }
        return pos;
    }


    private void getKills(){
        if(activePiece.color == DARK){
            for(Piece w: White_pieces){
                if(w.getCol() == activePiece.getCol() && w.getRow() == activePiece.getRow()){
                    w.isDead();
                    break;
                }
            }
        }else{
            for(Piece b: Black_pieces){
                if(b.getCol() == activePiece.getCol() && b.getRow() == activePiece.getRow()){
                    b.isDead();
                    break;
                }
            }
        }
    }

    public void changeColor(){
        if(currentTurn == LIGHT){
            currentTurn = DARK;
        }else{
            currentTurn = LIGHT;
        }
    }

    private void drawPieces(){
        for(int i = 0; i < White_pieces.size();i += 1){
            White_pieces.get(i).draw_piece(main_graphics);
        }

        for(int i = 0; i < Black_pieces.size();i += 1){
            Black_pieces.get(i).draw_piece(main_graphics);
        }
    }

    private void drawPromotion(){
        main_graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        main_graphics.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        main_graphics.setColor(Color.decode("#FFD100"));
        main_graphics.drawString("PROMOTION", 1115, 200);
        if(currentTurn == DARK){
            for(Piece w: proW_pieces){
                w.draw_piece(main_graphics);
            }
        }else{
            for(Piece b: proB_pieces){
                b.draw_piece(main_graphics);
            }
        }
    }
    private void drawTurns(){
        main_graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        main_graphics.setFont(new Font("Book Antiqua", Font.BOLD, 30));
        main_graphics.setColor(Color.decode("#FFD100"));
        if(currentTurn == LIGHT){
            main_graphics.drawString("WHITE's turn", 1115, 400);
        }else{
            main_graphics.drawString("BLACK's turn", 1115, 400);
        }
    }

    private void drawGlowBlocks(){
        glowActiveBlock();
        glowKillBlocks();
        glowValidBlocks();
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

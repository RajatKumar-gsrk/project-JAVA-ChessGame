package main;

import javax.swing.JFrame;

public class ChessGameMain{

    public static final int WIDTH = 1280;
    public static final int HIGTH = 720;
    public static void main(String[] args){
        JFrame main_frame = new JFrame();
        main_frame.setTitle("ChessGame");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setResizable(false);
        main_frame.setVisible(true);

        GamePanel main_Panel = new GamePanel();
        main_frame.add(main_Panel);
        main_frame.pack();
    }
}
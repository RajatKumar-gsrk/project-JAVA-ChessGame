package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel{
    
    public GamePanel(){
        setPreferredSize(new Dimension(ChessGameMain.WIDTH, ChessGameMain.HIGTH));
        setBackground(Color.BLACK);
    }
}

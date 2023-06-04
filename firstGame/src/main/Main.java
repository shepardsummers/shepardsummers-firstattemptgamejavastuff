package main;

import javax.swing.JFrame;
import main.GamePanel.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("THE BEST GAME THERE EVER WAS");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //fits window to preferred size

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();

    }

}
package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import object.SuperObject;
import tiles.TileMangager;

public class GamePanel extends JPanel implements Runnable {
    
    //SCREEN SETTINGS
    final int origionalTileSize = 16; // 16x16 tiles
    final int scale = 3; // scales tiles up by 3

    public final int tileSize = origionalTileSize * scale; // scales up tiles (public makes it able to access from other packages)
    public final int maxScreenCol = 16; //screen width in tiles
    public final int maxScreenRow = 12; //screen height in tiles
    public final int screenWidth = tileSize * maxScreenCol; //screen width in pixels 768p
    public final int screenHeight = tileSize * maxScreenRow; //screen heigh in pixels 576p

    //WORLD MAP PARAMETERS
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 20;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;
    
    //FPS
    int FPS = 60;
    
    TileMangager tileM = new TileMangager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //basically a clock
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel () {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight) ); //sets screen dimensions
        this.setBackground(Color.black); //sets background color
        this.setDoubleBuffered(true); //improves rendering preformance 

        this.addKeyListener(keyH); //now listens for a key press
        this.setFocusable(true); //game panel can be focused to recieve key input

    }
    
    public void setupGame () {
    	
    	aSetter.setObject();
    	
    }

    public void startGameThread () {

        gameThread = new Thread(this); //creates new clock
        gameThread.start(); //starts the new clock

    }

    public void run() {

        double drawInterval = 1000000000/FPS; // 1/60 secconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // long timer = 0;
        // int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime(); //gets current time

            delta += (currentTime - lastTime) / drawInterval; //creates a fraction where 1 is the drawinterval
            // timer = (currentTime - lastTime);

            lastTime = currentTime; //updates last time so it can go back through the loop

            if (delta > 1) {
                
                // 1 UPDATE: update information about charactor position
                update();

                // 2 DRAW: draw the screen with the updated info
                repaint();

                delta--; //reset delta
                // drawCount++;
            }

            // if (timer >= 1000000000) {
            //     System.out.println(drawCount);
            //     drawCount = 0;
            //     timer = 0;
            // }

        }

    }

    public void update() {

        //top left corner of the screen is 0,0

        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g); //super refers to parent class

        Graphics2D g2 = (Graphics2D)g;
        
        //TILE
        tileM.draw(g2);
        
        //OBJECT
        for (int i = 0; i < obj.length; i++) {
        	if(obj[i] != null) {
        		obj[i].draw(g2, this);
        	}
        }
        
        //PLAYER
        player.draw(g2);

        g2.dispose(); //dispose of graphics context and release system resources that it is useing

    }

}

package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    
    public final int ScreenX;
    public final int ScreenY;

    public Player (GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        
        ScreenX = gp.screenWidth/2 - (gp.tileSize/2);
        ScreenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefultValues();
        getPlayerImage();

    }

    public void setDefultValues() {

        worldX = 15 * gp.tileSize;
        worldY = 10 * gp.tileSize;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage() {

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/av_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/av_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/av_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/av_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/av_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/av_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/av_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/av_right2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update () {
    	
    	if (keyH.upPressed == true || keyH.downPressed == true || 
    			keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed == true) {
                direction = "up";
                
            }
            else if(keyH.downPressed == true) {
                direction = "down";
                
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
                
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
                
            }
            
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {
            	switch(direction) {
            	case "up": worldY -= speed; break;
            	case "down": worldY += speed; break;
            	case "left": worldX -= speed; break;
            	case "right": worldX += speed; break;
            	}
            }
            
            //Basically the sprite changes 60/10 times per second
            SpriteCounter ++;
            
            if (SpriteCounter > 10) {
            	if (SpriteNum == 1) {
            		SpriteNum = 2;
            	}
            	else if (SpriteNum == 2) {
            		SpriteNum = 1;
            	}
            	
            	SpriteCounter = 0;
            	
            }
    	}


    }

    public void draw (Graphics2D g2) {

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction) {
            case "up":
            	if (SpriteNum == 1) {
            		image = up1;            		
            	}
            	if (SpriteNum == 2) {
            		image = up2;
            	}
                break;
            case "down":
            	if (SpriteNum == 1) {
            		image = down1;
            	}
            	if (SpriteNum == 2) {
            		image = down2;
            	}
                break;
            case "left":
            	if (SpriteNum == 1) {
            		image = left1;
            	}
            	if (SpriteNum == 2) {
            		image = left2;
            	}
                break;
            case "right":
            	if (SpriteNum == 1) {
            		image = right1;
            	}
            	if (SpriteNum == 2) {
            		image = right2;
            	}                
                break;
        }

        g2.drawImage(image, ScreenX, ScreenY, gp.tileSize, gp.tileSize, null);
        
    }

}

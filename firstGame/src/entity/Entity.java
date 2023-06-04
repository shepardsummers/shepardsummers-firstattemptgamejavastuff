package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    //this will be parent class for players, monsters, etc and will have common variables

    public int worldX,worldY;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; //stores image files
    public String direction;

    public int SpriteCounter = 0;
    public int SpriteNum = 1;
    
    public Rectangle solidArea;
    public int solidAreaDefultX, solidAreaDefultY;
    public boolean collisionOn = false;
    
}

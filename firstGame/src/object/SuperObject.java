package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.ScreenX; //takes tile and moves it a distance away from 
		//player and accounts for player height and width
		int screenY = worldY - gp.player.worldY + gp.player.ScreenY;
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.ScreenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.ScreenX && 
			worldY + gp.tileSize > gp.player.worldY - gp.player.ScreenY && 
			worldY - gp.tileSize < gp.player.worldY + gp.player.ScreenY) {
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			
		}
		
	}
	
}

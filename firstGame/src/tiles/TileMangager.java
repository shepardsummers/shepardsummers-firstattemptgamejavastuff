package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileMangager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileMangager (GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		GetTileImage();
		loadMap("/maps/map02.txt");
	}
	
	public void GetTileImage () {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_Grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_Stone.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_Water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_Earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_Tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tile_Sand.png"));
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap (String strPass) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(strPass);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]); //changing each character in array into int
					mapTileNum[col][row] = num;
					col++;
					
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row ++;
				}
				
			}
			br.close();
			
		}
		catch (Exception e) {
			
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
//		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
		
		int wCol = 0;
		int wRow = 0;
		
		while (wCol < gp.maxWorldCol && wRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[wCol][wRow];
			
			int wX = wCol * gp.tileSize;
			int wY = wRow * gp.tileSize;
			
			int screenX = wX - gp.player.worldX + gp.player.ScreenX; //takes tile and moves it a distance away from 
			//player and accounts for player height and width
			int screenY = wY - gp.player.worldY + gp.player.ScreenY;
			
			if (wX + gp.tileSize > gp.player.worldX - gp.player.ScreenX &&
				wX - gp.tileSize < gp.player.worldX + gp.player.ScreenX && 
				wY + gp.tileSize > gp.player.worldY - gp.player.ScreenY && 
				wY - gp.tileSize < gp.player.worldY + gp.player.ScreenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				
			}
			
			wCol ++;
			//System.out.println(col);
			
			if (wCol == gp.maxWorldCol) {
				
				wCol = 0;
				wRow ++;
				//System.out.println(row);
				
			}
			
		}
		
		
	}
	
}

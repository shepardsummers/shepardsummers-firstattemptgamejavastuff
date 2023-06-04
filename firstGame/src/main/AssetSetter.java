package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter (GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void setObject () {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 3 * gp.tileSize;
		gp.obj[0].worldY = 10 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 1 * gp.tileSize;
		gp.obj[1].worldY = 16 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 1 * gp.tileSize;
		gp.obj[2].worldY = 12 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 23 * gp.tileSize;
		gp.obj[3].worldY = 16 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 25 * gp.tileSize;
		gp.obj[4].worldY = 17 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 26 * gp.tileSize;
		gp.obj[5].worldY = 15 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 26 * gp.tileSize;
		gp.obj[6].worldY = 13 * gp.tileSize;
		
	}
	
}

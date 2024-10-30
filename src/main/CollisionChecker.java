package main;

import entity.Entity;
import static utilz.Constants.Directions.*;
public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile (Entity entity) {
		
		int entityLeftWorldX = entity.EntityWorldX + entity.solidArea.x;
		int entityRightWorldX = entity.EntityWorldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.EntityWorldY + entity.solidArea.y;
		int entityBottomWorldY = entity.EntityWorldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		
		// khi nvat di chuyển bất cứ hướng nào
		// luôn có 2 thông số kiểm tra va chạm
		// VD: đi lên có vai trái vai phải
		// đi xuống có chân trái chân phải
		int tileNum1, tileNum2; 
		
		switch (entity.direction) {
		case UP:
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
			break;
		case DOWN:
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
            break;
        case LEFT:
        	entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
            break;
        case RIGHT:
        	entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionON = true;
			}
            break;
		default:
			break;
		}
	}
}

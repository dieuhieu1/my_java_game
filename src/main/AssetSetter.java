package main;

import object.obj_duck;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {
        gp.obj[0] = new obj_duck();
        gp.obj[0].worldX = 30 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

        gp.obj[1] = new obj_duck();
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 38 * gp.tileSize;

        gp.obj[2] = new obj_duck();
        gp.obj[2].worldX = 9 * gp.tileSize;
        gp.obj[2].worldY = 40 * gp.tileSize;

        gp.obj[3] = new obj_duck();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 7 * gp.tileSize;

    }
}

package ma.enset.jeu2d_stage1;

import ma.enset.jeu2d_stage1.object.*;


public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        this.gp.obj[0] = new OBJ_key(gp);
        SuperObject var10000 = this.gp.obj[0];
        this.gp.getClass();
        var10000.worldX = 23 * 48;
        var10000 = this.gp.obj[0];
        this.gp.getClass();
        var10000.worldY = 7 * 48;
        this.gp.obj[1] = new OBJ_key(gp);
        var10000 = this.gp.obj[1];
        this.gp.getClass();
        var10000.worldX = 23 * 48;
        var10000 = this.gp.obj[1];
        this.gp.getClass();
        var10000.worldY = 40 * 48;
        this.gp.obj[2] = new OBJ_key(gp);
        var10000 = this.gp.obj[2];
        this.gp.getClass();
        var10000.worldX = 38 * 48;
        var10000 = this.gp.obj[2];
        this.gp.getClass();
        var10000.worldY = 8 * 48;
        this.gp.obj[3] = new OBJ_Door(gp);
        var10000 = this.gp.obj[3];
        this.gp.getClass();
        var10000.worldX = 10 * 48;
        var10000 = this.gp.obj[3];
        this.gp.getClass();
        var10000.worldY = 11 * 48;
        this.gp.obj[4] = new OBJ_Door(gp);
        var10000 = this.gp.obj[4];
        this.gp.getClass();
        var10000.worldX = 8 * 48;
        var10000 = this.gp.obj[4];
        this.gp.getClass();
        var10000.worldY = 28 * 48;
        this.gp.obj[5] = new OBJ_Door(gp);
        var10000 = this.gp.obj[5];
        this.gp.getClass();
        var10000.worldX = 12 * 48;
        var10000 = this.gp.obj[5];
        this.gp.getClass();
        var10000.worldY = 22 * 48;
        this.gp.obj[6] = new OBJ_Chest(gp);
        var10000 = this.gp.obj[6];
        this.gp.getClass();
        var10000.worldX = 10 * 48;
        var10000 = this.gp.obj[6];
        this.gp.getClass();
        var10000.worldY = 7 * 48;
        this.gp.obj[7] = new OBJ_Boots(gp);
        var10000 = this.gp.obj[7];
        this.gp.getClass();
        var10000.worldX = 37 * 48;
        var10000 = this.gp.obj[7];
        this.gp.getClass();
        var10000.worldY = 42 * 48;
    }
}

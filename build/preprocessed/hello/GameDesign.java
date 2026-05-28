/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.io.IOException;

/**
 * @author lenov
 */
public class GameDesign {

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Image platform_tiles;
    private Image tileset;
    private TiledLayer nivel_1;
    private Image bobby_right;
    private Sprite bobby_derecha;
    public int bobby_derechaseq001Delay = 40;
    public int[] bobby_derechaseq001 = {3, 4, 5, 6, 7, 0, 1, 2};
    private Sprite bobby_izquierda;
    public int bobby_izquierdaseq001Delay = 40;
    public int[] bobby_izquierdaseq001 = {3, 4, 5, 6, 7, 0, 1, 2};
    private Image bobby_left;
    private Image bobby_down;
    private Image bobby_up;
    private Sprite bobby_arriba;
    public int bobby_arribaseq001Delay = 40;
    public int[] bobby_arribaseq001 = {3, 4, 5, 6, 7, 0, 1, 2};
    private Sprite bobby_abajo;
    public int bobby_abajoseq001Delay = 40;
    public int[] bobby_abajoseq001 = {3, 4, 5, 6, 7, 0, 1, 2};
    private Sprite bobby_aparecer;
    public int bobby_aparecerseq001Delay = 100;
    public int[] bobby_aparecerseq001 = {8, 7, 6, 5, 4, 3, 2, 1, 0};
    private Image bobby_fade;
    private Sprite bobby_desaparec;
    public int bobby_desaparecseq001Delay = 100;
    public int[] bobby_desaparecseq001 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private Image tile_finish;
    private Sprite bobby_meta;
    public int bobby_metaseq001Delay = 30;
    public int[] bobby_metaseq001 = {0, 1, 2, 3};
    private TiledLayer nivel_2;
    private Image hud;
    //</editor-fold>//GEN-END:|fields|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    public Image getPlatform_tiles() throws java.io.IOException {//GEN-BEGIN:|1-getter|0|1-preInit
        if (platform_tiles == null) {//GEN-END:|1-getter|0|1-preInit
            // write pre-init user code here
            platform_tiles = Image.createImage("/platform_tiles.png");//GEN-BEGIN:|1-getter|1|1-postInit
        }//GEN-END:|1-getter|1|1-postInit
        // write post-init user code here
        return this.platform_tiles;//GEN-BEGIN:|1-getter|2|
    }
//GEN-END:|1-getter|2|







    public Image getTileset() throws java.io.IOException {//GEN-BEGIN:|19-getter|0|19-preInit
        if (tileset == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
            tileset = Image.createImage("/tileset.png");//GEN-BEGIN:|19-getter|1|19-postInit
        }//GEN-END:|19-getter|1|19-postInit
        // write post-init user code here
        return this.tileset;//GEN-BEGIN:|19-getter|2|
    }
//GEN-END:|19-getter|2|



    public TiledLayer getNivel_1() throws java.io.IOException {//GEN-BEGIN:|22-getter|0|22-preInit
        if (nivel_1 == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            nivel_1 = new TiledLayer(20, 20, getTileset(), 16, 16);//GEN-BEGIN:|22-getter|1|22-midInit
            int[][] tiles = {
                { 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 14, 19, 19, 19, 19, 19, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 14, 19, 19, 19, 19, 22, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 10, 10, 19, 19, 19, 19, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 10, 10, 19, 20, 20, 20, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 45, 19, 19, 19, 19, 10, 10, 19, 20, 20, 20, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 10, 10, 19, 20, 20, 20, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 10, 10, 19, 19, 19, 19, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 34, 19, 19, 19, 19, 33, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 19, 19, 19, 19, 19, 14, 19, 19, 19, 19, 19, 19, 3, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
            };//GEN-END:|22-getter|1|22-midInit
            // write mid-init user code here
            for (int row = 0; row < 20; row++) {//GEN-BEGIN:|22-getter|2|22-postInit
                for (int col = 0; col < 20; col++) {
                    nivel_1.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|22-getter|2|22-postInit
        // write post-init user code here
        return nivel_1;//GEN-BEGIN:|22-getter|3|
    }
//GEN-END:|22-getter|3|



    public Image getBobby_right() throws java.io.IOException {//GEN-BEGIN:|25-getter|0|25-preInit
        if (bobby_right == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            bobby_right = Image.createImage("/bobby_right.png");//GEN-BEGIN:|25-getter|1|25-postInit
        }//GEN-END:|25-getter|1|25-postInit
        // write post-init user code here
        return this.bobby_right;//GEN-BEGIN:|25-getter|2|
    }
//GEN-END:|25-getter|2|

    public Sprite getBobby_derecha() throws java.io.IOException {//GEN-BEGIN:|26-getter|0|26-preInit
        if (bobby_derecha == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            bobby_derecha = new Sprite(getBobby_right(), 18, 25);//GEN-BEGIN:|26-getter|1|26-postInit
            bobby_derecha.setFrameSequence(bobby_derechaseq001);//GEN-END:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return bobby_derecha;
    }
//GEN-END:|26-getter|2|

    public Image getBobby_left() throws java.io.IOException {//GEN-BEGIN:|28-getter|0|28-preInit
        if (bobby_left == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            bobby_left = Image.createImage("/bobby_left.png");//GEN-BEGIN:|28-getter|1|28-postInit
        }//GEN-END:|28-getter|1|28-postInit
        // write post-init user code here
        return this.bobby_left;//GEN-BEGIN:|28-getter|2|
    }
//GEN-END:|28-getter|2|

    public Sprite getBobby_izquierda() throws java.io.IOException {//GEN-BEGIN:|29-getter|0|29-preInit
        if (bobby_izquierda == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            bobby_izquierda = new Sprite(getBobby_left(), 18, 25);//GEN-BEGIN:|29-getter|1|29-postInit
            bobby_izquierda.setFrameSequence(bobby_izquierdaseq001);//GEN-END:|29-getter|1|29-postInit
            // write post-init user code here
        }//GEN-BEGIN:|29-getter|2|
        return bobby_izquierda;
    }
//GEN-END:|29-getter|2|

    public Image getBobby_down() throws java.io.IOException {//GEN-BEGIN:|31-getter|0|31-preInit
        if (bobby_down == null) {//GEN-END:|31-getter|0|31-preInit
            // write pre-init user code here
            bobby_down = Image.createImage("/bobby_down.png");//GEN-BEGIN:|31-getter|1|31-postInit
        }//GEN-END:|31-getter|1|31-postInit
        // write post-init user code here
        return this.bobby_down;//GEN-BEGIN:|31-getter|2|
    }
//GEN-END:|31-getter|2|

    public Sprite getBobby_abajo() throws java.io.IOException {//GEN-BEGIN:|32-getter|0|32-preInit
        if (bobby_abajo == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            bobby_abajo = new Sprite(getBobby_down(), 18, 25);//GEN-BEGIN:|32-getter|1|32-postInit
            bobby_abajo.setFrameSequence(bobby_abajoseq001);//GEN-END:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return bobby_abajo;
    }
//GEN-END:|32-getter|2|

    public Image getBobby_up() throws java.io.IOException {//GEN-BEGIN:|34-getter|0|34-preInit
        if (bobby_up == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            bobby_up = Image.createImage("/bobby_up.png");//GEN-BEGIN:|34-getter|1|34-postInit
        }//GEN-END:|34-getter|1|34-postInit
        // write post-init user code here
        return this.bobby_up;//GEN-BEGIN:|34-getter|2|
    }
//GEN-END:|34-getter|2|

    public Sprite getBobby_arriba() throws java.io.IOException {//GEN-BEGIN:|35-getter|0|35-preInit
        if (bobby_arriba == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            bobby_arriba = new Sprite(getBobby_up(), 18, 25);//GEN-BEGIN:|35-getter|1|35-postInit
            bobby_arriba.setFrameSequence(bobby_arribaseq001);//GEN-END:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return bobby_arriba;
    }
//GEN-END:|35-getter|2|

    public Image getBobby_fade() throws java.io.IOException {//GEN-BEGIN:|37-getter|0|37-preInit
        if (bobby_fade == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            bobby_fade = Image.createImage("/bobby_fade.png");//GEN-BEGIN:|37-getter|1|37-postInit
        }//GEN-END:|37-getter|1|37-postInit
        // write post-init user code here
        return this.bobby_fade;//GEN-BEGIN:|37-getter|2|
    }
//GEN-END:|37-getter|2|

    public Sprite getBobby_aparecer() throws java.io.IOException {//GEN-BEGIN:|38-getter|0|38-preInit
        if (bobby_aparecer == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            bobby_aparecer = new Sprite(getBobby_fade(), 18, 25);//GEN-BEGIN:|38-getter|1|38-postInit
            bobby_aparecer.setFrameSequence(bobby_aparecerseq001);//GEN-END:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return bobby_aparecer;
    }
//GEN-END:|38-getter|2|

    public Image getTile_finish() throws java.io.IOException {//GEN-BEGIN:|40-getter|0|40-preInit
        if (tile_finish == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            tile_finish = Image.createImage("/tile_finish.png");//GEN-BEGIN:|40-getter|1|40-postInit
        }//GEN-END:|40-getter|1|40-postInit
        // write post-init user code here
        return this.tile_finish;//GEN-BEGIN:|40-getter|2|
    }
//GEN-END:|40-getter|2|

    public Sprite getBobby_meta() throws java.io.IOException {//GEN-BEGIN:|41-getter|0|41-preInit
        if (bobby_meta == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            bobby_meta = new Sprite(getTile_finish(), 16, 16);//GEN-BEGIN:|41-getter|1|41-postInit
            bobby_meta.setFrameSequence(bobby_metaseq001);//GEN-END:|41-getter|1|41-postInit
            // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return bobby_meta;
    }
//GEN-END:|41-getter|2|

    public Sprite getBobby_desaparec() throws java.io.IOException {//GEN-BEGIN:|43-getter|0|43-preInit
        if (bobby_desaparec == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            bobby_desaparec = new Sprite(getBobby_fade(), 18, 25);//GEN-BEGIN:|43-getter|1|43-postInit
            bobby_desaparec.setFrameSequence(bobby_desaparecseq001);//GEN-END:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return bobby_desaparec;
    }
//GEN-END:|43-getter|2|

    public TiledLayer getNivel_2() throws java.io.IOException {//GEN-BEGIN:|45-getter|0|45-preInit
        if (nivel_2 == null) {//GEN-END:|45-getter|0|45-preInit
            // write pre-init user code here
            nivel_2 = new TiledLayer(22, 20, getTileset(), 16, 16);//GEN-BEGIN:|45-getter|1|45-midInit
            int[][] tiles = {
                { 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1 },
                { 1, 2, 19, 19, 19, 19, 19, 19, 19, 19, 19, 14, 19, 19, 19, 19, 35, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 11, 11, 11, 19, 19, 19, 14, 19, 19, 19, 19, 19, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 12, 12, 12, 19, 19, 19, 14, 19, 19, 19, 19, 19, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 19, 19, 19, 19, 19, 19, 14, 19, 19, 19, 19, 19, 19, 19, 19, 22, 1 },
                { 1, 2, 19, 19, 19, 20, 20, 20, 20, 19, 19, 14, 20, 20, 20, 20, 19, 19, 19, 19, 19, 1 },
                { 1, 2, 19, 19, 19, 20, 20, 20, 20, 19, 19, 14, 20, 20, 20, 20, 19, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 20, 20, 20, 20, 19, 19, 14, 20, 20, 20, 20, 19, 19, 19, 19, 3, 1 },
                { 1, 2, 15, 13, 13, 13, 13, 13, 13, 13, 34, 14, 19, 19, 19, 19, 19, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 19, 19, 19, 19, 19, 20, 14, 19, 19, 19, 19, 19, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 19, 19, 19, 19, 19, 19, 15, 16, 36, 11, 11, 19, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 19, 19, 20, 19, 19, 19, 19, 20, 19, 12, 12, 14, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 20, 19, 20, 20, 19, 19, 19, 19, 20, 20, 19, 19, 14, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 20, 20, 20, 19, 19, 19, 19, 19, 19, 20, 20, 20, 14, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 19, 19, 14, 19, 19, 19, 3, 1 },
                { 1, 45, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 14, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 14, 19, 19, 19, 3, 1 },
                { 1, 2, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 14, 19, 19, 19, 3, 1 },
                { 1, 1, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 14, 19, 19, 33, 3, 1 },
                { 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1 }
            };//GEN-END:|45-getter|1|45-midInit
            // write mid-init user code here
            for (int row = 0; row < 20; row++) {//GEN-BEGIN:|45-getter|2|45-postInit
                for (int col = 0; col < 22; col++) {
                    nivel_2.setCell(col, row, tiles[row][col]);
                }
            }
        }//GEN-END:|45-getter|2|45-postInit
        // write post-init user code here
        return nivel_2;//GEN-BEGIN:|45-getter|3|
    }
//GEN-END:|45-getter|3|



    public Image getHud() throws java.io.IOException {//GEN-BEGIN:|49-getter|0|49-preInit
        if (hud == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            hud = Image.createImage("/hud.png");//GEN-BEGIN:|49-getter|1|49-postInit
        }//GEN-END:|49-getter|1|49-postInit
        // write post-init user code here
        return this.hud;//GEN-BEGIN:|49-getter|2|
    }
//GEN-END:|49-getter|2|

}

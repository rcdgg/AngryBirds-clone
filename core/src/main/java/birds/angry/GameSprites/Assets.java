package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Assets {
    public static TextureRegion[] redbirds;
    public static Texture bluebird, yellowbird, bluebird2, yellowbird2, redbird2, redbird1;
    public static Texture level1bg, level2bg, level3bg, loadgamebg, bpbg;
    public static Texture ppig;
    public static Texture kingpig;
    public static Texture soldierpig;
    public static Texture slingshot;
    public static Texture woodlog;
    public static Texture icelog;
    public static Texture stonelog;
    public static Image pausebg;
    public static Image winbg, losebg;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }
    public static void load(){
        Texture redBird_spritesheet = new Texture("birds/Redbird.png");
        int frameWidth = redBird_spritesheet.getWidth() / 4;
        int frameHeight = redBird_spritesheet.getHeight();
        TextureRegion[][] tmp = TextureRegion.split(redBird_spritesheet, frameWidth, frameHeight);
        redbirds = tmp[0];
        redbird1 = loadTexture("birds/redbird1.png");

        level1bg = loadTexture("screens/levels/level11bg.png");
        level2bg = loadTexture("screens/levels/level2bg.png");
        level3bg = loadTexture("screens/levels/level3bg.png");
        loadgamebg = loadTexture("screens/loadgame.png");
        bluebird = loadTexture("birds/Bluebird.png");
        yellowbird = loadTexture("birds/Yellowbird.png");
        bluebird2 = loadTexture("birds/bluebird2.png");
        yellowbird2 = loadTexture("birds/yellowbird2.png");
        redbird2 = loadTexture("birds/redbird2.png");
        ppig = loadTexture("pigs/PeasantPig.png");
        kingpig = loadTexture("pigs/KingPig.png");
        soldierpig = loadTexture("pigs/SoldierPig.png");
        slingshot = loadTexture("screens/levels/slingshot.png");
        woodlog = loadTexture("mats/woodlog.png");
        icelog = loadTexture("mats/icelog.png");
        stonelog = loadTexture("mats/stonelog.png");
        pausebg = new Image(loadTexture("screens/pausemenu.png"));
        winbg = new Image(loadTexture("screens/WinScreen.png"));
        losebg = new Image(loadTexture("screens/LoseScreen.png"));
        bpbg = loadTexture("screens/battlepassbg.png");
    }
    // make the appropriate dispose function
}

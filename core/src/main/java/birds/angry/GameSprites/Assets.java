package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Assets {
    public static TextureRegion[] redbirds;
    public static Texture level1bg;
    public static Texture ppig;
    public static Texture kingpig;
    public static Texture soldierpig;
    public static Texture slingshot;
    public static Texture woodlog;
    public static Texture icelog;
    public static Texture stonelog;
    public static Image pausebg;
    public static Image winbg;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }
    public static void load(){
        Texture redBird_spritesheet = new Texture("red bird spritesheet.png");
        int frameWidth = redBird_spritesheet.getWidth() / 4;
        int frameHeight = redBird_spritesheet.getHeight();
        TextureRegion[][] tmp = TextureRegion.split(redBird_spritesheet, frameWidth, frameHeight);
        redbirds = tmp[0];

        level1bg = loadTexture("screens/mainmenu/angy bird bg.jpg");

        ppig = loadTexture("screens/levels/ppig.png");
        kingpig = loadTexture("screens/levels/kingpig.png");
        soldierpig = loadTexture("screens/levels/soldierpig.png");
        slingshot = loadTexture("screens/levels/slingshot.png");
        woodlog = loadTexture("screens/levels/woodlog.png");
        icelog = loadTexture("screens/levels/icelog.png");
        stonelog = loadTexture("screens/levels/stonelog.png");
        pausebg = new Image(loadTexture("screens/pausebg.png"));
        winbg = new Image(loadTexture("screens/3star.png"));
    }
    // make the appropriate dispose function
}

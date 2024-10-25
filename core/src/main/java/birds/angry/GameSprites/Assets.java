package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureRegion[] redbirds;
    static Texture redbird1;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }
    public static void load(){
        Texture redBird_spritesheet = new Texture("red bird spritesheet.png");
        int frameWidth = redBird_spritesheet.getWidth() / 4;
        int frameHeight = redBird_spritesheet.getHeight();
        TextureRegion[][] tmp = TextureRegion.split(redBird_spritesheet, frameWidth, frameHeight);

        redbirds = tmp[0];
    }
}

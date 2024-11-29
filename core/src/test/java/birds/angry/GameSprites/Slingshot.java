package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Slingshot extends GameObject{
    private Texture slingtexture;
    public Slingshot(Vector2 pos){
        super(Assets.slingshot, pos, new Vector2(100, (float) (Assets.slingshot.getHeight() * 100) / Assets.slingshot.getWidth()));
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }

}

package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Slingshot extends GameObject{
    public Slingshot(Texture slingtexture, Vector2 pos, Vector2 size){
        super(slingtexture, pos, size);
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }

}

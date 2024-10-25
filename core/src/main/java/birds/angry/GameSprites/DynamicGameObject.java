package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject{
    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicGameObject(Texture texture, Vector2 position, Vector2 size){
        super(texture, position, size);
        velocity  = new Vector2();
        accel = new Vector2();
    }
}

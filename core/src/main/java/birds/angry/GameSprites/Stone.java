package birds.angry.GameSprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Stone extends Material{
    public Stone(Vector2 position, Body body) {
        super(Assets.stonelog, position, new Vector2( (float)Assets.stonelog.getWidth(), (float)Assets.stonelog.getHeight()-50), body);
    }
}

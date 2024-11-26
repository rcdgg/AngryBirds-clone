package birds.angry.GameSprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Ice extends Material{
    public Ice(Vector2 position, Body body) {
        super(Assets.icelog, position, new Vector2( (float)Assets.icelog.getWidth(), (float)Assets.icelog.getHeight()-50), body);
    }
}

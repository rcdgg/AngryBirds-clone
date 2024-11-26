package birds.angry.GameSprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Ice extends Material{
    public Ice(Vector2 position, World world) {
        super(Assets.icelog, position, new Vector2( (float)Assets.icelog.getWidth(), (float)Assets.icelog.getHeight()-50), world);
    }
}

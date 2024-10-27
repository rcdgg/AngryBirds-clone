package birds.angry.GameSprites;

import com.badlogic.gdx.math.Vector2;

public class Stone extends Material{
    public Stone(Vector2 position) {
        super(Assets.stonelog, position, new Vector2( (float)Assets.stonelog.getWidth(), (float)Assets.stonelog.getHeight()-50));
    }
}

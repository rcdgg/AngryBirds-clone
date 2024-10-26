package birds.angry.GameSprites;

import com.badlogic.gdx.math.Vector2;

public class Ice extends DynamicGameObject{
    public Ice(Vector2 position) {
        super(Assets.icelog, position, new Vector2( (float)Assets.icelog.getWidth(), (float)Assets.icelog.getHeight()-50));
    }
}

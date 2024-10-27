package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Wood extends Material{
    public Wood(Vector2 position) {
        super(Assets.woodlog, position, new Vector2( (float)Assets.woodlog.getWidth(), (float)Assets.woodlog.getHeight()-50));
    }

}

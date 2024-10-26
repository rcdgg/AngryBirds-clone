package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class SoldierPig extends Pig{
    public SoldierPig(Vector2 position) {
        super(Assets.soldierpig, position, new Vector2((float)Assets.soldierpig.getWidth()-250, (float)Assets.soldierpig.getHeight()-250));
        System.out.println("Spig height = "+Assets.soldierpig.getHeight());
        System.out.println("Spig width = "+Assets.soldierpig.getWidth());
    }
}

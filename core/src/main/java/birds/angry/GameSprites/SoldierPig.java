package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class SoldierPig extends Pig{
    public SoldierPig(Vector2 position, Body body) {
        super(Assets.soldierpig, position, new Vector2((float)Assets.soldierpig.getWidth()-250, (float)Assets.soldierpig.getHeight()-250), body);
        System.out.println("Spig height = "+Assets.soldierpig.getHeight());
        System.out.println("Spig width = "+Assets.soldierpig.getWidth());
    }
}

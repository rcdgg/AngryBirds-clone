package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class KingPig extends Pig{

    public KingPig(Vector2 position, Body body) {
        super(Assets.kingpig, position, new Vector2((float)Assets.kingpig.getWidth()-250, (float)Assets.kingpig.getHeight()-250), body);
        System.out.println("Kpig height = "+Assets.ppig.getHeight());
        System.out.println("Kpig width = "+Assets.ppig.getWidth());
    }

}


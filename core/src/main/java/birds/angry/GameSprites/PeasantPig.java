package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class PeasantPig extends Pig{
    public PeasantPig(Vector2 position) {
        super(Assets.ppig, position, new Vector2 ((float)(Assets.ppig.getHeight() - 250), (float)(Assets.ppig.getWidth()- 250)));
        System.out.println("ppig height = "+Assets.ppig.getHeight());
        System.out.println("ppig width = "+Assets.ppig.getWidth());
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }
}

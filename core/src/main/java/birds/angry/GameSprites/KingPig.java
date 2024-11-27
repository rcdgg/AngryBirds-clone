package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class KingPig extends Pig{
    private final Texture pigtex;
    public KingPig(Vector2 position, World world) {
        super(Assets.kingpig, position, new Vector2((float)Assets.kingpig.getWidth()-250, (float)Assets.kingpig.getHeight()-250), world);
        pigtex = Assets.kingpig;
        health = 4;
        this.setSize((float)Assets.kingpig.getWidth()/4, (float)Assets.kingpig.getHeight()/4);
//        System.out.println("Kpig height = "+Assets.ppig.getHeight());
//        System.out.println("Kpig width = "+Assets.ppig.getWidth());

    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(pigtex, getX(), getY(), getWidth(), getHeight());
//    }
    @Override
    public String getName(){
        return "KingPig";
    }
}


package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class PeasantPig extends Pig{
    private final Texture ppigtex;
    public PeasantPig(Vector2 position, World world) {
        super(Assets.ppig, position, new Vector2 ((float)(Assets.ppig.getHeight() - 250), (float)(Assets.ppig.getWidth()- 250)),world);
        health = 5;
        ppigtex = Assets.ppig;
        this.setSize((float)Assets.ppig.getHeight()/4, (float)Assets.ppig.getWidth()/4);
        //        System.out.println("ppig height = "+Assets.ppig.getHeight());
//        System.out.println("ppig width = "+Assets.ppig.getWidth());
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(ppigtex, getX(), getY(), getWidth(), getHeight());
//    }
    @Override
    public String getName(){
        return "PeasantPig";
    }
}

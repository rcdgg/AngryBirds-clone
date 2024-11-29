package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class SoldierPig extends Pig{
    public SoldierPig(Vector2 position, World world) {
        super(Assets.soldierpig, position, new Vector2((float)Assets.soldierpig.getWidth()-250, (float)Assets.soldierpig.getHeight()-250), world);
        health = 4;
        this.setSize((float)Assets.soldierpig.getWidth()/4, (float)Assets.soldierpig.getHeight()/4);
        //        System.out.println("Spig height = "+Assets.soldierpig.getHeight());
//        System.out.println("Spig width = "+Assets.soldierpig.getWidth());
    }
    @Override
    public String getName(){
        return "SoldierPig";
    }
}

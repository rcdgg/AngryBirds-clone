package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class Yellowbird extends Bird{
    public final Texture yellowbirdtex;
    public Yellowbird(Vector2 position, World world){
        super(Assets.yellowbird, position, new Vector2 ((float)(Assets.yellowbird.getHeight() - 100), (float)(Assets.yellowbird.getWidth()- 100)), world);
        yellowbirdtex = texture;
        this.setSize((float)Assets.redbirds[0].getRegionWidth()/4, (float)Assets.redbirds[0].getRegionHeight()/4);
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }
    @Override
    public String getName(){
        return "Yellowbird";
    }


    @Override
    public ArrayList<Bird> ability(){
        System.out.println(getName() + " ability");
        if(!ability_used) {
            for(Fixture f: body.getFixtureList()){
                f.setDensity(f.getDensity() + 10);
            }
            body.resetMassData();
            Vector2 imp = body.getLinearVelocity();
            imp.x *= 5f;
            imp.y *= 2.5f;
            body.applyLinearImpulse(imp, body.getWorldCenter(), true);
            ability_used = true;
        }
        return null;
    }
}

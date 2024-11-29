package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class Redbird extends Bird{
//    private final TextureRegion redbirdtexreg;
    public Texture tex;
    public Redbird(Vector2 position, World world){
        super(Assets.redbird1, position, new Vector2 ((float)(Assets.redbird1.getHeight() - 100), (float)(Assets.redbird1.getWidth()- 100)), world);
//        redbirdtexreg = Assets.redbirds[0];
//        this.setSize((float)Assets.redbird1.getRegionWidth()/4, (float)Assets.redbird1.getRegionHeight()/4);
    }
    public void nextTex(){
        if(texture == Assets.redbird1){
            tex = (Texture) Assets.redbirds2;
            this.texture = tex;
        }
        else{
            tex = (Texture) Assets.redbird1;
            this.texture = tex;
        }
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(redbirdtexreg, getX(), getY(), getWidth(), getHeight());
//    }
    @Override
    public String getName(){
        return "Redbird";
    }


    @Override
    public ArrayList<Bird> ability(){
        System.out.println(getName() + " ability");
        if(!ability_used) {
            ability_used = true;
            body.setLinearVelocity(0, body.getLinearVelocity().y);
            body.applyLinearImpulse(new Vector2(0, -2), body.getWorldCenter(), true);
        }
        return null;
    }
}

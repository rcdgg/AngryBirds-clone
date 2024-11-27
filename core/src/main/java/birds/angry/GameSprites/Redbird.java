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

public class Redbird extends Bird{
    private final TextureRegion redbirdtexreg;
    public Redbird(Vector2 position, World world){
        super((Texture) Assets.redbirds[0].getTexture(), position, new Vector2 ((float)(Assets.redbirds[0].getTexture().getHeight() - 100), (float)(Assets.redbirds[0].getTexture().getWidth()- 100)), world);
        redbirdtexreg = Assets.redbirds[0];
        this.setSize((float)Assets.redbirds[0].getRegionWidth()/4, (float)Assets.redbirds[0].getRegionHeight()/4);
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(redbirdtexreg, getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public String getName(){
        return "Redbird";
    }
    @Override
    public void ability(){
        System.out.println(getName() + " ability");
        if(!ability_used) {
            ability_used = true;
            body.setLinearVelocity(0, body.getLinearVelocity().y);
            body.applyLinearImpulse(new Vector2(0, -2), body.getWorldCenter(), true);
        }
    }
}

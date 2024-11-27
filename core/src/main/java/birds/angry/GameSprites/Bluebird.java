package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Bluebird extends Bird{
    private final Texture bluebirdtex;
    Stage temp;
    public Bluebird(Vector2 position, World world){
        super(Assets.bluebird, position, new Vector2 ((float)(Assets.bluebird.getHeight() - 100), (float)(Assets.bluebird.getWidth()- 100)), world);
        bluebirdtex = texture;
        this.setSize((float)Assets.redbirds[0].getRegionWidth()/4, (float)Assets.redbirds[0].getRegionHeight()/4);
    }
    @Override
    public void act(float delta){
        super.act(delta);
        if(temp!=null) temp.act(delta);
        // slingshot specific logic here like band and projectile
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
        if(temp != null){
            temp.draw();
        }
    }
    @Override
    public String getName(){
        return "Bluebird";
    }

    @Override
    public void ability(){
        System.out.println(getName() + " ability");
        if(!ability_used) {
            Stage temp = new Stage(new FitViewport(16,9));
            Bluebird one = new Bluebird(new Vector2(body.getPosition().x, body.getPosition().y + 1), world);
            Bluebird two = new Bluebird(new Vector2(body.getPosition().x, body.getPosition().y - 1), world);
            one.ability_used = true;
            two.ability_used = true;
            one.body.setLinearVelocity(body.getLinearVelocity());
            two.body.setLinearVelocity(body.getLinearVelocity());
            temp.addActor(one);
            temp.addActor(two);
            ability_used = true;
        }
    }
}

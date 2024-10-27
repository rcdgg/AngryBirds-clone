package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bluebird extends Bird{
    private final Texture bluebirdtex;
    public Bluebird(Vector2 position){
        super(Assets.bluebird, position, new Vector2 ((float)(Assets.bluebird.getHeight() - 100), (float)(Assets.bluebird.getWidth()- 100)));
        bluebirdtex = texture;
        this.setSize((float)Assets.redbirds[0].getRegionWidth()/4, (float)Assets.redbirds[0].getRegionHeight()/4);
    }
    @Override
    public void act(float delta){
        super.act(delta);
        // slingshot specific logic here like band and projectile
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bluebirdtex, getX(), getY(), getWidth(), getHeight());
    }
}

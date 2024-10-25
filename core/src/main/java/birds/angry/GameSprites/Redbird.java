package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Redbird extends Bird{
    private final TextureRegion redbirdtexreg;
    public Redbird(Vector2 position){
        super(Assets.redbirds[0].getTexture(), position, new Vector2 ((float)(Assets.redbirds[0].getTexture().getHeight() - 100), (float)(Assets.redbirds[0].getTexture().getWidth()- 100)));
        redbirdtexreg = Assets.redbirds[0];
        this.setSize((float)Assets.redbirds[0].getRegionWidth()/4, (float)Assets.redbirds[0].getRegionHeight()/4);
        this.setPosition(90, 90);
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
}

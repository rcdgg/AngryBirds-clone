package birds.angry.GameSprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.io.Serializable;

public class GameObject extends Actor{
    public Texture texture;
    protected TextureRegion textreg;
    public Vector2 position;
    protected Vector2 size;
    public boolean isDead = false;
    public GameObject(Texture texture, Vector2 position, Vector2 size) {
        this.texture = texture;
//        textreg = new TextureRegion(texture);
        textreg = null;
        this.position = position;
        setPosition(position.x, position.y);
        setSize(size.x, size.y);
    }

//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        if(isDead){
////            System.out.println(this+"dead so not drawing");
//            return;
//        }
//        textreg = new TextureRegion(texture);
////        textreg = new TextureRegion(texture);
//        batch.draw(
//            textreg,              // Use TextureRegion
//            getX(), getY(),             // Position
//            getWidth() / 2, getHeight() / 2, // Origin for rotation
//            getWidth(), getHeight(),    // Size
//            1, 1,                       // Scale
//            getRotation()               // Rotation in degrees
//        );
//    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, size.x, size.y);
    }

    public void dispose() {
        texture.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

}

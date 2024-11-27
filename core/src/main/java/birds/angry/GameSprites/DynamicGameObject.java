package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class DynamicGameObject extends GameObject{
    public final Vector2 velocity;
    public final Vector2 accel;
    public Body body;
    public float health;
    public DynamicGameObject(Texture texture, Vector2 position, Vector2 size, Body body){
        super(texture, position, size);
        velocity  = new Vector2();
        accel = new Vector2();
        this.body = body;
    }
    public DynamicGameObject(Texture texture, Vector2 position, Vector2 size){
        super(texture, position, size);
        velocity  = new Vector2();
        accel = new Vector2();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        setX(body.getPosition().x - this.getWidth() / 2);
        setY(body.getPosition().y - this.getHeight() / 2);
        setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }
}

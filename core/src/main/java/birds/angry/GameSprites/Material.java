package birds.angry.GameSprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Material extends DynamicGameObject {

    private float strength;
    public Material(Texture texture, Vector2 position, Vector2 size, Body body) {
        super(texture, position, size, body);
    }
}

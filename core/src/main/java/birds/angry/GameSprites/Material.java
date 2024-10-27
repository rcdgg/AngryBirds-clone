package birds.angry.GameSprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Material extends DynamicGameObject {

    private float strength;
    public Material(Texture texture, Vector2 position, Vector2 size) {
        super(texture, position, size);
    }
}

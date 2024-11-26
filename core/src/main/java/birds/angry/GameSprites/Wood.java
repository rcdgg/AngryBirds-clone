package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Wood extends Material{
    public Wood(Vector2 position, World world) {
        super(Assets.woodlog, position, new Vector2( (float)Assets.woodlog.getWidth(), (float)Assets.woodlog.getHeight()-50), world);
    }
    @Override
    public String getName(){
        return "woodlog";
    }

}

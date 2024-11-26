package birds.angry.GameSprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Stone extends Material{
    public Stone(Vector2 position, World body) {
        super(Assets.stonelog, position, new Vector2( (float)Assets.stonelog.getWidth(), (float)Assets.stonelog.getHeight()-50), body);
    }
    @Override
    public String getName(){
        return "stonelog";
    }
}


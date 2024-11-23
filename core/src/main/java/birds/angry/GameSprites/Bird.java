package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Bird extends DynamicGameObject{

    Bird(Texture texture, Vector2 position, Vector2 size){
        super(texture, position, size);
    }

    void Ability(){
    }


}

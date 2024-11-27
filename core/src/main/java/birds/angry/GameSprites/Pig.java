package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Pig extends DynamicGameObject{
    float pig_size;
    World world;
    public static final short PIG = 2;

    public Pig(Texture texture, Vector2 position, Vector2 size, World world) {
        super(texture, position, size);
        health = 2;
        score = 200;
        pig_size = 0.3f;
        this.world = world;
        createPig(position);
    }
    public void createPig(Vector2 pos){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(pos);
        Body bbody = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(pig_size);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = circle;
        fdef.filter.categoryBits = PIG;
        fdef.filter.maskBits = -1;
        fdef.density = 1;
        fdef.restitution = 0.5f;
        bbody.createFixture(fdef);
        body = bbody;
    }
    @Override
    public String getName(){
        return "";
    }

}
;

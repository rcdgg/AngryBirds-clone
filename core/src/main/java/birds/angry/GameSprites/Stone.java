package birds.angry.GameSprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Stone extends Material{
    public Stone(Vector2 position, World body) {
        super(Assets.stonelog, position, new Vector2( (float)Assets.stonelog.getWidth(), (float)Assets.stonelog.getHeight()-50), body);
    }
    @Override
    public String getName(){
        return "stonelog";
    }
    @Override
    public void createObject(Vector2 pos){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(pos);
        Body bbody = world.createBody(def);
        PolygonShape p = new PolygonShape();
        p.setAsBox(obj_size, 10 * obj_size);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = p;
        fdef.filter.categoryBits = 4;
        fdef.filter.maskBits = -1;
        fdef.restitution = 0.05f;
        fdef.density = 25;
        fdef.friction = 0.8f;
        bbody.createFixture(fdef);
        body = bbody;
    }
}


package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Wood extends Material{
    public Wood(Vector2 position, World world) {
        super(Assets.woodlog, position, new Vector2( (float)Assets.woodlog.getWidth(), (float)Assets.woodlog.getHeight()-50), world);
    }
    @Override
    public String getName(){
        return "woodlog";
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
        fdef.restitution = 0.1f;
        fdef.density = 10;
        fdef.friction = 0.6f;
        bbody.createFixture(fdef);
        body = bbody;
    }
}

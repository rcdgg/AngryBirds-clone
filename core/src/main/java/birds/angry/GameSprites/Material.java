package birds.angry.GameSprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Material extends DynamicGameObject {
    World world;
    float obj_size;
    public static final short MATERIAL = 4;
    private float strength;
    public Material(Texture texture, Vector2 position, Vector2 size, World world) {
        super(texture, position, size);
        health = 5;
        this.world = world;
        obj_size = 0.075f;
        createObject(position);
    }

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
        fdef.restitution = 0;
        fdef.density = 1;
        fdef.friction = 0.5f;
        bbody.createFixture(fdef);
        body = bbody;
    }
}

package birds.angry.GameSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class Bird extends DynamicGameObject{
    float bird_size;
    World world;
    public static final short BIRD = 1;
    public boolean shot = false;
    public boolean on = false;
    public boolean ability_used = false;
    public Bird(Texture texture, Vector2 position, Vector2 size, World world){
        super(texture, position, size);
        bird_size = 0.3f;
        this.world = world;
        createBird(position);
    }
    public void nextTex(){};

    public ArrayList<Bird> ability(){
        return null;
    }

    public void createBird(Vector2 pos){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(pos);
        Body bbody = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(bird_size);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = circle;
        fdef.filter.categoryBits = BIRD;
        fdef.filter.maskBits = Pig.PIG | Material.MATERIAL | 8 | 16;
        fdef.density = 1;
        fdef.restitution = 0.5f;
        bbody.createFixture(fdef);
        bbody.setFixedRotation(true);
        body = bbody;
    }

    @Override
    public String getName(){
        return "";
    }
}

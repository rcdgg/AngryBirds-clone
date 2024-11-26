package birds.angry.Screens;

import birds.angry.GameSprites.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Level1 extends BaseScreen implements InputProcessor{
    private Button pause;
    private Slingshot slingshot;
//    private Bird redbird, bluebird, yellowbird;
    private Redbird redbird;
    private Bluebird bluebird;
    private Yellowbird yellowbird;
    private PeasantPig ppig;
    private KingPig kingPig;
    private SoldierPig soldierPig;
    private Texture background;
    private Wood woodlog;
    private Ice icelog;
    private Stone stonelog;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Body redbirdBody, bluebirdBody, yellowbirdBody, ground, stone, wood, ice, slingbody, lastBody;
    private ArrayList<Bird> bird_list;
    private OrthographicCamera camera;
    float PPM = 100.0f;
    float bird_size = 0.3f;
    float obj_size = 0.1f;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Box2DDebugRenderer dbg;
    private Stage stage, uistage;
    private float grid_size;
    private final short BIRD = 1;
    private final short GROUND = 2;
    private final short OBSTACLE = 4;
    private MouseJointDef JointDef;
    private MouseJoint mouseJoint, initJoint;
    private Vector4 slingbound;


    public Level1(Game game) {
        super(game);
        stage = new Stage(new FitViewport(1600/ PPM,900 / PPM));
        uistage = new Stage(new FitViewport(1600,900));
        uistage.setDebugAll(false);
        stage.setDebugAll(false);
        slingbound = new Vector4();
        grid_size = 0.5f;
        Box2D.init();
        world = new World(new Vector2(0, -9.81f), true);
        dbg = new Box2DDebugRenderer();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        stone = createObject(new Vector2(5,5));
        ice = createObject(new Vector2(6,5));
        wood = createObject(new Vector2(7,5));

        bodyDef.position.set(0, 1);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ground = world.createBody(bodyDef);

//        fixtureDef.isSensor = false;
//        fixtureDef.restitution = 0.9f;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.maskBits = -1;
        PolygonShape p = new PolygonShape();
        p.setAsBox(16,1);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = GROUND;
        fixtureDef.filter.maskBits = -1;
        ground.createFixture(fixtureDef);

        bodyDef.position.set(3.5f, 3.5f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        slingbody = world.createBody(bodyDef);

//        fixtureDef.isSensor = false;
//        fixtureDef.restitution = 0.9f;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.maskBits = -1;
        p.setAsBox(0.1f,0.1f);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.maskBits = 0;
        slingbody.createFixture(fixtureDef);
        p.dispose();

        bird_list = new ArrayList<>();

//        Texture sling = new Texture(Gdx.files.internal("screens/levels/slingshot.png"));
        background = Assets.level1bg;
        slingshot = new Slingshot(new Vector2(6*grid_size, 4*grid_size));
        slingshot.setSize(2 * grid_size, 4 * grid_size);

        slingbound.x = slingshot.getPosition().x + slingshot.getWidth();
        slingbound.y = slingshot.getPosition().y + slingshot.getHeight();
        slingbound.z = slingbound.x - 2 * slingshot.getWidth();
        slingbound.w = slingbound.y - 1;

        redbird = new Redbird(new Vector2(4,2), world);
        redbird.setSize(2 * bird_size, 2 * bird_size);

//        redbird.setPosition(new Vector2(4*grid_size, 4*grid_size));
        bluebird = new Bluebird(new Vector2(2.5f*grid_size, 4*grid_size), world);
        bluebird.setSize(2.5f * bird_size, 2 * bird_size);

        yellowbird = new Yellowbird(new Vector2(1f*grid_size, 4*grid_size), world);
        yellowbird.setSize(2 * bird_size, 2 * bird_size);

        bird_list.add(redbird); bird_list.add(yellowbird); bird_list.add(bluebird);

        woodlog = new Wood(new Vector2(11*grid_size, 4*grid_size), wood);
        woodlog.setSize(2 * obj_size, 20 * obj_size);
        icelog = new Ice(new Vector2(14*grid_size, 4*grid_size), ice);
        icelog.setSize(2 * obj_size, 20 * obj_size);

        stonelog = new Stone(new Vector2(17*grid_size, 4*grid_size), stone);
        stonelog.setSize(2 * obj_size, 20 * obj_size);

//        redbird.setPosition(700, 600);
//        ppig = new PeasantPig(new Vector2(12*grid_size, 4*grid_size));
//        kingPig = new KingPig(new Vector2(15*grid_size, 4*grid_size));
//        soldierPig = new SoldierPig(new Vector2(18*grid_size, 4*grid_size));
        batch = new SpriteBatch();
        pause = new TextButton("pause", skin);
        pause.setPosition(50, 50);
//        pause.setTouchable(Touchable.enabled);
        uistage.addActor(pause);
        stage.addActor(slingshot);
        stage.addActor(redbird);
        stage.addActor(bluebird);
        stage.addActor(yellowbird);
//        stage.addActor(ppig);
//        stage.addActor(kingPig);
//        stage.addActor(soldierPig);
        stage.addActor(woodlog);
        stage.addActor(icelog);
        stage.addActor(stonelog);
        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause");
                game.setScreen(new Pause(game, game.getScreen()));

            }
        });
        redbirdBody = redbird.body;
        bluebirdBody = bluebird.body;
        yellowbirdBody = yellowbird.body;

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fa = contact.getFixtureA();
                Fixture fb = contact.getFixtureB();
                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == GROUND) || (fa.getFilterData().categoryBits == GROUND && fb.getFilterData().categoryBits == BIRD)){
                    System.out.println("Bird hit the ground");
                }
                if((fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == OBSTACLE)){
                    System.out.println("obj hit the obj");
                }
                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == BIRD)){
                    System.out.println("Bird hit the bird");
                }
                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == OBSTACLE) || (fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == BIRD)){
                    System.out.println("Bird hit the obstacle");
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
//        game.viewport.apply();
//        stage.getBatch().setProjectionMatrix(game.viewport.getCamera().combined);
//        batch.setProjectionMatrix(stage.getCamera().combined);
//        batch.begin();
//        slingshot.render(batch);
//        batch.end();
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
//        super.render(delta);
        if(mouseJoint == null) {
            if(!bird_list.isEmpty()){
                Bird temp = bird_list.getLast();
                temp.body.setTransform(slingbody.getPosition(), 0);
                temp.body.setType(BodyDef.BodyType.StaticBody);
            }

        }

        stage.act(delta);
        stage.draw();
        uistage.act(delta);
        uistage.draw();
        world.step(1/60f, 6,2);
        dbg.render(world, stage.getCamera().combined);

//        update(delta);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.GRAY);
//
//        float gridSpacing = grid_size;
//
//        for (float y = 0; y < stage.getHeight(); y += gridSpacing) {
//            shapeRenderer.line(0, y, stage.getWidth(), y);
//        }
//
//        for (float x = 0; x < stage.getWidth(); x += gridSpacing) {
//            shapeRenderer.line(x, 0, x, stage.getHeight());
//        }
//
//        shapeRenderer.end();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uistage);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
//        b2dr.render(world, camera.combined);
//        batch.begin();
//        batch.draw(Assets.redbirds[0].getTexture(), 100, 100);
//        batch.end();
    }
    public void update(float delta){
        world.step(1/60f, 6, 2);
    }
    public Body createBird(Vector2 pos){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(pos);
        Body bbody = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(bird_size);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = circle;
        fdef.filter.categoryBits = BIRD;
        fdef.filter.maskBits = -1;
        fdef.density = 1;
        fdef.restitution = 0.5f;
        bbody.createFixture(fdef);
        return bbody;
    }
    public Body createObject(Vector2 pos){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(pos);
        Body bbody = world.createBody(def);
        PolygonShape p = new PolygonShape();
        p.setAsBox(obj_size, 10 * obj_size);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = p;
        fdef.filter.categoryBits = OBSTACLE;
        fdef.filter.maskBits = -1;
        fdef.restitution = 0;
        fdef.density = 1;
        fdef.friction = 0.5f;
        bbody.createFixture(fdef);
        return bbody;
    }
    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.W){
            game.setScreen(new WinScreen(game));
            return true;
        }
        if(keycode == Input.Keys.L){
            game.setScreen(new LoseScreen(game));
            return true;
        }
//        else if(keycode==Input.Keys.L){
//            game.setScreen(new LoseScreen(game));
//            return true;
//        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    public boolean within_bounds(Vector2 pos){
        return slingbound.w <= pos.x && pos.x <= slingbound.x && slingbound.z <= pos.y && pos.y <= slingbound.y;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldPos = screenToWorld(screenX, screenY);
        if(!within_bounds(worldPos) || bird_list.isEmpty()) return false;
        Body body = getBodyAt(worldPos);
        if(body == null) return false;
        body.setType(BodyDef.BodyType.DynamicBody);
        lastBody = body;
        if (body != ground && body != stone && body != wood && body != ice) {
            MouseJointDef jointDef = new MouseJointDef();
            jointDef.bodyA = slingbody;
            jointDef.bodyB = body;
            jointDef.target.set(worldPos);
            jointDef.collideConnected = false;
            jointDef.maxForce = 1000f * body.getMass();

            mouseJoint = (MouseJoint) world.createJoint(jointDef);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 worldPos = screenToWorld(screenX, screenY);
        if(!within_bounds(worldPos) || bird_list.isEmpty()) return false;
        if (mouseJoint != null) {
//            float boundx = Math.min(slingbound.x, worldPos.x);
//            float boundy = Math.min(slingbound.y, worldPos.y);
            mouseJoint.setTarget(worldPos); // Update the target position
            System.out.println("target = "+mouseJoint.getTarget());
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 worldPos = screenToWorld(screenX, screenY);
        if(bird_list.isEmpty()) return false;
        Body b = getBodyAt(worldPos);
        if(b==redbirdBody || b==bluebirdBody || b==yellowbirdBody){
            if (mouseJoint != null) {
                world.destroyJoint(mouseJoint); // Remove the joint
                mouseJoint = null;
            }
            if(!within_bounds(worldPos)){
                b.setTransform(slingbody.getPosition(), 0);
                b.setType(BodyDef.BodyType.StaticBody);
                return false;
            }
            bird_list.remove(bird_list.getLast());
            b.applyLinearImpulse(new Vector2(3 * -(worldPos.x - slingbody.getPosition().x), 3 * -(worldPos.y - slingbody.getPosition().y)), b.getWorldCenter(), true);
            return true;
        }
        else {
            if(lastBody == null) return false;
            lastBody.setTransform(slingbody.getPosition(), 0);
            lastBody.setType(BodyDef.BodyType.StaticBody);
            world.destroyJoint(mouseJoint); // Remove the joint
            mouseJoint = null;
        }
        return false;
    }

    private Vector2 screenToWorld(int screenX, int screenY) {
        Vector3 screenCoords = new Vector3(screenX, screenY, 0);
        stage.getCamera().unproject(screenCoords);
        return new Vector2(screenCoords.x, screenCoords.y);
    }

    private Body getBodyAt(Vector2 position) {
        final Body[] result = new Body[1];
        world.QueryAABB((fixture) -> {
            if (fixture.testPoint(position.x, position.y)) {
                result[0] = fixture.getBody();
                return false;
            }
            return true;
        }, position.x - 0.01f, position.y - 0.01f, position.x + 0.01f, position.y + 0.01f);
        return result[0];
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }


    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    public void dispose(){
        super.dispose();
        this.stage.dispose();
        world.dispose();
        dbg.dispose();
    }
}

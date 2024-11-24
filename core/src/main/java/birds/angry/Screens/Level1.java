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
    private Body redbirdBody, bluebirdBody, yellowbirdBody, ground, stone, wood, ice;
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
    private MouseJoint mouseJoint;


    public Level1(Game game) {
        super(game);
        stage = new Stage(new FitViewport(1600/ PPM,900 / PPM));
        uistage = new Stage(new FitViewport(1600,900));
        uistage.setDebugAll(true);

        grid_size = 0.5f;
        Box2D.init();
        world = new World(new Vector2(0, -9.81f), true);
        dbg = new Box2DDebugRenderer();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = BIRD;
        fixtureDef.filter.maskBits = GROUND | OBSTACLE;
        redbirdBody = createBird(new Vector2(1, 10));
        bluebirdBody = createBird(new Vector2(3, 10));
        yellowbirdBody = createBird(new Vector2(2, 13));
        stone = createObject(new Vector2(5,5));
        ice = createObject(new Vector2(6,5));
        wood = createObject(new Vector2(7,5));

        bodyDef.position.set(1, 1);
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
        p.dispose();

//        Texture sling = new Texture(Gdx.files.internal("screens/levels/slingshot.png"));
        background = Assets.level1bg;
        slingshot = new Slingshot(new Vector2(6*grid_size, 4*grid_size));
        redbird = new Redbird(new Vector2(5,5));
        redbird.setSize(2 * bird_size, 2 * bird_size);

//        redbird.setPosition(new Vector2(4*grid_size, 4*grid_size));
        bluebird = new Bluebird(new Vector2(2.5f*grid_size, 4*grid_size));
        bluebird.setSize(2.5f * bird_size, 2 * bird_size);

        yellowbird = new Yellowbird(new Vector2(1f*grid_size, 4*grid_size));
        yellowbird.setSize(2 * bird_size, 2 * bird_size);

        woodlog = new Wood(new Vector2(11*grid_size, 4*grid_size));
        woodlog.setSize(2 * obj_size, 20 * obj_size);
        icelog = new Ice(new Vector2(14*grid_size, 4*grid_size));
        icelog.setSize(2 * obj_size, 20 * obj_size);

        stonelog = new Stone(new Vector2(17*grid_size, 4*grid_size));
        stonelog.setSize(2 * obj_size, 20 * obj_size);

//        redbird.setPosition(700, 600);
        ppig = new PeasantPig(new Vector2(12*grid_size, 4*grid_size));
        kingPig = new KingPig(new Vector2(15*grid_size, 4*grid_size));
        soldierPig = new SoldierPig(new Vector2(18*grid_size, 4*grid_size));
        batch = new SpriteBatch();
        pause = new TextButton("pause", skin);
        pause.setPosition(50, 50);
//        pause.setTouchable(Touchable.enabled);
        uistage.addActor(pause);
//        stage.addActor(slingshot);
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
//        redbirdBody = createBird();
//        ground = createGround();
//        redbirdBody = createBird();
//        ground = createGround();
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

        redbird.setX(redbirdBody.getPosition().x - redbird.getWidth() / 2);
        redbird.setY(redbirdBody.getPosition().y - redbird.getHeight() / 2);

        bluebird.setX(bluebirdBody.getPosition().x - bluebird.getWidth() / 2);
        bluebird.setY(bluebirdBody.getPosition().y - bluebird.getHeight() / 2);
        bluebird.setRotation(bluebirdBody.getAngle() * MathUtils.radiansToDegrees);

        yellowbird.setX(yellowbirdBody.getPosition().x - yellowbird.getWidth() / 2);
        yellowbird.setY(yellowbirdBody.getPosition().y - yellowbird.getHeight() / 2);

        woodlog.setX(wood.getPosition().x - woodlog.getWidth() / 2);
        woodlog.setY(wood.getPosition().y - woodlog.getHeight() / 2);
        woodlog.setRotation(wood.getAngle() * MathUtils.radiansToDegrees);

        stonelog.setX(stone.getPosition().x - stonelog.getWidth() / 2);
        stonelog.setY(stone.getPosition().y - stonelog.getHeight() / 2);
        stonelog.setRotation(stone.getAngle() * MathUtils.radiansToDegrees);

        icelog.setX(ice.getPosition().x - icelog.getWidth() / 2);
        icelog.setY(ice.getPosition().y - icelog.getHeight() / 2);
        icelog.setRotation(ice.getAngle() * MathUtils.radiansToDegrees);
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldPos = screenToWorld(screenX, screenY);

        Body body = getBodyAt(worldPos);
        if (body != null && body != ground) {
            MouseJointDef jointDef = new MouseJointDef();
            jointDef.bodyA = ground;
            jointDef.bodyB = body;
            jointDef.target.set(worldPos);
            jointDef.collideConnected = true;
            jointDef.maxForce = 1000f * body.getMass();

            mouseJoint = (MouseJoint) world.createJoint(jointDef);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (mouseJoint != null) {
            Vector2 worldPos = screenToWorld(screenX, screenY);
            mouseJoint.setTarget(worldPos); // Update the target position
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (mouseJoint != null) {
            world.destroyJoint(mouseJoint); // Remove the joint
            mouseJoint = null;
        }
        return true;
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

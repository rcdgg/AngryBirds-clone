package birds.angry.Screens;

import birds.angry.GameSprites.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private Body redbirdBody;
    private Body ground;
    private OrthographicCamera camera;
    float PPM = 100.0f;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Box2DDebugRenderer dbg;
    private Stage stage, uistage;
    private float grid_size;
    private final short BIRD = 1;
    private final short GROUND = 2;
    private final short OBSTACLE = 4;
    private MouseJointDef JointDef;
    private MouseJoint joint;


    public Level1(Game game) {
        super(game);
        stage = new Stage(new FitViewport(1600/ PPM,900 / PPM));
        uistage = new Stage(new FitViewport(1600,900));

        grid_size = 0.5f;
        Box2D.init();
        world = new World(new Vector2(0, -9.81f), true);
        dbg = new Box2DDebugRenderer();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = BIRD;
        fixtureDef.filter.maskBits = GROUND | OBSTACLE;
        bodyDef.position.set(1,10);
        bodyDef.gravityScale = 1;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        redbirdBody = createBird();

//        fixtureDef.isSensor = false;
//        fixtureDef.restitution = 0.9f;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.maskBits = -1;
        CircleShape c = new CircleShape();
        c.setRadius(1);
//        fixtureDef.shape = c;
//        fixtureDef.restitution = 0.5f;
//        redbirdBody.createFixture(fixtureDef);
//        c.dispose();


        bodyDef.position.set(1, 1);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

//        fixtureDef.isSensor = false;
//        fixtureDef.restitution = 0.9f;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.maskBits = -1;
        PolygonShape p = new PolygonShape();
        p.setAsBox(16,1);

        fixtureDef.shape = p;
        fixtureDef.filter.categoryBits = GROUND;
        fixtureDef.filter.maskBits = BIRD;
        body.createFixture(fixtureDef);
        p.dispose();

//        Texture sling = new Texture(Gdx.files.internal("screens/levels/slingshot.png"));
        background = Assets.level1bg;
        slingshot = new Slingshot(new Vector2(6*grid_size, 4*grid_size));
        redbird = new Redbird(new Vector2(5,5));
        redbird.setSize(2 * c.getRadius(), 2 * c.getRadius());
        redbird.setOriginX(redbird.getWidth() / 2);
        redbird.setOriginY(redbird.getHeight() /2);
//        redbird.setPosition(new Vector2(4*grid_size, 4*grid_size));
        bluebird = new Bluebird(new Vector2(2.5f*grid_size, 4*grid_size));
        yellowbird = new Yellowbird(new Vector2(1f*grid_size, 4*grid_size));
        woodlog = new Wood(new Vector2(11*grid_size, 4*grid_size));
        icelog = new Ice(new Vector2(14*grid_size, 4*grid_size));
        stonelog = new Stone(new Vector2(17*grid_size, 4*grid_size));
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
//        stage.addActor(bluebird);
//        stage.addActor(yellowbird);
//        stage.addActor(ppig);
//        stage.addActor(kingPig);
//        stage.addActor(soldierPig);
        stage.addActor(woodlog);
//        stage.addActor(icelog);
//        stage.addActor(stonelog);
        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause");
                game.setScreen(new Pause(game, game.getScreen()));

            }
        });
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
        JointDef = new MouseJointDef();
        JointDef.bodyA = world.createBody(new BodyDef());
        JointDef.collideConnected = true;
        JointDef.maxForce = 10.0f;
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
        stage.act(delta);
        stage.draw();
//        uistage.act(delta);
//        uistage.draw();
//        update(delta);
        world.step(1/60f, 6,2);
        dbg.render(world, stage.getCamera().combined);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
//        b2dr.render(world, camera.combined);
//        batch.begin();
//        batch.draw(Assets.redbirds[0].getTexture(), 100, 100);
//        batch.end();
    }
    public void update(float delta){
        world.step(1/60f, 6, 2);
    }
    public Body createBird(){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(1,10);
        Body bbody = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(1);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = circle;
        fdef.restitution = 0.5f;
        bbody.createFixture(fdef);
        return bbody;
    }
    public Body createGround(){
        BodyDef grounddef = new BodyDef();
        grounddef.position.set(0,0);
        grounddef.type = BodyDef.BodyType.StaticBody;
        Body ground = world.createBody(grounddef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(grid_size*30, 4*grid_size);
        FixtureDef gdef = new FixtureDef();
        gdef.shape = shape;
        gdef.filter.categoryBits = 2;
        gdef.filter.maskBits = -1;
        ground.createFixture(gdef);
        return ground;
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

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();
    private QueryCallback queryCallback = new QueryCallback() {
        @Override
        public boolean reportFixture(Fixture fixture) {
            if(!fixture.testPoint(tmp.x, tmp.y))
                return false;
            if(fixture.getBody()==redbirdBody)System.out.println("Redbird");
            JointDef.bodyB = fixture.getBody();
            JointDef.target.set(tmp.x, tmp.y);
            joint = (MouseJoint) world.createJoint(JointDef);
            return true;
        }
    };
    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        stage.getCamera().unproject(tmp.set(i, i1, 0));
        tmp.x /= PPM;
        tmp.y /= PPM;
        world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);

        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        if(joint==null){
            return false;
        }
        world.destroyJoint(joint);
        joint = null;
        return true;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        if(joint==null) return false;
        stage.getCamera().unproject(tmp.set(i, i1, 0));
        Vector2 wc = new Vector2(tmp.x/PPM, tmp.y/PPM);
        joint.setTarget(tmp2.set(wc.x, wc.y));
        System.out.println("being touched");
        return true;
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

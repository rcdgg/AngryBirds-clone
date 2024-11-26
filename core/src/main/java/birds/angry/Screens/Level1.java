package birds.angry.Screens;

import birds.angry.GameSprites.*;
import birds.angry.GameState;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Level1 extends LevelScreen implements InputProcessor{
//    private Button pause;
//    private Slingshot slingshot;
////    private Bird redbird, bluebird, yellowbird;
//    private Redbird redbird;
//    private Bluebird bluebird;
//    private Yellowbird yellowbird;
//    private PeasantPig ppig;
//    private KingPig kingPig;
//    private SoldierPig soldierPig;
//    private Texture background;
//    private Wood woodlog;
//    private Ice icelog;
//    private Stone stonelog;
//    private World world;
//    private Box2DDebugRenderer b2dr;
//    private Body redbirdBody, bluebirdBody, yellowbirdBody, ground, stone, wood, ice, slingbody, lastBody;
//    private ArrayList<Bird> bird_list;
//    private ArrayList<Material> mat_list;
//    private ArrayList<Pig> pig_list;
//    private OrthographicCamera camera;
//    float PPM = 100.0f;
//    float bird_size = 0.3f;
//    float obj_size = 0.1f;
//    private BodyDef bodyDef;
//    private FixtureDef fixtureDef;
//    private Box2DDebugRenderer dbg;
//    private Stage stage, uistage, pausestage;
//    private float grid_size;
//    private final short BIRD = Bird.BIRD;
//    private final short GROUND = 2;
//    private final short OBSTACLE = Material.MATERIAL;
//    private final short PIG = Pig.PIG;
//    private MouseJointDef JointDef;
//    private MouseJoint mouseJoint, initJoint;
//    private Vector4 slingbound;
//    private GameState gameState;
//    private boolean pause_b = false;
//    private Image pausebg;

    public Level1(Game game, String filepath) {
        super(game, filepath);
//        stage = new Stage(new FitViewport(1600/ PPM,900 / PPM));
//        uistage = new Stage(new FitViewport(1600,900));
//        pausestage = new Stage(new FitViewport(1600,900));
//        uistage.setDebugAll(false);
//        pausestage.setDebugAll(true);
//        stage.setDebugAll(false);
//        slingbound = new Vector4();
//        grid_size = 0.5f;
//        Box2D.init();
//        world = new World(new Vector2(0, -9.81f), true);
//        dbg = new Box2DDebugRenderer();
//
//        bodyDef = new BodyDef();
//        fixtureDef = new FixtureDef();

//        stone = createObject(new Vector2(5,5));
//        ice = createObject(new Vector2(6,5));
//        wood = createObject(new Vector2(7,5));

        //ground
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
        p.setAsBox(0.01f,0.01f);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.maskBits = 0;
        slingbody.createFixture(fixtureDef);
        p.dispose();

//        bird_list = new ArrayList<>();
//        mat_list = new ArrayList<>();
//        pig_list = new ArrayList<>();
//        Texture sling = new Texture(Gdx.files.internal("screens/levels/slingshot.png"));
        background = Assets.level1bg;
        //slingshot
        slingshot = new Slingshot(new Vector2(6*grid_size, 4*grid_size));
        slingshot.setSize(2 * grid_size, 4 * grid_size);

        slingbound.x = slingshot.getPosition().x + slingshot.getWidth();
        slingbound.y = slingshot.getPosition().y + slingshot.getHeight();
        slingbound.z = slingbound.x - 1.5f * slingshot.getWidth();
        slingbound.w = slingbound.y - 1.5f;
        stage.addActor(slingshot);
        //---------------------
//        redbird = new Redbird(new Vector2(2.5f*grid_size,4), world);
//        redbird.setSize(2 * bird_size, 2 * bird_size);
//
////        redbird.setPosition(new Vector2(4*grid_size, 4*grid_size));
//        bluebird = new Bluebird(new Vector2(2.5f*grid_size, 4), world);
//        bluebird.setSize(2.5f * bird_size, 2 * bird_size);
//
//        yellowbird = new Yellowbird(new Vector2(1f*grid_size, 3), world);
//        yellowbird.setSize(2 * bird_size, 2 * bird_size);
//
//        bird_list.add(redbird); bird_list.add(yellowbird); bird_list.add(bluebird);
//
//        woodlog = new Wood(new Vector2(5, 5), world);
//        woodlog.setSize(2 * obj_size, 20 * obj_size);
//        icelog = new Ice(new Vector2(14*grid_size, 5), world);
//        icelog.setSize(2 * obj_size, 20 * obj_size);
//
//        stonelog = new Stone(new Vector2(17*grid_size, 5), world);
//        stonelog.setSize(2 * obj_size, 20 * obj_size);
//
//        mat_list.add(icelog); mat_list.add(woodlog); mat_list.add(stonelog);
//
//        ppig = new PeasantPig(new Vector2(13*grid_size, 5*grid_size), world);
//        ppig.setSize(2 * bird_size, 2 * bird_size);
//        kingPig = new KingPig(new Vector2(16*grid_size, 5*grid_size), world);
//        kingPig.setSize(2 * bird_size, 2 * bird_size);
//        soldierPig = new SoldierPig(new Vector2(19*grid_size, 5*grid_size), world);
//        soldierPig.setSize(2 * bird_size, 2 * bird_size);
//        pig_list.add(ppig); pig_list.add(kingPig); pig_list.add(soldierPig);
//
        //----------------------
//        batch = new SpriteBatch();
//        pause = new TextButton("pause", skin);
//        pause.setPosition(50, 50);
////        pause.setTouchable(Touchable.enabled);
//        uistage.addActor(pause);
//        stage.addActor(slingshot);
//        stage.addActor(redbird);
//        stage.addActor(bluebird);
//        stage.addActor(yellowbird);
////        stage.addActor(ppig);
////        stage.addActor(kingPig);
////        stage.addActor(soldierPig);
//        stage.addActor(woodlog);
//        stage.addActor(icelog);
//        stage.addActor(stonelog);
//        grid_size = 50;
//        pausebg = Assets.pausebg;
//        pausebg.setSize(500,   pausebg.getHeight() / pausebg.getWidth() * 500);
//        pausebg.setPosition(pausestage.getWidth() / 2 - pausebg.getWidth() / 2 - 50, pausestage.getHeight() / 2 - pausebg.getHeight() / 2);

//        Button resume = new Button(invisibleButtonStyle);
//        resume.setSize(5 * grid_size,1.15f * grid_size);
//        resume.setPosition(12.5f * grid_size, 11 * grid_size);
//        Button save = new Button(invisibleButtonStyle);
//        save.setSize(5 * grid_size,1.15f * grid_size);
//        save.setPosition(12.5f * grid_size, 7.75f * grid_size);
//        Button restart = new Button(invisibleButtonStyle);
//        restart.setSize(5 * grid_size,1.15f * grid_size);
//        restart.setPosition(12.5f * grid_size, 9.4f * grid_size);
//        Button levelselect = new Button(invisibleButtonStyle);
//        levelselect.setSize(5 * grid_size,1.15f * grid_size);
//        levelselect.setPosition(12.5f * grid_size, 6 * grid_size);
//        pausestage.addActor(pausebg);
//        pausestage.addActor(resume);
//        pausestage.addActor(restart);
//        pausestage.addActor(save);
//        pausestage.addActor(levelselect);
//
//        resume.addListener(event -> {
//            if (event.toString().equals("touchDown")) {
//                // Handle button click
//                pause_b = false;
//                System.out.println("work");
//                return true;
//            }
//            return false;
//        });
//        levelselect.addListener(event -> {
//            if (event.toString().equals("touchDown")) {
//                // Handle button click
//                game.setScreen(new LevelSelect(game));
//            }
//            return false;
//        });
//        save.addListener(event -> {
//            if (event.toString().equals("touchDown")) {
//                // Handle button click
//                save_game();
//                pause_b = false;
//            }
//            return false;
//        });public void save_gamestate(){
//        gameState = new GameState();
//        for(Bird b: bird_list){
//            GameState.GameObjectState g = new GameState.GameObjectState();
//            g.type =  b.getName();
//            g.x = b.body.getPosition().x;
//            g.y = b.body.getPosition().y;
//            g.vx = b.body.getLinearVelocity().x;
//            g.vy = b.body.getLinearVelocity().y;
//            g.sx = b.getWidth();
//            g.sy = b.getHeight();
//            g.angle = b.body.getAngle();
//            gameState.birds.add(g);
//            System.out.println(g.type);
//        }
//        for(Material b: mat_list){
//            GameState.GameObjectState g = new GameState.GameObjectState();
//            g.type =  b.getName();
//            g.x = b.body.getPosition().x;
//            g.y = b.body.getPosition().y;
//            g.vx = b.body.getLinearVelocity().x;
//            g.vy = b.body.getLinearVelocity().y;
//            g.sx = b.getWidth();
//            g.sy = b.getHeight();
//            g.angle = b.body.getAngle();
//            gameState.materials.add(g);
//            System.out.println(g.type);
//        }
//        for(Pig p : pig_list){
//            GameState.GameObjectState g = new GameState.GameObjectState();
//            g.type = p.getName();g.x = p.body.getPosition().x;
//            g.y = p.body.getPosition().y;
//            g.vx = p.body.getLinearVelocity().x;
//            g.vy = p.body.getLinearVelocity().y;
//            g.sx = p.getWidth();
//            g.sy = p.getHeight();
//            g.angle = p.body.getAngle();
//            gameState.pigs.add(g);
//            System.out.println(g.type);
//        }
//        // for pigs as well and health score etc
//
//    }
//
//    public void save_game(){
//        save_gamestate();
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath))) {
//            out.writeObject(gameState);
//            System.out.println("Game state saved successfully!");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void load_game(String filepath){
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath))){
//            gameState =(GameState) in.readObject();
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        bird_list.clear();
//        mat_list.clear();
//        pig_list.clear();
//
//        for(GameState.GameObjectState b: gameState.birds){
//            Bird bird = null;
//            if(Objects.equals(b.type, "Redbird")) bird = new Redbird(new Vector2(b.x, b.y), world);
//            else if(Objects.equals(b.type, "Yellowbird")) bird = new Yellowbird(new Vector2(b.x, b.y), world);
//            else bird = new Bluebird(new Vector2(b.x, b.y), world);
//            bird.setSize(b.sx, b.sy);
//            Body body = bird.body;
//            body.setTransform(b.x, b.y, b.angle);
//            body.setLinearVelocity(b.vx, b.vy);
//
//            bird_list.add(bird);
//        }
//        for(GameState.GameObjectState b: gameState.materials){
//            Material mat = null;
//            if(Objects.equals(b.type, "icelog")) mat = new Ice(new Vector2(b.x, b.y), world);
//            else if(Objects.equals(b.type, "woodlog")) mat = new Wood(new Vector2(b.x, b.y), world);
//            else mat = new Stone(new Vector2(b.x, b.y), world);
//            mat.setSize(b.sx, b.sy);
//            Body body = mat.body;
//            body.setTransform(b.x, b.y, b.angle);
//            body.setLinearVelocity(b.vx, b.vy);
//            mat_list.add(mat);
//        }
//        for(GameState.GameObjectState p : gameState.pigs){
//            Pig pig = null;
//            if(Objects.equals(p.type, "KingPig")) {pig = new KingPig(new Vector2(p.x, p.y), world); System.out.println("KingPig");}
//            else if(Objects.equals(p.type, "PeasantPig")) pig = new PeasantPig(new Vector2(p.x, p.y), world);
//            else pig = new SoldierPig(new Vector2(p.x, p.y), world);
//            pig.setSize(p.sx, p.sy);
//            Body body = pig.body;
//            body.setTransform(p.x, p.y, p.angle);
//            body.setLinearVelocity(p.vx, p.vy);
//            pig_list.add(pig);
//        }
//        System.out.println("load done");
//    }

        restart.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                game.setScreen(new Level1(game, "core/src/main/java/birds/angry/level1.ser"));
                pause_b = false;
            }
            return false;
        });

//        pause.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Pause");
//                pause_b = true;
////                game.setScreen(new Pause(game, game.getScreen()));
//
//            }
//        });
//        redbirdBody = redbird.body;
//        bluebirdBody = bluebird.body;
//        yellowbirdBody = yellowbird.body;

//        world.setContactListener(new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//                Fixture fa = contact.getFixtureA();
//                Fixture fb = contact.getFixtureB();
//                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == GROUND) || (fa.getFilterData().categoryBits == GROUND && fb.getFilterData().categoryBits == BIRD)){
//                    System.out.println("Bird hit the ground");
//                }
//                if((fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == OBSTACLE)){
//                    System.out.println("obj hit the obj");
//                }
//                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == BIRD)){
//                    System.out.println("Bird hit the bird");
//                }
//                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == OBSTACLE) || (fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == BIRD)){
//                    System.out.println("Bird hit the obstacle");
//                }
//                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == BIRD)){
//                    System.out.println("Bird hit the pig");
//                }
//                if((fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == OBSTACLE)){
//                    System.out.println("obj hit the pig");
//                }
//                if((fa.getFilterData().categoryBits == GROUND && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == GROUND)){
//                    System.out.println("Pig hit the ground");
//                }
//            }
//
//            @Override
//            public void endContact(Contact contact) {
//
//            }
//
//            @Override
//            public void preSolve(Contact contact, Manifold manifold) {
//
//            }
//
//            @Override
//            public void postSolve(Contact contact, ContactImpulse contactImpulse) {
//
//            }
//        });
        load_game(filepath);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
//        ScreenUtils.clear(Color.BLACK);
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
//        if(mouseJoint == null) {
//            if(!bird_list.isEmpty()){
//                Bird temp = bird_list.getLast();
//                temp.on = true;
//                temp.body.setTransform(slingbody.getPosition(), 0);
//                temp.body.setType(BodyDef.BodyType.StaticBody);
//            }
//
//        }
//        for(Bird b: bird_list){
//            stage.addActor(b);
//        }
//        for(Material b: mat_list){
//            stage.addActor(b);
//        }
//        for(Pig p: pig_list){
//            stage.addActor(p);
//        }
        stage.act(delta);
        stage.draw();
        uistage.act(delta);
        uistage.draw();
        if(pause_b){
            pausestage.act(delta);
            pausestage.draw();
        }
        else world.step(1/60f, 6,2);
        dbg.render(world, stage.getCamera().combined);


//        InputMultiplexer multiplexer = new InputMultiplexer();
//        multiplexer.addProcessor(uistage);
//        multiplexer.addProcessor(stage);
//        multiplexer.addProcessor(pausestage);
//        multiplexer.addProcessor(this);
//        Gdx.input.setInputProcessor(multiplexer);
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

//    public boolean within_bounds(Vector2 pos){
//        return slingbound.z <= pos.x && pos.x <= slingbound.x && slingbound.w <= pos.y && pos.y <= slingbound.y;
//    }
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        Vector2 worldPos = screenToWorld(screenX, screenY);
//        if(!within_bounds(worldPos) || bird_list.isEmpty()) {
//            System.out.println("hi");
//            return false;
//        }
//        Body body = getBodyAt(worldPos);
//        if(body == null) return false;
//        if(body != slingbody) body.setType(BodyDef.BodyType.DynamicBody);
//        lastBody = body;
//        if (body != ground /*&& body != stone && body != wood && body != ice && body != slingbody*/) {
//            MouseJointDef jointDef = new MouseJointDef();
//            jointDef.bodyA = ground;
//            jointDef.bodyB = body;
//            jointDef.target.set(worldPos);
//            jointDef.collideConnected = false;
//            jointDef.maxForce = 1000f * body.getMass();
//
//            mouseJoint = (MouseJoint) world.createJoint(jointDef);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        Vector2 worldPos = screenToWorld(screenX, screenY);
//        if(bird_list.isEmpty()) return false;
//        if (mouseJoint != null) {
//            float boundx = Math.max(Math.min(slingbound.x, worldPos.x), slingbound.z);
//            float boundy = Math.max(Math.min(slingbound.y, worldPos.y), slingbound.w);
//            mouseJoint.setTarget(new Vector2(boundx, boundy)); // Update the target position
//            System.out.println("target = "+mouseJoint.getTarget());
//        }
//        return true;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        Vector2 worldPos = screenToWorld(screenX, screenY);
//        if(bird_list.isEmpty()) return false;
//        Body b = getBodyAt(worldPos);
//        Bird bb = null;
//        if(b != null){
//            System.out.println("here");
//            if (mouseJoint != null) {
//                world.destroyJoint(mouseJoint); // Remove the joint
//                mouseJoint = null;
//            }
//            for(Bird temp: bird_list){
//                if(temp.body.equals(b)){
//                    bb = temp;
//                    System.out.println("bb is "+bb);
//                }
//            }
//            if(bb==null) return false;
//            if(!within_bounds(worldPos)){
//                assert bb != null;
//                if(bb.on){
//                    b.setTransform(slingbody.getPosition(), 0);
//                    b.setType(BodyDef.BodyType.StaticBody);
//                }
//                return false;
//            }
//            bb.shot = true;
//            bird_list.remove(bird_list.getLast());
//            b.applyLinearImpulse(new Vector2(3 * -(worldPos.x - slingbody.getPosition().x), 3 * -(worldPos.y - slingbody.getPosition().y)), b.getWorldCenter(), true);
//            return true;
//        }
//        else {
//            if(lastBody == null) return false;
//            for(Bird t: bird_list){
//                if(t.body.equals(lastBody)){
//                    System.out.println(t.getClass().getSimpleName());
//                    if(!t.shot){
//                        lastBody.setTransform(slingbody.getPosition(), 0);
//                        lastBody.setType(BodyDef.BodyType.StaticBody);
//                    }
//                }
//            }
//            if(mouseJoint!= null)world.destroyJoint(mouseJoint); // Remove the joint
//            mouseJoint = null;
//        }
//        return false;
//    }
//
//    private Vector2 screenToWorld(int screenX, int screenY) {
//        Vector3 screenCoords = new Vector3(screenX, screenY, 0);
//        stage.getCamera().unproject(screenCoords);
//        return new Vector2(screenCoords.x, screenCoords.y);
//    }
//
//    private Body getBodyAt(Vector2 position) {
//        final Body[] result = new Body[1];
//        world.QueryAABB((fixture) -> {
//            if (fixture.testPoint(position.x, position.y)) {
//                result[0] = fixture.getBody();
//                return false;
//            }
//            return true;
//        }, position.x - 0.01f, position.y - 0.01f, position.x + 0.01f, position.y + 0.01f);
//        return result[0];
//    }

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

//    public void save_gamestate(){
//        gameState = new GameState();
//        for(Bird b: bird_list){
//            GameState.GameObjectState g = new GameState.GameObjectState();
//            g.type =  b.getName();
//            g.x = b.body.getPosition().x;
//            g.y = b.body.getPosition().y;
//            g.vx = b.body.getLinearVelocity().x;
//            g.vy = b.body.getLinearVelocity().y;
//            g.sx = b.getWidth();
//            g.sy = b.getHeight();
//            g.angle = b.body.getAngle();
//            gameState.birds.add(g);
//            System.out.println(g.type);
//        }
//        for(Material b: mat_list){
//            GameState.GameObjectState g = new GameState.GameObjectState();
//            g.type =  b.getName();
//            g.x = b.body.getPosition().x;
//            g.y = b.body.getPosition().y;
//            g.vx = b.body.getLinearVelocity().x;
//            g.vy = b.body.getLinearVelocity().y;
//            g.sx = b.getWidth();
//            g.sy = b.getHeight();
//            g.angle = b.body.getAngle();
//            gameState.materials.add(g);
//            System.out.println(g.type);
//        }
//        for(Pig p : pig_list){
//            GameState.GameObjectState g = new GameState.GameObjectState();
//            g.type = p.getName();g.x = p.body.getPosition().x;
//            g.y = p.body.getPosition().y;
//            g.vx = p.body.getLinearVelocity().x;
//            g.vy = p.body.getLinearVelocity().y;
//            g.sx = p.getWidth();
//            g.sy = p.getHeight();
//            g.angle = p.body.getAngle();
//            gameState.pigs.add(g);
//            System.out.println(g.type);
//        }
//        // for pigs as well and health score etc
//
//    }
//
//    public void save_game(){
//        save_gamestate();
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath))) {
//            out.writeObject(gameState);
//            System.out.println("Game state saved successfully!");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void load_game(String filepath){
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath))){
//            gameState =(GameState) in.readObject();
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        bird_list.clear();
//        mat_list.clear();
//        pig_list.clear();
//
//        for(GameState.GameObjectState b: gameState.birds){
//            Bird bird = null;
//            if(Objects.equals(b.type, "Redbird")) bird = new Redbird(new Vector2(b.x, b.y), world);
//            else if(Objects.equals(b.type, "Yellowbird")) bird = new Yellowbird(new Vector2(b.x, b.y), world);
//            else bird = new Bluebird(new Vector2(b.x, b.y), world);
//            bird.setSize(b.sx, b.sy);
//            Body body = bird.body;
//            body.setTransform(b.x, b.y, b.angle);
//            body.setLinearVelocity(b.vx, b.vy);
//
//            bird_list.add(bird);
//        }
//        for(GameState.GameObjectState b: gameState.materials){
//            Material mat = null;
//            if(Objects.equals(b.type, "icelog")) mat = new Ice(new Vector2(b.x, b.y), world);
//            else if(Objects.equals(b.type, "woodlog")) mat = new Wood(new Vector2(b.x, b.y), world);
//            else mat = new Stone(new Vector2(b.x, b.y), world);
//            mat.setSize(b.sx, b.sy);
//            Body body = mat.body;
//            body.setTransform(b.x, b.y, b.angle);
//            body.setLinearVelocity(b.vx, b.vy);
//            mat_list.add(mat);
//        }
//        for(GameState.GameObjectState p : gameState.pigs){
//            Pig pig = null;
//            if(Objects.equals(p.type, "KingPig")) {pig = new KingPig(new Vector2(p.x, p.y), world); System.out.println("KingPig");}
//            else if(Objects.equals(p.type, "PeasantPig")) pig = new PeasantPig(new Vector2(p.x, p.y), world);
//            else pig = new SoldierPig(new Vector2(p.x, p.y), world);
//            pig.setSize(p.sx, p.sy);
//            Body body = pig.body;
//            body.setTransform(p.x, p.y, p.angle);
//            body.setLinearVelocity(p.vx, p.vy);
//            pig_list.add(pig);
//        }
//        System.out.println("load done");
//    }


    public void dispose(){
        super.dispose();
        this.stage.dispose();
        world.dispose();
        dbg.dispose();
    }
}

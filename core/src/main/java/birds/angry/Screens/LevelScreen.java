package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameSprites.*;
import birds.angry.GameState;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import java.util.Objects;

public class LevelScreen extends BaseScreen implements InputProcessor {
    Button pause;
     Slingshot slingshot;
    //    private Bird redbird, bluebird, yellowbird;
     Texture background;
     World world;
     Body ground, slingbody, lastBody;
     ArrayList<Bird> bird_list;
     ArrayList<Material> mat_list;
     ArrayList<Pig> pig_list;
     OrthographicCamera camera;
    float PPM = 100.0f;
    float bird_size = 0.3f;
    float obj_size = 0.075f;
     BodyDef bodyDef;
     FixtureDef fixtureDef;
     Box2DDebugRenderer dbg;
     Stage stage, uistage, pausestage;
     float grid_size = 0.5f;
     final short BIRD = Bird.BIRD;
     final short GROUND = 8;
     final short OBSTACLE = Material.MATERIAL;
     final short PIG = Pig.PIG;
     MouseJointDef JointDef;
     MouseJoint mouseJoint;
     Vector4 slingbound;
     GameState gameState;
     boolean pause_b = false;
     Image pausebg;
     Button restart, save;
     String filepath;
     ArrayList<DynamicGameObject> to_remove;
     int score;
     public Bird lastbird;

    public LevelScreen(Game game, String filepath) {
        super(game);
        mouseJoint = null;
        gameState = new GameState();
        to_remove = new ArrayList<>();
        this.filepath = filepath;
        stage = new Stage(new FitViewport(1600/ PPM,900 / PPM));
        uistage = new Stage(new FitViewport(1600,900));
        pausestage = new Stage(new FitViewport(1600,900));

        uistage.setDebugAll(false);
        pausestage.setDebugAll(true);
        stage.setDebugAll(false);

        slingbound = new Vector4();

        grid_size = 0.5f;
        Box2D.init();
        world = new World(new Vector2(0, -9.81f), true);
        dbg = new Box2DDebugRenderer();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        bird_list = new ArrayList<>();
        mat_list = new ArrayList<>();
        pig_list = new ArrayList<>();

        batch = new SpriteBatch();
        pause = new TextButton("pause", skin);
        pause.setPosition(50, 50);
        uistage.addActor(pause);

        grid_size = 50;

        //---- pause stage
        pausebg = Assets.pausebg;
        pausebg.setSize(500,   pausebg.getHeight() / pausebg.getWidth() * 500);
        pausebg.setPosition(pausestage.getWidth() / 2 - pausebg.getWidth() / 2 - 50, pausestage.getHeight() / 2 - pausebg.getHeight() / 2);

        Button resume = new Button(invisibleButtonStyle);
        resume.setSize(5 * grid_size,1.15f * grid_size);
        resume.setPosition(12.5f * grid_size, 11 * grid_size);

        save = new Button(invisibleButtonStyle);
        save.setSize(5 * grid_size,1.15f * grid_size);
        save.setPosition(12.5f * grid_size, 7.75f * grid_size);

        restart = new Button(invisibleButtonStyle);
        restart.setSize(5 * grid_size,1.15f * grid_size);
        restart.setPosition(12.5f * grid_size, 9.4f * grid_size);

        Button levelselect = new Button(invisibleButtonStyle);
        levelselect.setSize(5 * grid_size,1.15f * grid_size);
        levelselect.setPosition(12.5f * grid_size, 6 * grid_size);

        pausestage.addActor(pausebg);
        pausestage.addActor(resume);
        pausestage.addActor(restart);
        pausestage.addActor(save);
        pausestage.addActor(levelselect);

        resume.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                pause_b = false;
                System.out.println("work");
                return true;
            }
            return false;
        });
        levelselect.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                game.setScreen(new LevelSelect(game));
            }
            return false;
        });

        //restart and save is level specific
        //----

        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause");
                pause_b = true;
//                game.setScreen(new Pause(game, game.getScreen()));

            }
        });
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
                    try{
                        Material hitmat = (Material) getObjectAt(fa.getBody());
                        if (hitmat!=null) {
                            hitmat.health -= 1;
                            if(hitmat.health<=0) {
                                to_remove.add(hitmat);
                                score += hitmat.score;
                            }
                        }
                    }catch (ClassCastException e)
                    {
                        Material hitmat = (Material) getObjectAt(fb.getBody());
                        if(hitmat!=null) {
                            hitmat.health -= 1;
                            if(hitmat.health<=0) {
                                to_remove.add(hitmat);
                                score += hitmat.score;
                            }
                        }
                    }

                }
                if((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == BIRD)){
                    System.out.println("Bird hit the pig");
                    try{
                        Pig hitpig = (Pig) getObjectAt(fa.getBody());
                        if (hitpig!=null) {
                            hitpig.health -= 1;
                            if(hitpig.health<=0) {
                                score += hitpig.score;
                                to_remove.add(hitpig);
                            }
                        }
                    }catch (ClassCastException e)
                    {
                        Pig hitpig = (Pig) getObjectAt(fb.getBody());
                        if(hitpig!=null) {
                            hitpig.health -= 1;
                            if(hitpig.health<=0) {
                                score += hitpig.score;
                                to_remove.add(hitpig);
                            }
                        }
                    }
                }
                if((fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == OBSTACLE)){
                    System.out.println("obj hit the pig");
                    DynamicGameObject a, b;
                    try{
                        a = (Pig) getObjectAt(fa.getBody());
                        b = (Material) getObjectAt(fb.getBody());
                        if(a != null && b != null) {
                            a.health -= 1;
                            b.health -= 1;
                            if(a.health <= 0) {
                                to_remove.add(a);
                                score += a.score;
                            }
                            if(b.health <= 0) {
                                to_remove.add(b);
                                score += b.score;
                            }
                        }
                    }catch (ClassCastException e)
                    {
                        a = (Material) getObjectAt(fa.getBody());
                        b = (Pig) getObjectAt(fb.getBody());
                        if(a != null && b != null) {
                            a.health -= 1;
                            b.health -= 1;
                            if(a.health <= 0) {
                                to_remove.add(a);
                                score += a.score;
                            }
                            if(b.health <= 0) {
                                to_remove.add(b);
                                score += b.score;
                            }
                        }
                    }
                }
                if((fa.getFilterData().categoryBits == GROUND && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == GROUND)){
                    System.out.println("Pig hit the ground");
                    Body pig;
                    try{
                        pig = getBodyAt(fa.getBody().getPosition());
                    }catch (ClassCastException e)
                    {
                        pig = getBodyAt(fb.getBody().getPosition());
                    }
                    if(pig!=null) System.out.println("pigggggy!");
                    if(pig!=null && pig.getLinearVelocity().y < -0.2f){

                    }
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
        grid_size = 0.5f;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        ScreenUtils.clear(Color.BLACK);
        if(mouseJoint == null) {
            if(!bird_list.isEmpty()){
                Bird temp = bird_list.getLast();
                temp.on = true;
                temp.body.setTransform(slingbody.getPosition(), 0);
                temp.body.setType(BodyDef.BodyType.StaticBody);
            }

        }
        TextButton score_b = new TextButton(String.format("%d", score), skin);
        score_b.setPosition(800,1000);
        uistage.addActor(score_b);
        for(Bird b: bird_list){
            stage.addActor(b);
        }
        for(Material b: mat_list){
            stage.addActor(b);
        }
        for(Pig p: pig_list){
            if(p.body.getPosition().x <= 16 + p.getWidth() / 2) stage.addActor(p);
            else {
                to_remove.add(p);
//                world.destroyBody(p.body);
            }
        }
        for(DynamicGameObject p: to_remove){
            System.out.println("removing pig");
            world.destroyBody(p.body);
            p.isDead = true;
            System.out.println(p.getName() + ":" + p.isDead);
            System.out.println(pig_list);
            System.out.println("removed");
            pig_list.remove(p);
        }
        to_remove.clear();
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uistage);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(pausestage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.SPACE){
            System.out.println("ahuidahdad");
            if(lastbird != null){
                ArrayList<Bird> yo = lastbird.ability();
                if(lastbird instanceof Bluebird){
                    System.out.println("ability returned"+yo);
//                    bird_list.addAll(yo);
                    stage.addActor(yo.get(0)); stage.addActor(yo.get(1));
//                    System.out.println(bird_list);
                }
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    public boolean within_bounds(Vector2 pos){
        return slingbound.z <= pos.x && pos.x <= slingbound.x && slingbound.w <= pos.y && pos.y <= slingbound.y;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldPos = screenToWorld(screenX, screenY);
        if(!within_bounds(worldPos) || bird_list.isEmpty()) {
            System.out.println("hi");
            return false;
        }
        Body body = getBodyAt(worldPos);
        if(body == null) return false;
        if(body != slingbody) body.setType(BodyDef.BodyType.DynamicBody);
        lastBody = body;
        if (body != ground /*&& body != stone && body != wood && body != ice && body != slingbody*/) {
            MouseJointDef jointDef = new MouseJointDef();
            jointDef.bodyA = ground;
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
        if(bird_list.isEmpty()) return false;
        if (mouseJoint != null) {
            float boundx = Math.max(Math.min(slingbound.x, worldPos.x), slingbound.z);
            float boundy = Math.max(Math.min(slingbound.y, worldPos.y), slingbound.w);
            mouseJoint.setTarget(new Vector2(boundx, boundy)); // Update the target position
            System.out.println("target = "+mouseJoint.getTarget());
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 worldPos = screenToWorld(screenX, screenY);
        if(bird_list.isEmpty()) return false;
        Body b = getBodyAt(worldPos);
        Bird bb = null;
        if(b != null){
            System.out.println("here");
            if (mouseJoint != null) {
                world.destroyJoint(mouseJoint); // Remove the joint
                mouseJoint = null;
            }
            for(Bird temp: bird_list){
                if(temp.body.equals(b)){
                    bb = temp;
                    System.out.println("bb is "+bb);
                }
            }
            if(bb==null) return false;
            if(!within_bounds(worldPos)){
                assert bb != null;
                if(bb.on){
                    b.setTransform(slingbody.getPosition(), 0);
                    b.setType(BodyDef.BodyType.StaticBody);
                }
                return false;
            }
            bb.shot = true;
            bb.body.setFixedRotation(false);
            bb.body.setAngularDamping(2.0f);
            lastbird = bird_list.getLast();
            bird_list.remove(bird_list.getLast());
            b.applyLinearImpulse(new Vector2(3 * -(worldPos.x - slingbody.getPosition().x), 3 * -(worldPos.y - slingbody.getPosition().y)), b.getWorldCenter(), true);
            return true;
        }
        else {
            if(lastBody == null) return false;
            for(Bird t: bird_list){
                if(t.body.equals(lastBody)){
                    System.out.println(t.getClass().getSimpleName());
                    if(!t.shot){
                        lastBody.setTransform(slingbody.getPosition(), 0);
                        lastBody.setType(BodyDef.BodyType.StaticBody);
                    }
                }
            }
            if(mouseJoint!= null)world.destroyJoint(mouseJoint); // Remove the joint
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
    private Object getObjectAt(Body body){
        for(Bird b: bird_list){
            if(b.body.equals(body)) return b;
        }
        for(Material b: mat_list){
            if(b.body.equals(body)) return b;
        }
        for(Pig b: pig_list){
            if(b.body.equals(body)) return b;
        }
        return null;
    }

    public void save_gamestate(){
        gameState = new GameState();
        gameState.score = this.score;

        for(Bird b: bird_list){
            GameState.GameObjectState g = new GameState.GameObjectState();
            g.type =  b.getName();
            g.x = b.body.getPosition().x;
            g.y = b.body.getPosition().y;
            g.vx = b.body.getLinearVelocity().x;
            g.vy = b.body.getLinearVelocity().y;
            g.sx = b.getWidth();
            g.sy = b.getHeight();
            g.angle = b.body.getAngle();
            gameState.birds.add(g);
            System.out.println(g.type);
        }
        for(Material b: mat_list){
            GameState.GameObjectState g = new GameState.GameObjectState();
            g.type =  b.getName();
            g.x = b.body.getPosition().x;
            g.y = b.body.getPosition().y;
            g.vx = b.body.getLinearVelocity().x;
            g.vy = b.body.getLinearVelocity().y;
            g.sx = b.getWidth();
            g.sy = b.getHeight();
            g.angle = b.body.getAngle();
            gameState.materials.add(g);
            System.out.println(g.type);
        }
        for(Pig p : pig_list){
            GameState.GameObjectState g = new GameState.GameObjectState();
            g.type = p.getName();g.x = p.body.getPosition().x;
            g.y = p.body.getPosition().y;
            g.vx = p.body.getLinearVelocity().x;
            g.vy = p.body.getLinearVelocity().y;
            g.sx = p.getWidth();
            g.sy = p.getHeight();
            g.angle = p.body.getAngle();
            gameState.pigs.add(g);
            System.out.println(g.type);
        }
        // for pigs as well and health score etc

    }

    public void save_game(String filepathh){
        save_gamestate();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepathh))) {
            out.writeObject(gameState);
            System.out.println("Game state saved successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void load_game(String filepathh){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepathh))){
            gameState =(GameState) in.readObject();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.score = gameState.score;
        bird_list.clear();
        mat_list.clear();
        pig_list.clear();

        for(GameState.GameObjectState b: gameState.birds){
            Bird bird = null;
            if(Objects.equals(b.type, "Redbird")) bird = new Redbird(new Vector2(b.x, b.y), world);
            else if(Objects.equals(b.type, "Yellowbird")) bird = new Yellowbird(new Vector2(b.x, b.y), world);
            else bird = new Bluebird(new Vector2(b.x, b.y), world);
            bird.setSize(b.sx, b.sy);
            Body body = bird.body;
            body.setTransform(b.x, b.y, b.angle);
            body.setLinearVelocity(b.vx, b.vy);

            bird_list.add(bird);
        }
        for(GameState.GameObjectState b: gameState.materials){
            Material mat = null;
            if(Objects.equals(b.type, "icelog")) mat = new Ice(new Vector2(b.x, b.y), world);
            else if(Objects.equals(b.type, "woodlog")) mat = new Wood(new Vector2(b.x, b.y), world);
            else mat = new Stone(new Vector2(b.x, b.y), world);
            mat.setSize(b.sx, b.sy);
            Body body = mat.body;
            body.setTransform(b.x, b.y, b.angle);
            body.setLinearVelocity(b.vx, b.vy);
            mat_list.add(mat);
        }
        for(GameState.GameObjectState p : gameState.pigs){
            Pig pig = null;
            if(Objects.equals(p.type, "KingPig")) {pig = new KingPig(new Vector2(p.x, p.y), world); System.out.println("KingPig");}
            else if(Objects.equals(p.type, "PeasantPig")) pig = new PeasantPig(new Vector2(p.x, p.y), world);
            else pig = new SoldierPig(new Vector2(p.x, p.y), world);
            pig.setSize(p.sx, p.sy);
            Body body = pig.body;
            body.setTransform(p.x, p.y, p.angle);
            body.setLinearVelocity(p.vx, p.vy);
            pig_list.add(pig);
        }
        System.out.println("load done");
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameScore;
import birds.angry.GameSprites.*;
import birds.angry.GameState;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
     ArrayList<Bird> bird_list, bird_shot;
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
     final short PLANET = 16;
     MouseJointDef JointDef;
     MouseJoint mouseJoint;
     Vector4 slingbound;
     GameState gameState;
     boolean pause_b = false;
     Image pausebg;
     Button restart, save;
     String filepath;
     ArrayList<DynamicGameObject> to_remove;
     int score, high_score;
     public Bird lastbird;
     TextButton score_b;
     ArrayList<Body> changetype;

    public LevelScreen(Game game, String filepath) {
        super(game);
        mouseJoint = null;
        gameState = new GameState();
        to_remove = new ArrayList<>();
        this.filepath = filepath;
        stage = new Stage(new FitViewport(1600/ PPM,900/ PPM));
        uistage = new Stage(new FitViewport(1600,900));
        pausestage = new Stage(new FitViewport(1600,900));
        score = 0;
        uistage.setDebugAll(false);
        pausestage.setDebugAll(false);
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
        bird_shot = new ArrayList<>();
        changetype = new ArrayList<>();

        batch = new SpriteBatch();
        pause = new TextButton("pause", skin);
        pause.setPosition(50, 50);

        uistage.addActor(pause);
//        uistage.addActor(score_b);

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

                if ((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == GROUND) || (fa.getFilterData().categoryBits == GROUND && fb.getFilterData().categoryBits == BIRD)) {
                    System.out.println("Bird hit the ground");
                }
                if(fa.getFilterData().categoryBits==PLANET || fb.getFilterData().categoryBits==PLANET){
                    System.out.println("planet hit");
                }
                if ((fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == OBSTACLE)) {
                    System.out.println("obj hit the obj");
                }
                if ((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == BIRD)) {
                    System.out.println("Bird hit the bird");
                }
                if ((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == OBSTACLE) || (fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == BIRD)) {
                    System.out.println("Bird hit the obstacle");
                    try {
                        Material hitmat = (Material) getObjectAt(fa.getBody());
                        if(hitmat.body.getType()== BodyDef.BodyType.StaticBody) changetype.add(hitmat.body);
                        hitmat.health -= 1;
                        if (hitmat.health <= 0) {
                            to_remove.add(hitmat);
                            score += hitmat.score;
                        }
                    } catch (Exception e) {
                        Material hitmat = (Material) getObjectAt(fb.getBody());
                        if(hitmat.body.getType()== BodyDef.BodyType.StaticBody) changetype.add(hitmat.body);
                        hitmat.health -= 1;
                        if (hitmat.health <= 0) {
                            to_remove.add(hitmat);
                            score += hitmat.score;
                        }
                    }

                }
                if ((fa.getFilterData().categoryBits == BIRD && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == BIRD)) {
                    System.out.println("Bird hit the pig");
                    try {
                        Pig hitpig = (Pig) getObjectAt(fa.getBody());
                        if (hitpig.body.getType() == BodyDef.BodyType.StaticBody) changetype.add(hitpig.body);
//                        if(hitpig.body.getType()!=BodyDef.BodyType.DynamicBody)hitpig.body.setType(BodyDef.BodyType.DynamicBody);
                        hitpig.health -= 1;
                        System.out.println("pig hit a");
                        if (hitpig.health <= 0) {
                            score += hitpig.score;
                            to_remove.add(hitpig);
                        }
                    } catch (Exception e) {
                        Pig hitpig = (Pig) getObjectAt(fb.getBody());
                        if (hitpig.body.getType() == BodyDef.BodyType.StaticBody) changetype.add(hitpig.body);
//                        if(hitpig.body.getType()!=BodyDef.BodyType.DynamicBody)hitpig.body.setType(BodyDef.BodyType.DynamicBody);
                        hitpig.health -= 1;
                        System.out.println("pig hit b");
                        if (hitpig.health <= 0) {
                            score += hitpig.score;
                            to_remove.add(hitpig);
                        }
                    }
                }
                if ((fa.getFilterData().categoryBits == OBSTACLE && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == OBSTACLE)) {
                    System.out.println("obj hit the pig");
                    DynamicGameObject a, b;
                    try {
                        a = (Pig) getObjectAt(fa.getBody());
                        if(a.body.getType()== BodyDef.BodyType.StaticBody) changetype.add(a.body);
//                            a.body.setType(BodyDef.BodyType.DynamicBody);
                        b = (Material) getObjectAt(fb.getBody());
                        if(b.body.getType()== BodyDef.BodyType.StaticBody) changetype.add(b.body);
//                            b.body.setType(BodyDef.BodyType.DynamicBody);
                        a.health -= 1;
                        b.health -= 1;
                        if (a.health <= 0) {
                            to_remove.add(a);
                            score += a.score;
                        }
                        if (b.health <= 0) {
                            to_remove.add(b);
                            score += b.score;
                        }
                    } catch (Exception e) {
                        try {
                            a = (Material) getObjectAt(fa.getBody());
                            if(a.body.getType()== BodyDef.BodyType.StaticBody) changetype.add(a.body);
//                                a.body.setType(BodyDef.BodyType.DynamicBody);
                            b = (Pig) getObjectAt(fb.getBody());
                            if(b.body.getType()== BodyDef.BodyType.StaticBody) changetype.add(b.body);
//                                b.body.setType(BodyDef.BodyType.DynamicBody);
                            a.health -= 1;
                            b.health -= 1;
                            if (a.health <= 0) {
                                to_remove.add(a);
                                score += a.score;
                            }
                            if (b.health <= 0) {
                                to_remove.add(b);
                                score += b.score;
                            }
                        } catch (Exception ee) {
                            System.out.println("exception ??");
                        }
                    }
                }
                if ((fa.getFilterData().categoryBits == GROUND && fb.getFilterData().categoryBits == PIG) || (fa.getFilterData().categoryBits == PIG && fb.getFilterData().categoryBits == GROUND)) {
                    System.out.println("Pig hit the ground");
                    try {
                        Pig hitpig = (Pig) getObjectAt(fa.getBody());
                        float downvelocity = hitpig.body.getLinearVelocity().y;
                        System.out.println("downvel = " + downvelocity);
                        if (downvelocity <= -2.5) hitpig.health -= 1;
                        if (hitpig.health <= 0) {
                            score += hitpig.score;
                            to_remove.add(hitpig);
                        }
                    } catch (Exception e) {
                        Pig hitpig = (Pig) getObjectAt(fb.getBody());
                        float downvelocity = hitpig.body.getLinearVelocity().y;
                        System.out.println("downvel = " + downvelocity);
                        if (downvelocity <= -2.5) hitpig.health -= 1;
                        if (hitpig.health <= 0) {
                            score += hitpig.score;
                            to_remove.add(hitpig);
                        }
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

        for(Bird b: bird_list){
            stage.addActor(b);
        }
        for(Material b: mat_list){
            stage.addActor(b);
        }
        for(Pig p: pig_list){
            if(p.body.getPosition().x < 16 + p.getWidth() / 2) stage.addActor(p);

            else {
                to_remove.add(p);
//                world.destroyBody(p.body);
            }
        }
        for(Bird b : bird_shot){
            stage.addActor(b);
        }
        for(DynamicGameObject p: to_remove){
            System.out.println("removing pig");
            world.destroyBody(p.body);
            p.isDead = true;
            this.score += p.score;
            System.out.println(p.getName() + ":" + p.isDead);
            System.out.println(pig_list);
            System.out.println("removed");
            pig_list.remove(p);
        }
        to_remove.clear();
        for(Body b : changetype){
            b.setType(BodyDef.BodyType.DynamicBody);
        }
        score_b = new TextButton(String.format("Score: %d", score), skin);
        score_b.setPosition(1400,830);
        uistage.addActor(score_b);
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
                    bird_shot.addAll(yo);
                    stage.addActor(yo.get(0)); stage.addActor(yo.get(1));
//                    System.out.println(bird_list);
                }
            }
        }
        if(keycode == Input.Keys.W){
            game.setScreen(new WinScreen(game, 1, 1));
        }
        if(keycode == Input.Keys.L){
            game.setScreen(new LoseScreen(game));
        }
        if(keycode==Input.Keys.NUM_1){
            System.out.println("key pressed");
            for(Bird b : bird_list){
                if(b instanceof Redbird) b.nextTex();
            }
        }
        if(keycode==Input.Keys.NUM_2){
            System.out.println("key pressed");
            for(Bird b : bird_list){
                if(b instanceof Bluebird) b.nextTex();
            }
        }
        if(keycode==Input.Keys.NUM_3){
            System.out.println("key pressed");
            for(Bird b : bird_list){
                if(b instanceof Yellowbird) b.nextTex();
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
        if(bird_list.isEmpty()) {
            System.out.println("hi");
            return false;
        }
        Body body = getBodyAt(worldPos);
        Bird bb;
        if(body == null) return false;
        try {
            bb = (Bird) getObjectAt(body);
        } catch (ClassCastException e){
            return true;
        }
        if(bb == null) return false;
        if(!bb.on){
            bird_list.remove(bb);
            bird_list.getLast().on = false;
            bird_list.getLast().body.setTransform(bb.body.getPosition(), 0);
            bird_list.getLast().body.setType(BodyDef.BodyType.DynamicBody);
            bird_list.add(bb);
            bb.body.setType(BodyDef.BodyType.StaticBody);
            return false;
        }
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
        if(b!=null && b.getType()!=BodyDef.BodyType.DynamicBody && b.getFixtureList().get(0).getFilterData().categoryBits != GROUND) b.setType(BodyDef.BodyType.DynamicBody);
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
            bird_shot.add(bird_list.getLast());
            bird_list.remove(bird_list.getLast());
            b.applyLinearImpulse(new Vector2(3 * -(b.getPosition().x - slingbody.getPosition().x), 3 * -(b.getPosition().y - slingbody.getPosition().y)), b.getWorldCenter(), true);
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
        for(Bird b : bird_shot){
            GameState.GameObjectState g = new GameState.GameObjectState();
            g.type =  b.getName();
            g.x = b.body.getPosition().x;
            g.y = b.body.getPosition().y;
            g.vx = b.body.getLinearVelocity().x;
            g.vy = b.body.getLinearVelocity().y;
            g.sx = b.getWidth();
            g.sy = b.getHeight();
            g.angle = b.body.getAngle();
            gameState.birds_shot.add(g);
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
//        try {
//            Field high = gameState.getClass().getDeclaredField("high_score");
//            this.high_score = gameState.high_score;
//        } catch (NoSuchFieldException e) {
//            System.out.println("no high score");
//        }
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
        for(GameState.GameObjectState b: gameState.birds_shot){
            Bird bird = null;
            if(Objects.equals(b.type, "Redbird")) bird = new Redbird(new Vector2(b.x, b.y), world);
            else if(Objects.equals(b.type, "Yellowbird")) bird = new Yellowbird(new Vector2(b.x, b.y), world);
            else bird = new Bluebird(new Vector2(b.x, b.y), world);
            bird.setSize(b.sx, b.sy);
            Body body = bird.body;
            body.setTransform(b.x, b.y, b.angle);
            body.setLinearVelocity(b.vx, b.vy);

            bird_shot.add(bird);
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

    public Vector2 trajectory(Vector2 startingPosition, Vector2 startingVelocity, float n){
        float t = 4 / 60.0f; // seconds per time step (at 60fps)
        Vector2 stepVelocity = new Vector2(t * startingVelocity.x, t * startingVelocity.y); // m/s
        Vector2 stepGravity = new Vector2(t * t * world.getGravity().x, t * t * world.getGravity().y); // m/s/s

        Vector2 finale = new Vector2();
        finale.x = startingPosition.x + n * stepVelocity.x + 0.5f * (n * n + n) * stepGravity.x;
        finale.y = startingPosition.y + n * stepVelocity.y + 0.5f * (n * n + n) * stepGravity.y;
        return finale;
    }

    Vector2 custom_grav(Body body) {
        Vector2 centre = new Vector2(8+4.2f*grid_size, 4.44f);
        Vector2 bodyPosition = body.getPosition();
        Vector2 forceDirection = centre.cpy().sub(bodyPosition).nor(); // Direction to center
//        float distance = centre.dst(bodyPosition); // Distance to center
        float strength = 9.8f * body.getMass(); // Inverse square law for force strength

        // Scale by strength
        return forceDirection.scl(strength);
    }

    void drawTrajectory(ShapeRenderer shapeRenderer, Body body, Vector2 init_velo, int step) {
        Vector2 position = body.getPosition();
        // Current velocity
        float mass = body.getMass();
        float t = 4 / 60f;
        // Compute the gravity force
        Vector2 gravity = custom_grav(body);
        Vector2 acceleration = gravity.scl(1 / mass); // a = F/m

        for(int i = 0; i < step; i++) {
            init_velo.add(acceleration.scl((float) t)); // v = v0 + a * t
            position.add(init_velo.cpy().scl((float) t)); // p = p0 + v * t
        }
        shapeRenderer.circle(position.x * PPM, position.y * PPM, 5);

    }

    public void win_condition(int level){
        System.out.println(AngryBirds.score);
        AngryBirds.score.set(level - 1, Math.max(this.score, AngryBirds.score.get(level - 1)));
        GameScore gs = new GameScore();
        gs.high_scores = AngryBirds.score;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("assets/save_slot" + AngryBirds.save_slot + "/scores.ser"))) {
            out.writeObject(gs);
            System.out.println("score saved successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        game.setScreen(new WinScreen(game, level, this.score));
    }
}

package birds.angry.Screens;

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

public class Level2 extends LevelScreen {
    private Bird redbird, bluebird, yellowbird;
    private PeasantPig ppig;
    private KingPig kingPig;
    private SoldierPig soldierPig;
    private Wood woodlog;
    private Ice icelog;
    private Stone stonelog;

    public Level2(Game game, String filepath) {
        super(game, filepath);
        //----
        //ground
        bodyDef.position.set(0, 1);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ground = world.createBody(bodyDef);

        PolygonShape p = new PolygonShape();
        p.setAsBox(16,1);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = GROUND;
        fixtureDef.filter.maskBits = -1;
        ground.createFixture(fixtureDef);

        //slingbody
        bodyDef.position.set(3.5f, 3.5f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        slingbody = world.createBody(bodyDef);

        p.setAsBox(0.01f,0.01f);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.maskBits = 0;
        slingbody.createFixture(fixtureDef);
        p.dispose();

        //load background
        background = Assets.level2bg;

        //change slingshot position if needed
        slingshot.setPosition(3, 2);

        slingbound.x = slingshot.getPosition().x + slingshot.getWidth();
        slingbound.y = slingshot.getPosition().y + slingshot.getHeight();
        slingbound.z = slingbound.x - 1.5f * slingshot.getWidth();
        slingbound.w = slingbound.y - 1.5f;
        System.out.println("aaaaaa");
        //----
//        Box2D.init();
//        world = new World(new Vector2(0, -9.81f), true);
//        bird_list = new ArrayList<>();
//        mat_list = new ArrayList<>();
//        pig_list = new ArrayList<>();
////        load_game();
//        stage = new Stage(new FitViewport(1600/ PPM,900 / PPM));
//        uistage = new Stage(new FitViewport(1600,900));
//        pausestage = new Stage(new FitViewport(1600,900));
//        uistage.setDebugAll(false);
//        stage.setDebugAll(false);
//        slingbound = new Vector4();
//        grid_size = 0.5f;
//        dbg = new Box2DDebugRenderer();
//        bodyDef = new BodyDef();
//        fixtureDef = new FixtureDef();
//
////        stone = createObject(new Vector2(5,5));
////        ice = createObject(new Vector2(6,5));
////        wood = createObject(new Vector2(7,5));
//
//        bodyDef.position.set(0, 1);
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        ground = world.createBody(bodyDef);
//
////        fixtureDef.isSensor = false;
////        fixtureDef.restitution = 0.9f;
////        fixtureDef.friction = 0.2f;
////        fixtureDef.filter.maskBits = -1;
//        PolygonShape p = new PolygonShape();
//        p.setAsBox(16,1);
//
//        fixtureDef.shape = p;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.categoryBits = GROUND;
//        fixtureDef.filter.maskBits = -1;
//        ground.createFixture(fixtureDef);
//
//        bodyDef.position.set(3.5f, 3.5f);
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        slingbody = world.createBody(bodyDef);
//
////        fixtureDef.isSensor = false;
////        fixtureDef.restitution = 0.9f;
////        fixtureDef.friction = 0.2f;
////        fixtureDef.filter.maskBits = -1;
//        p.setAsBox(0.01f,0.01f);
//
//        fixtureDef.shape = p;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.maskBits = 0;
//        slingbody.createFixture(fixtureDef);
//        p.dispose();
//        background = Assets.level2bg;
//        slingshot = new Slingshot(new Vector2(6*grid_size, 4*grid_size));
//        slingshot.setSize(2 * grid_size, 4 * grid_size);
//        slingbound = new Vector4();
//        slingbound.x = slingshot.getPosition().x + slingshot.getWidth();
//        slingbound.y = slingshot.getPosition().y + slingshot.getHeight();
//        slingbound.z = slingbound.x - 2 * slingshot.getWidth();
//        slingbound.w = slingbound.y - 1.5f;
//        stage.addActor(slingshot);
//        batch = new SpriteBatch();
//        pause = new TextButton("pause", skin);
//        pause.setPosition(50, 50);
////        pause.setTouchable(Touchable.enabled);
//        uistage.addActor(pause);
//        pause.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Pause");
//                game.setScreen(new Pause(game, game.getScreen()));
//
//            }
//        });

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

        //----------------------
//        redbirdBody = bird_list.get(2).body;
//        bluebirdBody = bird_list.get(0).body;
//        yellowbirdBody = bird_list.get(1).body;
        save.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                save_game("assets/save_slot1/level2_save.ser");
                pause_b = false;
            }
            return false;
        });

        restart.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                game.setScreen(new Level2(game, "assets/level/level2.ser"));
                pause_b = false;
            }
            return false;
        });

        load_game(filepath);

    }



    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(background, 0, 0, 1600, 900);
        batch.end();
        stage.act(delta);
        stage.draw();
        uistage.act(delta);
        uistage.draw();
        if(pause_b){
            pausestage.act(delta);
            pausestage.draw();
        }
        world.step(1/60f, 6,2);
        dbg.render(world, stage.getCamera().combined);

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


    public void dispose(){
        super.dispose();
        this.stage.dispose();
        world.dispose();
        dbg.dispose();
    }
}

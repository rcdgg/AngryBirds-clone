package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameScore;
import birds.angry.GameSprites.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Level2 extends LevelScreen{

    private Bird redbird, bluebird, yellowbird;
    private PeasantPig ppig;
    private KingPig kingPig;
    private SoldierPig soldierPig;
    private Wood woodlog, woodlog2, woodlog3;
    private Ice icelog;
    private Stone stonelog;


    public Level2(Game game, String filepath) {
        super(game, filepath);

        //ground
        bodyDef.position.set(0, 0.5f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ground = world.createBody(bodyDef);

        PolygonShape p = new PolygonShape();
        p.setAsBox(16,0.55f);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = GROUND;
        fixtureDef.filter.maskBits = -1;
        ground.createFixture(fixtureDef);

        // ground 2
        bodyDef.position.set(0, 1.77f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground2 = world.createBody(bodyDef);
        PolygonShape p2 = new PolygonShape();
        p2.setAsBox(15.62f*grid_size,0.65f);
        fixtureDef.shape = p2;
        ground2.createFixture(fixtureDef);

        //ground 3
        bodyDef.position.set(0f, 0f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground3 = world.createBody(bodyDef);
        PolygonShape p3 = new PolygonShape();
        p3.setAsBox(5f*grid_size, 11.5f*grid_size);
        fixtureDef.shape = p3;
        ground3.createFixture(fixtureDef);
        //ground 4
        bodyDef.position.set(ground3.getPosition().x+2.8f, 1.45f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground4 = world.createBody(bodyDef);
        p.setAsBox(0.35f, 3.6f);
        fixtureDef.shape = p;
        ground4.createFixture(fixtureDef);
        //ground 5
        bodyDef.position.set(ground4.getPosition().x+0.35f+0.35f, 1.45f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground5 = world.createBody(bodyDef);
        p.setAsBox(0.35f, 2.9f);
        fixtureDef.shape = p;
        ground5.createFixture(fixtureDef);
        //ground 6
        bodyDef.position.set(ground5.getPosition().x+0.35f+0.35f, 1.47f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground6 = world.createBody(bodyDef);
        p.setAsBox(0.35f, 2.2f);
        fixtureDef.shape = p;
        ground6.createFixture(fixtureDef);
        //ground 7
        bodyDef.position.set(ground6.getPosition().x+0.35f+0.33f, 1.47f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground7 = world.createBody(bodyDef);
        p.setAsBox(0.39f, 1.5f);
        fixtureDef.shape = p;
        ground7.createFixture(fixtureDef);
        //ground 8
        bodyDef.position.set(ground2.getPosition().x+14.9f, ground2.getPosition().y-0.1f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground8 = world.createBody(bodyDef);
        p.setAsBox(4.3f, 0.72f);
        fixtureDef.shape = p;
        ground8.createFixture(fixtureDef);
        //ground 9
        bodyDef.position.set(ground3.getPosition().x+14.6f, ground3.getPosition().y+1.5f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground9 = world.createBody(bodyDef);
        p.setAsBox(2f, 4.2f);
        fixtureDef.shape = p;
        ground9.createFixture(fixtureDef);
        //ground 10
        bodyDef.position.set(15.7f*grid_size, 12.3f*grid_size);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground10 = world.createBody(bodyDef);
        p.setAsBox(5.5f*grid_size/2 - 0.1f, grid_size/2);
        fixtureDef.shape = p;
        ground10.createFixture(fixtureDef);
        //slingbody
        bodyDef.position.set(3 * grid_size, 15f * grid_size);
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
        slingshot = new Slingshot(new Vector2(2*grid_size, 11.5f*grid_size));
        slingshot.setSize(2 * grid_size, 4 * grid_size);
        stage.addActor(slingshot);
        slingbound.x = slingshot.getPosition().x + slingshot.getWidth() - 0.5f;
        slingbound.y = slingshot.getPosition().y + slingshot.getHeight() + 0.5f;
        slingbound.z = slingbound.x - 2 * slingshot.getWidth();
        slingbound.w = slingbound.y - 1.5f - 0.5f;
        //---------------------
//        redbird = new Redbird(new Vector2(2.5f*grid_size,5), world);
//        redbird.setSize(2 * bird_size, 2 * bird_size);
////        redbird.setPosition(3, ground3.getPosition().y+7f);
//
////        redbird.setPosition(new Vector2(4*grid_size, 4*grid_size));
//
//        yellowbird = new Yellowbird(new Vector2(1f*grid_size, 5), world);
//        yellowbird.setSize(2 * bird_size, 2 * bird_size);
//
//        bluebird = new Bluebird(new Vector2(0.3f, 5), world);
//        bluebird.setSize(2.5f * bird_size, 2 * bird_size);
//
//        bird_list.add(redbird); bird_list.add(yellowbird);
//        bird_list.add(bluebird);
//
//        woodlog = new Wood(new Vector2(15.5f*grid_size, 14.5f), world);
//        woodlog.setSize(2 * obj_size, 20 * obj_size);;
//        woodlog2 = new Wood(new Vector2(17.5f*grid_size, 14.5f), world);
//        woodlog2.setSize(2 * obj_size, 20 * obj_size);
//        woodlog3 = new Wood(new Vector2(16.5f*grid_size, 14.5f + woodlog.getHeight()), world);
//        woodlog3.setSize(2 * obj_size, 20 * obj_size);
//        woodlog3.body.setTransform(woodlog3.body.getPosition().x, woodlog3.body.getPosition().y, (3.14f/2));
////        woodlog2.body.setTransform(woodlog2.body.getPosition().x + woodlog2.getHeight()/2, woodlog2.body.getPosition().y, (3.14f/2));
//
//        icelog = new Ice(new Vector2(15.3f*grid_size, 6f), world);
//        icelog.setSize(2 * obj_size, 20 * obj_size);
//        icelog.body.setTransform(icelog.body.getPosition().x, icelog.body.getPosition().y, (3.14f/2));
////        icelog2 = new Ice(new Vector2(14*grid_size, icelog.getHeight()+3), world);
////        icelog2.setSize(2 * obj_size, 20 * obj_size);
////        icelog2.body.setTransform(icelog2.body.getPosition().x + icelog2.getHeight()/2, icelog2.body.getPosition().y, (3.14f/2));
//
//        stonelog = new Stone(new Vector2(21.2f*grid_size, 6f), world);
//        stonelog.setSize(2 * obj_size, 20 * obj_size);
//        stonelog.body.setTransform(stonelog.body.getPosition().x, stonelog.body.getPosition().y, (3.14f/2));
//
//        mat_list.add(icelog); mat_list.add(woodlog); mat_list.add(stonelog);mat_list.add(woodlog2);mat_list.add(woodlog3);
////        mat_list.add(woodlog2);mat_list.add(icelog2);
//
//
//        soldierPig = new SoldierPig(new Vector2((16.5f)*grid_size, 14.5f*grid_size), world);
//        soldierPig.setSize(2 * bird_size, 2 * bird_size);
//        kingPig = new KingPig(new Vector2(27*grid_size, 13*grid_size), world);
//        kingPig.setSize(2 * bird_size, 2 * bird_size);
//        ppig = new PeasantPig(new Vector2(19.3f*grid_size, 3*grid_size), world);
//        ppig.setSize(2 * bird_size, 2 * bird_size);
//        pig_list.add(ppig); pig_list.add(kingPig); pig_list.add(soldierPig);
//
        //----------------------

        save.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                save_game("assets/save_slot"+ AngryBirds.save_slot +"/level2_save.ser");
//                save_game("assets/level/level2.ser");
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
        batch.draw(background, 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
        uistage.act(delta);
        uistage.draw();
        if(grid){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.GRAY);

            float gridSpacing = 50f;

            for (float y = 0; y < uistage.getHeight(); y += gridSpacing) {
                shapeRenderer.line(0, y, uistage.getWidth(), y);
            }

            for (float x = 0; x < uistage.getWidth(); x += gridSpacing) {
                shapeRenderer.line(x, 0, x, uistage.getHeight());
            }

            shapeRenderer.end();
        }
        if(!bird_list.isEmpty()){
            Vector2 worldPos = bird_list.getLast().body.getPosition();
            Vector2 init_velo = new Vector2(3 * -(worldPos.x - slingbody.getPosition().x), 3 * -(worldPos.y - slingbody.getPosition().y));
            init_velo.x *= 1 / bird_list.getLast().body.getMass();
            init_velo.y *= 1 / bird_list.getLast().body.getMass();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            for (int i = 0; i < 20; i++) {
                Vector2 temp = trajectory(worldPos, init_velo, i);
                shapeRenderer.circle(temp.x * PPM, temp.y * PPM, 5);
            }
            shapeRenderer.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.BLACK);
            for (int i = 0; i < 20; i++) {
                Vector2 temp = trajectory(worldPos, init_velo, i);
                shapeRenderer.circle(temp.x * PPM, temp.y * PPM, 5);
                shapeRenderer.circle(temp.x * PPM, temp.y * PPM, 4);
            }
            shapeRenderer.end();
        }
        if(pause_b){
            pausestage.act(delta);
            pausestage.draw();
        }
        else if(pig_list.isEmpty()){
            win_condition(2);
        }
        else if(bird_list.isEmpty() && lastBody.getLinearVelocity().equals(new Vector2(0,0))){
            game.setScreen(new LoseScreen(game));
        }else if(bird_list.isEmpty() && (lastBody.getPosition().x < 0 || lastBody.getPosition().y < 0 || lastBody.getPosition().x > 16)){
            game.setScreen(new LoseScreen(game));
        }
        else world.step(1/60f, 6,2);
        if(bird_list.isEmpty()) System.out.println(lastBody.getPosition().x + " - "+ lastBody.getPosition().y);
//        dbg.render(world, stage.getCamera().combined);
    }


    public void dispose(){
        super.dispose();
        this.stage.dispose();
        uistage.dispose();
        pausestage.dispose();
        world.dispose();
        dbg.dispose();
    }
}

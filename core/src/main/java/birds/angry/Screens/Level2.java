package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameSprites.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Level2 extends LevelScreen{

    private Bird redbird, bluebird, yellowbird;
    private PeasantPig ppig;
    private KingPig kingPig;
    private SoldierPig soldierPig;
    private Wood woodlog;
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
        p2.setAsBox(8.8f,0.72f);
        fixtureDef.shape = p2;
        ground2.createFixture(fixtureDef);
        //ground 3
        bodyDef.position.set(0f, 1.77f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground3 = world.createBody(bodyDef);
        PolygonShape p3 = new PolygonShape();
        p3.setAsBox(2.8f, 4.3f);
        fixtureDef.shape = p3;
        ground3.createFixture(fixtureDef);
        //ground 4
        bodyDef.position.set(ground3.getPosition().x+2.8f+0.35f, 1.77f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground4 = world.createBody(bodyDef);
        PolygonShape p4 = new PolygonShape();
        p4.setAsBox(0.35f, 3.6f);
        fixtureDef.shape = p4;
        ground4.createFixture(fixtureDef);
        //ground 5
        bodyDef.position.set(ground4.getPosition().x+0.35f+0.35f, 1.77f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground5 = world.createBody(bodyDef);
        PolygonShape p5 = new PolygonShape();
        p5.setAsBox(0.35f, 2.9f);
        fixtureDef.shape = p5;
        ground5.createFixture(fixtureDef);
        //ground 6
        bodyDef.position.set(ground5.getPosition().x+0.35f+0.35f, 1.77f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground6 = world.createBody(bodyDef);
        PolygonShape p6 = new PolygonShape();
        p6.setAsBox(0.35f, 2.2f);
        fixtureDef.shape = p6;
        ground6.createFixture(fixtureDef);
        //ground 7
        bodyDef.position.set(ground6.getPosition().x+0.35f+0.35f, 1.77f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground7 = world.createBody(bodyDef);
        PolygonShape p7 = new PolygonShape();
        p7.setAsBox(0.39f, 1.5f);
        fixtureDef.shape = p7;
        ground7.createFixture(fixtureDef);
        //ground 8
        bodyDef.position.set(ground2.getPosition().x+16f, ground2.getPosition().y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground8 = world.createBody(bodyDef);
        PolygonShape p8 = new PolygonShape();
        p8.setAsBox(4.3f, 0.72f);
        fixtureDef.shape = p8;
        ground8.createFixture(fixtureDef);
        //ground 9
        bodyDef.position.set(ground3.getPosition().x+16f, ground3.getPosition().y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground9 = world.createBody(bodyDef);
        PolygonShape p9 = new PolygonShape();
        p9.setAsBox(2f, 4.2f);
        fixtureDef.shape = p9;
        ground9.createFixture(fixtureDef);
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
        slingshot = new Slingshot(new Vector2(6*grid_size, 2.78f));
        slingshot.setSize(2 * grid_size, 4 * grid_size);
        stage.addActor(slingshot);
        slingshot.setPosition(3, 2);

        slingbound.x = slingshot.getPosition().x + slingshot.getWidth();
        slingbound.y = slingshot.getPosition().y + slingshot.getHeight();
        slingbound.z = slingbound.x - 1.5f * slingshot.getWidth();
        slingbound.w = slingbound.y - 1.5f;
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
//        woodlog = new Wood(new Vector2(11*grid_size, ground.getPosition().y + 2), world);
//        woodlog.setSize(2 * obj_size, 20 * obj_size);;
//        woodlog2 = new Wood(new Vector2(11*grid_size, woodlog.getHeight()+3), world);
//        woodlog2.setSize(2 * obj_size, 20 * obj_size);
//        woodlog2.body.setTransform(woodlog2.body.getPosition().x + woodlog2.getHeight()/2, woodlog2.body.getPosition().y, (3.14f/2));
//
//        icelog = new Ice(new Vector2(14*grid_size, ground.getPosition().y+2), world);
//        icelog.setSize(2 * obj_size, 20 * obj_size);
//        icelog2 = new Ice(new Vector2(14*grid_size, icelog.getHeight()+3), world);
//        icelog2.setSize(2 * obj_size, 20 * obj_size);
//        icelog2.body.setTransform(icelog2.body.getPosition().x + icelog2.getHeight()/2, icelog2.body.getPosition().y, (3.14f/2));
//
//        stonelog = new Stone(new Vector2(17*grid_size, ground.getPosition().y+2), world);
//        stonelog.setSize(2 * obj_size, 20 * obj_size);
//
//        mat_list.add(icelog); mat_list.add(woodlog); mat_list.add(stonelog); mat_list.add(woodlog2);mat_list.add(icelog2);
//
//        ppig = new PeasantPig(new Vector2((12.5f)*grid_size, 5*grid_size), world);
//        ppig.setSize(2 * bird_size, 2 * bird_size);
//        kingPig = new KingPig(new Vector2(16*grid_size, 5*grid_size), world);
//        kingPig.setSize(2 * bird_size, 2 * bird_size);
//        soldierPig = new SoldierPig(new Vector2(19*grid_size, 5*grid_size), world);
//        soldierPig.setSize(2 * bird_size, 2 * bird_size);
//        pig_list.add(ppig); pig_list.add(kingPig); pig_list.add(soldierPig);

        //----------------------

        save.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                save_game("assets/save_slot"+ AngryBirds.save_slot +"/level2_save.ser");
                pause_b = false;
            }
            return false;
        });

        restart.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                game.setScreen(new Level1(game, "assets/level/level2.ser"));
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
        if(pause_b){
            pausestage.act(delta);
            pausestage.draw();
        }
        else if(pig_list.isEmpty()){
            game.setScreen(new WinScreen(game));
        }
        else if(bird_list.isEmpty() && lastBody.getLinearVelocity().x < 0.05f && lastBody.getLinearVelocity().x > -0.05f&&lastBody.getLinearVelocity().y < 0.05f&&lastBody.getLinearVelocity().y > -0.05f){
            game.setScreen(new LoseScreen(game));
        }else if(bird_list.isEmpty() && (lastBody.getPosition().x < 0 || lastBody.getPosition().y < 0 || lastBody.getPosition().x > 16 || lastBody.getPosition().y > 9)){
            game.setScreen(new LoseScreen(game));
        }
        else world.step(1/60f, 6,2);
        if(bird_list.isEmpty()) System.out.println(lastBody.getPosition().x + " - "+ lastBody.getPosition().y);
        dbg.render(world, stage.getCamera().combined);
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

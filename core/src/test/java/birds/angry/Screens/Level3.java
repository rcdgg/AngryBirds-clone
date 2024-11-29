package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameSprites.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Level3 extends LevelScreen {
    private Bird redbird, bluebird, yellowbird;
    private PeasantPig ppig;
    private KingPig kingPig;
    private SoldierPig soldierPig;
    private Wood woodlog, woodlog2;
    private Ice icelog, icelog2;
    private Stone stonelog;
    Vector2 centre;

    public Level3(Game game, String filepath) {
        super(game, filepath);
        //----
        world.setGravity(new Vector2(0,0));
        centre = new Vector2(8, 4.5f);
        //ground
        bodyDef.position.set(1.9f, 1.3f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ground = world.createBody(bodyDef);
        CircleShape cg = new CircleShape();
        PolygonShape p = new PolygonShape();
//        p.setAsBox(4,1.78f);
        cg.setRadius(6*grid_size);
        fixtureDef.shape = cg;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = PLANET;
        fixtureDef.filter.maskBits = -1;
        ground.createFixture(fixtureDef);

        // ground 2
//        bodyDef.position.set(11.2f, ground.getPosition().y + 2.08f);
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        Body ground2 = world.createBody(bodyDef);
//
//        p.setAsBox(2.8f,0.3f);
//        fixtureDef.shape = p;
//        fixtureDef.friction = 0.2f;
//        fixtureDef.filter.categoryBits = GROUND;
//        fixtureDef.filter.maskBits = -1;
//        ground2.createFixture(fixtureDef);

        // ground 3
        bodyDef.position.set(8, 4.5f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground3 = world.createBody(bodyDef);
        CircleShape cs = new CircleShape();
//        p.setAsBox(2f,2f);
        cs.setRadius(3*grid_size);
        fixtureDef.shape = cs;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = PLANET;
        fixtureDef.filter.maskBits = -1;
        ground3.createFixture(fixtureDef);

        //slingbody
        bodyDef.position.set(2.6f*grid_size, 12*grid_size);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        slingbody = world.createBody(bodyDef);

        p.setAsBox(0.01f,0.03f);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.maskBits = 0;
        slingbody.createFixture(fixtureDef);
//        slingbody.setTransform(5*grid_size, 12*grid_size, 0.349f);
        p.dispose();

        //load background
        background = Assets.level1bg;

        //change slingshot position if needed
//        slingshot.setPosition(3, 2.78f);
        slingshot = new Slingshot(new Vector2(2f*grid_size, 8.5f*grid_size));
        slingshot.setRotation(10);
        slingshot.setSize(2 * grid_size, 4 * grid_size);
        stage.addActor(slingshot);
        slingbound.x = slingshot.getPosition().x + slingshot.getWidth() + 1;
        slingbound.y = slingshot.getPosition().y + slingshot.getHeight()+0.5f;
        slingbound.z = slingbound.x - 2 * slingshot.getWidth()-0.5f;
        slingbound.w = slingbound.y - 1.5f-0.5f;
        System.out.println("aaaaaa");

        //---------------------
        float right = 4;
        float up = 2.8f;
        redbird = new Redbird(new Vector2(2.5f*grid_size,4), world);
        redbird.setSize(2 * bird_size, 2 * bird_size);
        redbird.body.setType(BodyDef.BodyType.StaticBody);

//        redbird.setPosition(new Vector2(4*grid_size, 4*grid_size));
        bluebird = new Bluebird(new Vector2(2.5f*grid_size, 4), world);
        bluebird.setSize(2.5f * bird_size, 2 * bird_size);
        bluebird.body.setType(BodyDef.BodyType.StaticBody);

        yellowbird = new Yellowbird(new Vector2(1f*grid_size, 3), world);
        yellowbird.setSize(2 * bird_size, 2 * bird_size);
        yellowbird.body.setType(BodyDef.BodyType.StaticBody);

        bird_list.add(redbird); bird_list.add(yellowbird); bird_list.add(bluebird);

        woodlog = new Wood(new Vector2(11*grid_size + right, ground.getPosition().y + up), world);
        woodlog.setSize(2 * obj_size, 20 * obj_size);;
        woodlog.body.setType(BodyDef.BodyType.StaticBody);
//        MassData md = woodlog.body.getMassData();
//        md.center.y = woodlog.getHeight()/16;
//        woodlog.body.setMassData(md);
        woodlog2 = new Wood(new Vector2(11*grid_size + right, ground.getPosition().y + woodlog.getHeight()+up), world);
        woodlog2.setSize(2 * obj_size, 20 * obj_size);
        woodlog2.body.setTransform(woodlog2.body.getPosition().x + woodlog2.getHeight()/2, woodlog2.body.getPosition().y, (3.14f/2));
        woodlog2.body.setType(BodyDef.BodyType.StaticBody);
//        MassData md2 = woodlog2.body.getMassData();
//        md.center.y = woodlog2.getHeight()/16;
//        woodlog2.body.setMassData(md2);

        icelog = new Ice(new Vector2(14*grid_size + right, ground.getPosition().y+up), world);
        icelog.setSize(2 * obj_size, 20 * obj_size);
        icelog.body.setType(BodyDef.BodyType.StaticBody);
        icelog2 = new Ice(new Vector2(14*grid_size + right, ground.getPosition().y+icelog.getHeight()+up), world);
        icelog2.setSize(2 * obj_size, 20 * obj_size);
        icelog2.body.setTransform(icelog2.body.getPosition().x + icelog2.getHeight()/2, icelog2.body.getPosition().y, (3.14f/2));
        icelog2.body.setType(BodyDef.BodyType.StaticBody);

        stonelog = new Stone(new Vector2(17*grid_size + right, ground.getPosition().y+up), world);
        stonelog.setSize(2 * obj_size, 20 * obj_size);
        stonelog.body.setType(BodyDef.BodyType.StaticBody);

        mat_list.add(icelog); mat_list.add(woodlog); mat_list.add(stonelog); mat_list.add(woodlog2);mat_list.add(icelog2);

        ppig = new PeasantPig(new Vector2((12.5f)*grid_size + right, ground.getPosition().y + up), world);
        ppig.setSize(2 * bird_size, 2 * bird_size);
        ppig.body.setType(BodyDef.BodyType.StaticBody);
        kingPig = new KingPig(new Vector2(16*grid_size + right, ground.getPosition().y + up), world);
        kingPig.setSize(2 * bird_size, 2 * bird_size);
        kingPig.body.setType(BodyDef.BodyType.StaticBody);
        soldierPig = new SoldierPig(new Vector2(19*grid_size + right, 5*grid_size + up), world);
        soldierPig.setSize(2 * bird_size, 2 * bird_size);
        soldierPig.body.setType(BodyDef.BodyType.StaticBody);
        pig_list.add(ppig); pig_list.add(kingPig); pig_list.add(soldierPig);

        for(Material m : mat_list){
            MassData md = m.body.getMassData();
            md.center.y = m.getHeight()/64;
            m.body.setMassData(md);
        }
        //----------------------

        save.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                save_game("assets/save_slot"+ AngryBirds.save_slot +"/level3_save.ser");
//                save_game("assets/level/level1.ser");
                pause_b = false;
            }
            return false;
        });

        restart.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                game.setScreen(new Level3(game, "assets/level/level3.ser"));
                pause_b = false;
            }
            return false;
        });

//        load_game(filepath);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        batch.begin();
//        batch.draw(background, 0, 0, 1600, 900);
//        batch.end();

        for(Bird b: bird_list){
            applyCentralGravity(b.body);
        }
        for(Bird b: bird_shot){
            applyCentralGravity(b.body);
        }
        for(Material b: mat_list){
            applyCentralGravity(b.body);
        }
        for(Pig p: pig_list){
            applyCentralGravity(p.body);
        }

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
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//            shapeRenderer.setColor(Color.WHITE);
//            for (int i = 0; i < 60; i++) {
//                Vector2 temp = trajectory(worldPos, init_velo, custom_grav(bird_list.getLast().body), i);
//                shapeRenderer.circle(temp.x * PPM, temp.y * PPM, 5);
//            }
//            shapeRenderer.end();
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//            shapeRenderer.setColor(Color.BLACK);
//            for (int i = 0; i < 15; i++) {
//                Vector2 temp = trajectory(worldPos, init_velo,custom_grav(bird_list.getLast().body), i);
//                shapeRenderer.circle(temp.x * PPM, temp.y * PPM, 5);
//                shapeRenderer.circle(temp.x * PPM, temp.y * PPM, 4);
//            }
//            shapeRenderer.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for(int i = 0; i < 15; i++) drawTrajectory(shapeRenderer, bird_list.getLast().body, init_velo, i); // Example: 100 steps, 0.1s per step
            shapeRenderer.end();
        }

        if(pause_b){
            pausestage.act(delta);
            pausestage.draw();
        }
        else if(pig_list.isEmpty()){
            win_condition(3);
        }
        else if(bird_list.isEmpty() && lastBody.getLinearVelocity().equals(new Vector2(0,0))){
            game.setScreen(new LoseScreen(game));
        }else if(bird_list.isEmpty() && (lastBody.getPosition().x < 0 || lastBody.getPosition().y < 0 || lastBody.getPosition().x > 16)){
            game.setScreen(new LoseScreen(game));
        }
        else world.step(1/120f, 6,2);
        dbg.render(world, stage.getCamera().combined);

    }

    private void applyCentralGravity(Body body) {
        body.applyForceToCenter(custom_grav(body), true); // Apply force to body center
    }


    public void dispose(){
        super.dispose();
        this.stage.dispose();
        uistage.dispose();
        world.dispose();
        dbg.dispose();
    }
}

package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameSprites.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Level1 extends LevelScreen {
    private Bird redbird, bluebird, yellowbird;
    private PeasantPig ppig;
    private KingPig kingPig;
    private SoldierPig soldierPig;
    private Wood woodlog, woodlog2;
    private Ice icelog, icelog2;
    private Stone stonelog;

    public Level1(Game game, String filepath) {
        super(game, filepath);
        //----
        //ground
        bodyDef.position.set(0, 1);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ground = world.createBody(bodyDef);

        PolygonShape p = new PolygonShape();
        p.setAsBox(16,1.78f);
        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = GROUND;
        fixtureDef.filter.maskBits = -1;
        ground.createFixture(fixtureDef);

        // ground 2
        bodyDef.position.set(11.2f, ground.getPosition().y + 2.08f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body ground2 = world.createBody(bodyDef);

        p.setAsBox(2.8f,0.3f);
        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = GROUND;
        fixtureDef.filter.maskBits = -1;
        ground2.createFixture(fixtureDef);

        //slingbody
        bodyDef.position.set(3.5f, 4.28f);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        slingbody = world.createBody(bodyDef);

        p.setAsBox(0.01f,0.03f);

        fixtureDef.shape = p;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.maskBits = 0;
        slingbody.createFixture(fixtureDef);
        p.dispose();

        //load background
        background = Assets.level1bg;

        //change slingshot position if needed
//        slingshot.setPosition(3, 2.78f);
        slingshot = new Slingshot(new Vector2(6*grid_size, 2.78f));
        slingshot.setSize(2 * grid_size, 4 * grid_size);
        stage.addActor(slingshot);
        slingbound.x = slingshot.getPosition().x + slingshot.getWidth();
        slingbound.y = slingshot.getPosition().y + slingshot.getHeight();
        slingbound.z = slingbound.x - 1.5f * slingshot.getWidth();
        slingbound.w = slingbound.y - 1.5f;
        System.out.println("aaaaaa");

        //---------------------
//        float right = 4;
//        float up = 2.8f;
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
//        woodlog = new Wood(new Vector2(11*grid_size + right, ground.getPosition().y + up), world);
//        woodlog.setSize(2 * obj_size, 20 * obj_size);;
//        woodlog2 = new Wood(new Vector2(11*grid_size + right, ground.getPosition().y + woodlog.getHeight()+up), world);
//        woodlog2.setSize(2 * obj_size, 20 * obj_size);
//        woodlog2.body.setTransform(woodlog2.body.getPosition().x + woodlog2.getHeight()/2, woodlog2.body.getPosition().y, (3.14f/2));
//
//        icelog = new Ice(new Vector2(14*grid_size + right, ground.getPosition().y+up), world);
//        icelog.setSize(2 * obj_size, 20 * obj_size);
//        icelog2 = new Ice(new Vector2(14*grid_size + right, ground.getPosition().y+icelog.getHeight()+up), world);
//        icelog2.setSize(2 * obj_size, 20 * obj_size);
//        icelog2.body.setTransform(icelog2.body.getPosition().x + icelog2.getHeight()/2, icelog2.body.getPosition().y, (3.14f/2));
//
//        stonelog = new Stone(new Vector2(17*grid_size + right, ground.getPosition().y+up), world);
//        stonelog.setSize(2 * obj_size, 20 * obj_size);
//
//        mat_list.add(icelog); mat_list.add(woodlog); mat_list.add(stonelog); mat_list.add(woodlog2);mat_list.add(icelog2);
//
//        ppig = new PeasantPig(new Vector2((12.5f)*grid_size + right, ground.getPosition().y + up), world);
//        ppig.setSize(2 * bird_size, 2 * bird_size);
//        kingPig = new KingPig(new Vector2(16*grid_size + right, ground.getPosition().y + up), world);
//        kingPig.setSize(2 * bird_size, 2 * bird_size);
//        soldierPig = new SoldierPig(new Vector2(19*grid_size + right, 5*grid_size + up), world);
//        soldierPig.setSize(2 * bird_size, 2 * bird_size);
//        pig_list.add(ppig); pig_list.add(kingPig); pig_list.add(soldierPig);

        //----------------------

        save.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                save_game("assets/save_slot"+ AngryBirds.save_slot +"/level1_save.ser");
                pause_b = false;
            }
            return false;
        });

        restart.addListener(event -> {
            if (event.toString().equals("touchDown")) {
                // Handle button click
                game.setScreen(new Level1(game, "assets/level/level1.ser"));
                pause_b = false;
            }
            return false;
        });

        load_game(filepath);

    }


    int fr = 0;
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
        if(pause_b){
            pausestage.act(delta);
            pausestage.draw();
        }
        else if(pig_list.isEmpty()){
            game.setScreen(new WinScreen(game));
        }
        else if(bird_list.isEmpty() && lastBody.getLinearVelocity().equals(new Vector2(0,0))){
            game.setScreen(new LoseScreen(game));
        }else if(bird_list.isEmpty() && (lastBody.getPosition().x < 0 || lastBody.getPosition().y < 0 || lastBody.getPosition().x > 16 || lastBody.getPosition().y > 9)){
            game.setScreen(new LoseScreen(game));
        }
        else world.step(1/60f, 6,2);
        dbg.render(world, stage.getCamera().combined);

    }


    public void dispose(){
        super.dispose();
        this.stage.dispose();
        world.dispose();
        dbg.dispose();
    }
}

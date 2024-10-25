package Screens;

import birds.angry.AngryBirds;
import birds.angry.GameSprites.Assets;
import birds.angry.GameSprites.PeasantPig;
import birds.angry.GameSprites.Redbird;
import birds.angry.GameSprites.Slingshot;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level1 extends BaseScreen {
    private Button pause;
    SpriteBatch batch;
    private Slingshot slingshot;
    private Redbird redbird;
    private PeasantPig ppig;
    private Texture background;

    public Level1(Game game) {
        super(game);

//        Texture sling = new Texture(Gdx.files.internal("screens/levels/slingshot.png"));
        background = Assets.level1bg;
        slingshot = new Slingshot(new Vector2(6*grid_size, 4*grid_size));
        redbird = new Redbird(new Vector2(4*grid_size, 4*grid_size));
//        redbird.setPosition(700, 600);
        ppig = new PeasantPig(new Vector2(12*grid_size, 4*grid_size));
        batch = new SpriteBatch();
        pause = new TextButton("pause", skin);
        pause.setPosition(50, 50);
//        pause.setTouchable(Touchable.enabled);
        stage.addActor(pause);
        stage.addActor(slingshot);
        stage.addActor(redbird);
        stage.addActor(ppig);
        stage.setDebugAll(true);
        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Pause");
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
        super.render(delta);
//        batch.begin();
//        batch.draw(Assets.redbirds[0].getTexture(), 100, 100);
//        batch.end();
    }
}

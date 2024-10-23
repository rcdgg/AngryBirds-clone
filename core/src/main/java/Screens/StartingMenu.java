package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StartingMenu extends BaseScreen {
    private TextButton start, quit;
    private ImageButton settings;
    private final Image background;
    private final Texture title;
    private ShapeRenderer shapeRenderer;



    public StartingMenu(Game game){
        super(game);
        shapeRenderer = new ShapeRenderer();

        Skin skin = new Skin(Gdx.files.internal("screens/mainmenu/skin/comic-ui.json"));

        start = new TextButton("Start Game", skin);
        quit = new TextButton("Exit", skin);

        Texture cogTexture = new Texture(Gdx.files.internal("screens/mainmenu/cog icon.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(cogTexture));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(cogTexture));
        settings = new ImageButton(buttonStyle);

        background = new Image(new Texture(Gdx.files.internal("screens/mainmenu/angy bird bg.jpg")));
        title = new Texture(Gdx.files.internal("screens/mainmenu/title.png"));

        settings.setPosition(10, 10);
        settings.setSize(70, 70);

        Table table = new Table();
        table.setFillParent(true);
//        stage.addActor(background);
        table.add(start).pad(70).height(100);
        table.row();
        table.add(quit).height(100);
        stage.addActor(settings);

        stage.addActor(table);

        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new SettingsMenu(game, game.getScreen()));
            }
        });
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new LevelSelect(game));
            }
        });
        quit.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                Gdx.app.exit();
//                System.exit(0);
            }
        });

    }

    public void create(){

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        background.draw(batch,1);
        batch.draw(title,450,550,700,300);
        batch.end();
        shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(45, 45, 40);
        shapeRenderer.end();
//        System.out.println("1st screen");
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        shapeRenderer.dispose();
    }
}

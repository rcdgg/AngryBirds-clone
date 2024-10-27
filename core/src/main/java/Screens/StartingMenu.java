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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StartingMenu extends BaseScreen {
    private TextButton start, quit, load, bp;
    private ImageButton settings;
    private SpriteBatch batch;
    private Image background;
    private Texture cogTexture;



    public StartingMenu(Game game){
        super(game);
    }
    @Override
    public void show(){
        start = new TextButton("Start Game", skin);
        quit = new TextButton("Exit", skin);
        load = new TextButton("Load Game", skin);
        bp = new TextButton("Battle pass", skin);


        batch = (SpriteBatch) stage.getBatch();

        Texture cogTexture = new Texture(Gdx.files.internal("screens/mainmenu/cog icon.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(cogTexture));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(cogTexture));
        settings = new ImageButton(buttonStyle);

        settings.setPosition(10, 10);
        settings.setSize(70, 70);

        background = new Image(new Texture(Gdx.files.internal("screens/mainmenu/angy bird bg.jpg")));

        Image title = new Image(new Texture(Gdx.files.internal("screens/mainmenu/title.png")));
        int factor = grid_size * 7;
        title.setSize(title.getWidth() * factor / title.getHeight(),factor);
        title.setPosition(stage.getWidth() / 2 - title.getWidth() / 2, grid_size * 12);

        Table table = new Table();
        table.setFillParent(true);
//        stage.addActor(background);

        table.add(start).pad(40);
        table.row();
        table.add(load).padBottom(40);
        table.row();
        table.add(bp).padBottom(40);
        table.row();
        table.add(quit);

        stage.addActor(table);

        stage.addActor(title);
        stage.addActor(settings);

        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new SettingsMenu(game));
            }
        });

        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new LevelSelect(game));
            }
        });

        bp.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new BattlePass(game));
            }
        });

        load.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new LoadGame(game));
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
        super.show();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch,1);
        batch.end();
//        System.out.println("1st screen");
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(45, 45, 40);
        shapeRenderer.end();
        super.render(delta);

    }

    @Override
    public void dispose() {
        super.dispose();
        cogTexture.dispose();
    }
}

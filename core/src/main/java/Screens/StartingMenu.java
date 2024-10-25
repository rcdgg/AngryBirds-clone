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
    private TextButton start, quit, load;
    private ImageButton settings;
    private ShapeRenderer shapeRenderer;
    private Skin skin;



    public StartingMenu(Game game){
        super(game);
        shapeRenderer = new ShapeRenderer();

        skin = new Skin(Gdx.files.internal("screens/mainmenu/skin/comic-ui.json"));



    }
    @Override
    public void show(){
        start = new TextButton("Start Game", skin);
        quit = new TextButton("Exit", skin);
        load = new TextButton("Load Game", skin);

        Texture cogTexture = new Texture(Gdx.files.internal("screens/mainmenu/cog icon.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(cogTexture));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(cogTexture));
        settings = new ImageButton(buttonStyle);

        settings.setPosition(10, 10);
        settings.setSize(70, 70);

        Texture texture = new Texture(Gdx.files.internal("screens/mainmenu/angy bird bg.jpg"));
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);

        Image title = new Image(new Texture(Gdx.files.internal("screens/mainmenu/title.png")));
        int factor = grid_size * 7;
        title.setSize(title.getWidth() * factor / title.getHeight(),factor);
        title.setPosition(stage.getWidth() / 2 - title.getWidth() / 2, grid_size * 11);

        Table table = new Table();
        table.setFillParent(true);
//        stage.addActor(background);
        table.row();
        table.add(start).pad(70).height(100);
        table.row();
        table.add(load).padBottom(70).height(100);
        table.row();
        table.add(quit).height(100);
        table.background(drawable);
        stage.addActor(table);

        stage.addActor(title);
        stage.addActor(settings);

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
        super.show();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
//        batch.setProjectionMatrix(stage.getCamera().combined);
//        batch.begin();
//        background.draw(batch,1);
//        batch.draw(title,450,550,700,300);
//        batch.end();
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

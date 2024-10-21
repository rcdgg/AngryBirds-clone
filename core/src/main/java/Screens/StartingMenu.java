package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StartingMenu extends BaseScreen {
    private TextButton start, quit;
    private ImageButton settings;
    private Image background;
    SpriteBatch batch;

    public StartingMenu(){
        super();
        Skin skin = new Skin(Gdx.files.internal("screens/mainmenu/skin/comic-ui.json"));

        batch = new SpriteBatch();

        start = new TextButton("Start Game", skin);
        quit = new TextButton("Exit", skin);

        Texture cogTexture = new Texture(Gdx.files.internal("screens/mainmenu/cog icon.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(cogTexture));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(cogTexture));
        settings = new ImageButton(buttonStyle);

        background = new Image(new Texture(Gdx.files.internal("screens/mainmenu/angy bird bg.jpg")));

        settings.setPosition(10, 10);
        settings.setSize(70, 70);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(background);
        table.add(start).pad(70).height(100);
        table.row();
        table.add(quit).height(100);
        stage.addActor(settings);

        stage.addActor(table);

        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                System.out.println("settings");
            }
        });
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                System.out.println("button working?");
            }
        });
    }

    public void create(){

    }

    @Override
    public void render(float delta) {
        batch.begin();
        background.draw(batch,1);
        batch.end();
        super.render(delta);
    }
}

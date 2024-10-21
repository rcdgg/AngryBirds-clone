package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StartingMenu extends BaseScreen {
    private TextButton start, quit;
    private ImageButton settings;


    public StartingMenu(){
        super();
        Skin skin = new Skin(Gdx.files.internal("screens/mainmenu/skin/comic-ui.json"));

        start = new TextButton("Start Game", skin);
        quit = new TextButton("Exit", skin);

        Texture cogTexture = new Texture(Gdx.files.internal("screens/mainmenu/cog icon.png"));
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(cogTexture));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(cogTexture));
        settings = new ImageButton(buttonStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.add(start).pad(70).height(100);
        table.row();
        table.add(quit).height(100);
        table.add(settings);
//        centerButton(start);

        stage.addActor(table);

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
        super.render(delta);
    }
}

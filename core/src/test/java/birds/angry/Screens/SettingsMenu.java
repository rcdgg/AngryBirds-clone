package birds.angry.Screens;

// will open above the previous screen

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsMenu extends BaseScreen{

    public SettingsMenu(Game game){
        super(game);
        Image bg = new Image(new Texture(Gdx.files.internal("screens/mainmenu/angy bird bg.jpg")));
        Image background = new Image(new Texture(Gdx.files.internal("screens/settingsmenu/settingsmenu.png")));
        background.setSize(1000,   background.getHeight() / background.getWidth() * 1000);
        background.setPosition(stage.getWidth() / 2 - background.getWidth() / 2, stage.getHeight() / 2 - background.getHeight() / 2);

        Button back = new Button(invisibleButtonStyle);
        back.setSize(120,120);
        back.setPosition(stage.getWidth() / 2 - back.getWidth() / 2 - 10, 100);
        stage.addActor(bg);
        stage.addActor(background);
        stage.addActor(back);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new StartingMenu(game));
                dispose();
            }
        });
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.BLACK);
//        batch.begin();
//        batch.draw(prev_screen, stage.getWidth(), stage.getHeight());
//        batch.end();
        super.render(delta);
    }

}

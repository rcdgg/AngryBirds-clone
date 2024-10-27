package birds.angry.Screens;

import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class WinScreen extends BaseScreen {
    Image winbg;
    Button levelselect;
    public WinScreen(Game game) {
        super(game);
        winbg = Assets.winbg;
        levelselect = new Button(invisibleButtonStyle);
        levelselect.setSize(70,70);
        levelselect.setPosition(13 * grid_size,4.5f * grid_size);

        stage.addActor(winbg);
        stage.addActor(levelselect);

        levelselect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new LevelSelect(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}

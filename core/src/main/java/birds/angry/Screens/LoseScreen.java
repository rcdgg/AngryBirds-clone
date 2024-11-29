package birds.angry.Screens;

import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoseScreen extends BaseScreen {
    private Image losebg;
    private Button levelselect;
    public LoseScreen(Game game) {
        super(game);
        losebg = Assets.losebg;
        levelselect = new Button(invisibleButtonStyle);
        levelselect.setSize(70,70);
        levelselect.setPosition(15.5f * grid_size,4.5f * grid_size);

        stage.addActor(losebg);
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
//        ScreenUtils.clear(Color.BLACK);
        super.render(delta);
    }
}

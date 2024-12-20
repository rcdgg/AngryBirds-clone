package birds.angry.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class SaveGame extends BaseScreen{
    public SaveGame(Game game){
        super(game);
        TextButton placeholder = new TextButton("Save Game TBD", skin);
        placeholder.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
        stage.addActor(placeholder);

        placeholder.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new StartingMenu(game));
            }
        });
    }
    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.WHITE);
        super.render(delta);
    }
}

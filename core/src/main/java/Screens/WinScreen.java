package Screens;

import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class WinScreen extends BaseScreen {
    Image winbg;
    Button levelselect;
    public WinScreen(Game game) {
        super(game);
        winbg = Assets.winbg;
        winbg.setSize(250,   winbg.getHeight() / winbg.getWidth() * 250);
        winbg.setPosition(stage.getWidth() / 2 - winbg.getWidth() / 2 - 50, stage.getHeight() / 2 - winbg.getHeight() / 2);
        levelselect = new TextButton("back", skin);
        levelselect.setPosition(stage.getWidth() / 2 - winbg.getWidth() / 2 - 50, stage.getHeight() / 2 - winbg.getHeight() / 2 - 70);
        stage.addActor(winbg);
        stage.addActor(levelselect);
        levelselect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new LevelSelect(game));
                dispose();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}

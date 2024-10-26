package Screens;

import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class WinScreen extends BaseScreen {
    Image winbg;
    public WinScreen(Game game) {
        super(game);
        winbg = Assets.winbg;
        winbg.setSize(250,   winbg.getHeight() / winbg.getWidth() * 250);
        winbg.setPosition(stage.getWidth() / 2 - winbg.getWidth() / 2 - 50, stage.getHeight() / 2 - winbg.getHeight() / 2);
        stage.addActor(winbg);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}

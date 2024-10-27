package birds.angry.Screens;

import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Pause extends BaseScreen{
    private Screen prev_screen;
    private Image pausebg;
    public Pause(Game game, Screen prev_screen) {
        super(game);
        this.prev_screen = prev_screen;
        pausebg = Assets.pausebg;
        pausebg.setSize(500,   pausebg.getHeight() / pausebg.getWidth() * 500);
        pausebg.setPosition(stage.getWidth() / 2 - pausebg.getWidth() / 2 - 50, stage.getHeight() / 2 - pausebg.getHeight() / 2);

        Button resume = new Button(invisibleButtonStyle);
        resume.setSize(5 * grid_size,1.15f * grid_size);
        resume.setPosition(12.5f * grid_size, 11 * grid_size);
        Button save = new Button(invisibleButtonStyle);
        save.setSize(5 * grid_size,1.15f * grid_size);
        save.setPosition(12.5f * grid_size, 7.75f * grid_size);
        Button restart = new Button(invisibleButtonStyle);
        restart.setSize(5 * grid_size,1.15f * grid_size);
        restart.setPosition(12.5f * grid_size, 9.4f * grid_size);
        Button levelselect = new Button(invisibleButtonStyle);
        levelselect.setSize(5 * grid_size,1.15f * grid_size);
        levelselect.setPosition(12.5f * grid_size, 6 * grid_size);
        stage.addActor(pausebg);
        stage.addActor(resume);
        stage.addActor(restart);
        stage.addActor(save);
        stage.addActor(levelselect);

        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(prev_screen);
                dispose();
            }
        });
        levelselect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new LevelSelect(game));
                dispose();
            }
        });
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new SaveGame(game));
                dispose();
            }
        });

    }
    @Override
    public void render(float delta){
        super.render(delta);
    }
}

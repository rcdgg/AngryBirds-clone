package Screens;

import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class Pause extends BaseScreen{
    private Screen prev_screen;
    private Button resume;
    private Button save;
    private Button levelselect;
    private Image pausebg;
    public Pause(Game game, Screen prev_screen) {
        super(game);
        this.prev_screen = prev_screen;
        pausebg = Assets.pausebg;
        pausebg.setSize(250,   pausebg.getHeight() / pausebg.getWidth() * 250);
        pausebg.setPosition(stage.getWidth() / 2 - pausebg.getWidth() / 2 - 50, stage.getHeight() / 2 - pausebg.getHeight() / 2);
        resume = new TextButton("resume", skin);
        resume.setPosition(stage.getWidth() / 2 - pausebg.getWidth() / 2 + 40, stage.getHeight() / 2 - pausebg.getHeight() / 2 + 150);
        save = new TextButton("save", skin);
        save.setPosition(stage.getWidth() / 2 - pausebg.getWidth() / 2 + 40, stage.getHeight() / 2 - pausebg.getHeight() / 2 + 90);
        levelselect = new TextButton("level select", skin);
        levelselect.setPosition(stage.getWidth() / 2 - pausebg.getWidth() / 2 + 40, stage.getHeight() / 2 - pausebg.getHeight() / 2 + 40);
        //        Button resume = new Button(Assets.resumeButtonStyle);
        stage.addActor(pausebg);
        stage.addActor(resume);
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

    }
    @Override
    public void render(float delta){
        super.render(delta);
    }
}

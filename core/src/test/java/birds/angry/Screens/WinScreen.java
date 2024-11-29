package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class WinScreen extends BaseScreen {
    private Image winbg;
    private Button levelselect;
    private BitmapFont font;
    private int score, high_score;
    public WinScreen(Game game, int level, int score) {
        super(game);
        winbg = Assets.winbg;
        levelselect = new Button(invisibleButtonStyle);
        levelselect.setSize(70,70);
        levelselect.setPosition(15.5f * grid_size,4.5f * grid_size);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40; // Font size in pixels
        parameter.color = Color.WHITE; // Font color
        font = generator.generateFont(parameter); // Create font
        generator.dispose(); // Dispose generator after use

        this.score = score;
        high_score = AngryBirds.score.get(level - 1);

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
        batch.begin();
        font.draw(batch, String.format("%d", score), 15 * grid_size, 9 * grid_size);
        font.draw(batch, String.format("%d", high_score), 16.5f * grid_size, 7.5f * grid_size);
        batch.end();
    }
}

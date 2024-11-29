package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class BattlePass extends BaseScreen{
    private BitmapFont font, font2;
    private Texture background;
    private int total_score = 0;

    public BattlePass(Game game){
        super(game);
        for(int i: AngryBirds.score){
            total_score += i;
        }

        Button skin1 = new Button(invisibleButtonStyle);
        Button skin2 = new Button(invisibleButtonStyle);

        skin1.setSize(8.5f * grid_size,4.5f * grid_size);
        skin1.setPosition(6.5f * grid_size,8 * grid_size);

        skin2.setSize(8.5f * grid_size,4.5f * grid_size);
        skin2.setPosition(skin1.getX() + skin1.getWidth() + 1.5f * grid_size,8 * grid_size);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("retropix.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40; // Font size in pixels
        parameter.color = Color.WHITE; // Font color
        font = generator.generateFont(parameter); // Create font
        parameter.size = 41;
        parameter.color = Color.BLACK;
        font2 = generator.generateFont(parameter);
        generator.dispose(); // Dispose generator after use

        font.getData().setScale(1.5f, 1.0f);
        font2.getData().setScale(1.5f, 1.02f);

        background = Assets.bpbg;
        stage.addActor(skin1);
        stage.addActor(skin2);

        skin1.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                AngryBirds.skin_pack = 1;
                game.setScreen(new StartingMenu(game));
            }
        });

        skin2.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                if(total_score >= 2500) AngryBirds.skin_pack = 2;
                game.setScreen(new StartingMenu(game));
            }
        });
    }
    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.WHITE);
//        super.render(delta);
        batch.begin();git
        batch.draw(background, 0, 0, 1600, 900);
        font2.draw(batch, String.format("%d", total_score), 14.95f * grid_size, 5.9f * grid_size);
        font.draw(batch, String.format("%d", total_score), 15 * grid_size, 5.9f * grid_size);
        batch.end();
        stage.act(delta);
        stage.draw();
        if(grid){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.GRAY);

            int gridSpacing = grid_size;

            for (int y = 0; y < stage.getHeight(); y += gridSpacing) {
                shapeRenderer.line(0, y, stage.getWidth(), y);
            }

            for (int x = 0; x < stage.getWidth(); x += gridSpacing) {
                shapeRenderer.line(x, 0, x, stage.getHeight());
            }

            shapeRenderer.end();
        }
    }
}

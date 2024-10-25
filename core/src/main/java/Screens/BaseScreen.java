package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BaseScreen extends ScreenAdapter {
    protected Stage stage;
    protected Game game;
    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;
    protected int grid_size;
    private boolean grid;
    protected Skin skin;

    public BaseScreen(Game game) {
        stage = new Stage(new FitViewport(1600, 900));
        this.game = game;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        stage.setDebugAll(true);
        grid = true;
        grid_size = 50;
        skin = new Skin(Gdx.files.internal("skin/flat-earth/skin/flat-earth-ui.json"));
    }

    @Override
    public void show() {
        // Set the input processor to this stage
        Gdx.input.setInputProcessor(stage);
        render(0);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and draw the stage
        stage.act(delta);
        stage.draw();
        if(grid){
            // Begin drawing lines
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.GRAY);  // Set grid line color

            // Grid spacing
            float gridSpacing = grid_size;  // The space between lines

            // Draw horizontal lines
            for (float y = 0; y < stage.getHeight(); y += gridSpacing) {
                shapeRenderer.line(0, y, stage.getWidth(), y);
            }

            // Draw vertical lines
            for (float x = 0; x < stage.getWidth(); x += gridSpacing) {
                shapeRenderer.line(x, 0, x, stage.getHeight());
            }

            shapeRenderer.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
//        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}


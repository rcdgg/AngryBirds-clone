package birds.angry.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BaseScreen extends ScreenAdapter implements ApplicationListener {
    protected Stage stage;
    protected Game game;
    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;
    protected int grid_size;
    private boolean grid;
    protected Skin skin;
    protected Button.ButtonStyle invisibleButtonStyle;
    int save_slot;

    public BaseScreen(Game game) {
        stage = new Stage(new FitViewport(1600, 900));
        this.game = game;
        batch = new SpriteBatch();
        batch.setProjectionMatrix(stage.getCamera().combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        stage.setDebugAll(true);
        grid = true;
        grid_size = 50;
        skin = new Skin(Gdx.files.internal("skin/flat-earth/skin/flat-earth-ui.json"));

        //invisible button
        {
            invisibleButtonStyle = new Button.ButtonStyle();
            invisibleButtonStyle.up = null;
            invisibleButtonStyle.down = null;
            invisibleButtonStyle.checked = null;
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        render(0);
    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {

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
        shapeRenderer.dispose();
        skin.dispose();
    }
}


package birds.angry;

import Screens.StartingMenu;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import java.awt.*;

/* look into asset manager
* more backgrounds because multiple levels(organise the asset folder
* more birds
* battle pass
* */
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds extends Game {
    //    private SpriteBatch batch;
//    private Texture bg;
//    private FitViewport viewport;
//    private OrthographicCamera camera;
//    private TextureRegion[] red_bird;
//    ShapeRenderer shapeRenderer;
//    Sprite redbird_sprite;
//    int red_bird_text_cnt = 0;
//    BitmapFont font;
//    private Stage stage;
//    private Skin skin;
    private Game game;
    public AngryBirds(){
        game = this;
    }
    @Override
    public void create() {
        screen = new StartingMenu(game);
        screen.show();
//        batch = new SpriteBatch();
//        bg = new Texture("angy bird bg.jpg");
//
//        camera = new OrthographicCamera();
//        viewport = new FitViewport(16, 9, camera);
//
//        Texture redBird_spritesheet = new Texture("red bird spritesheet.png");
//        int frameWidth = redBird_spritesheet.getWidth() / 4;
//        int frameHeight = redBird_spritesheet.getHeight();
//        TextureRegion[][] tmp = TextureRegion.split(redBird_spritesheet, frameWidth, frameHeight);
//
//        red_bird = tmp[0];
//        redbird_sprite = new Sprite(red_bird[0]);
//        shapeRenderer = new ShapeRenderer();
//
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Minecrafter.Reg.ttf"));
//        FreeTypeFontParameter parameter =  new FreeTypeFontParameter();
//        parameter.size = 2;
//        parameter.borderColor = Color.BLACK;
//        font = generator.generateFont(parameter);
//        generator.dispose();

    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }
//
//    public void draw(){
//        float red_bird_ratio = (float) red_bird[0].getRegionHeight() / red_bird[0].getRegionWidth();
//        int red_bird_scale = 1;
//        redbird_sprite.setSize(red_bird_scale, red_bird_scale * red_bird_ratio);
//
//        ScreenUtils.clear(Color.BLACK);
//        batch.setProjectionMatrix(viewport.getCamera().combined);
//        batch.begin();
////        batch.draw(image,0,0, image_scale, image_scale * image_ratio);
////        batch.draw(red_bird[0],0,0,red_bird_scale, red_bird_scale * red_bird_ratio);
//        batch.draw(bg, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
//        redbird_sprite.draw(batch);
////        font.setColor(Color.WHITE);
//        font.draw(batch, "WASD - move around", 1,9 );
//
//        batch.end();
//        shapeRenderer.setProjectionMatrix(camera.combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(0,0, red_bird_scale, red_bird_scale * red_bird_ratio);
//        shapeRenderer.end();
//    }
//
//    public void input(){
//        // Create a vector to hold the mouse position
//        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//
//        // Unproject the screen coordinates to world coordinates
//        camera.unproject(mousePos);
//
//        // Now mousePos.x and mousePos.y hold the world coordinates
//        float worldMouseX = mousePos.x;
//        float worldMouseY = mousePos.y;
//
//        float speed = 2f;
//
//        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
//            redbird_sprite.setPosition((float) (worldMouseX - redbird_sprite.getWidth() / 1.95), (float) (worldMouseY - redbird_sprite.getHeight() / 2.4));
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            redbird_sprite.translateX(speed * Gdx.graphics.getDeltaTime()); // Move right
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            redbird_sprite.translateX(-speed * Gdx.graphics.getDeltaTime()); // Move left
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            redbird_sprite.translateY(speed * Gdx.graphics.getDeltaTime()); // Move up
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            redbird_sprite.translateY(-speed * Gdx.graphics.getDeltaTime()); // Move down
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//            redbird_sprite.setRegion(red_bird[(red_bird_text_cnt++ + 1) % 4]);
//        }
//    }
//
//    public void logic(){
//
//    }
//
//    @Override
//    public void resize(int width, int height){
//        viewport.update(width, height, true);
//    }
//
    @Override
    public void dispose() {
        super.dispose();
        screen.dispose();
    }
}

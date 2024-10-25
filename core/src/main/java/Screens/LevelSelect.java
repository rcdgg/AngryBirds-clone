package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class LevelSelect extends BaseScreen{
    private final Image background;
    private ShapeRenderer shapeRenderer;
    public LevelSelect(Game game){
        super(game);
        shapeRenderer = new ShapeRenderer();
        background = new Image(new Texture("screens/levelselect/level select.png"));

        // Create a Pixmap for the stroke (border)
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED); // Stroke color
        pixmap.drawRectangle(0, 0, 100, 100); // Draw a rectangle stroke

// Create a texture from the pixmap
        Texture pixmapTexture = new Texture(pixmap);
        pixmap.dispose(); // Dispose of the pixmap after creating the texture

// Create a NinePatch from the texture to handle resizing
        NinePatch strokeNinePatch = new NinePatch(pixmapTexture, 2, 2, 2, 2); // Adjust the patch settings for the stroke size
        Drawable strokeDrawable = new NinePatchDrawable(strokeNinePatch);

// Create a ButtonStyle and assign the stroke drawable
        Button.ButtonStyle strokeButtonStyle = new Button.ButtonStyle();
        strokeButtonStyle.up = strokeDrawable; // Use the stroke drawable as the button's up state



        // Create a button style with no background or drawable
        Button.ButtonStyle invisibleButtonStyle = new Button.ButtonStyle();
        invisibleButtonStyle.up = null;   // No texture for the normal state
        invisibleButtonStyle.down = null; // No texture for the pressed state
        invisibleButtonStyle.checked = null; // No texture for the checked state


        Button one = new Button(invisibleButtonStyle);
        one.setPosition(300,stage.getHeight() / 2 - 50);
        one.setSize(100,120);
        one.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new Level1(game));
            }
        });

        Button two = new Button(invisibleButtonStyle);
        two.setPosition(530,stage.getHeight() / 2 - 50);
        two.setSize(100,120);

        Button three = new Button(invisibleButtonStyle);
        three.setPosition(750,stage.getHeight() / 2 - 50);
        three.setSize(100,120);

        Button back = new Button(invisibleButtonStyle);
        back.setSize(100,120);
        back.setPosition(50, 15);

        stage.addActor(one);
        stage.addActor(two);
        stage.addActor(three);
        stage.addActor(back);

//        Skin skin = new Skin(Gdx.files.internal("screens/mainmenu/skin/comic-ui.json"));
//        TextButton check = new TextButton("Level Select", skin);
//        stage.addActor(check);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new StartingMenu(game));
            }
        });
    }
    public void render(float delta) {
//        System.out.println("2nd screen");
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        background.draw(batch,1);
        batch.end();
        super.render(delta);
    }
}

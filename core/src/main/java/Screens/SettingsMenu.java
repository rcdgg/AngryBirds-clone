package Screens;

// will open above the previous screen

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsMenu extends BaseScreen{

    public SettingsMenu(Game game){
        super(game);
        Image bg = new Image(new Texture(Gdx.files.internal("screens/mainmenu/angy bird bg.jpg")));
        Image background = new Image(new Texture(Gdx.files.internal("screens/settingsmenu/settingsmenu.png")));
        background.setSize(1000,   background.getHeight() / background.getWidth() * 1000);
        background.setPosition(stage.getWidth() / 2 - background.getWidth() / 2, stage.getHeight() / 2 - background.getHeight() / 2);

//        // Create a Pixmap for the stroke (border)
//        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
//        pixmap.setColor(Color.RED); // Stroke color
//        pixmap.drawRectangle(0, 0, 100, 100); // Draw a rectangle stroke
//
//// Create a texture from the pixmap
//        Texture pixmapTexture = new Texture(pixmap);
//        pixmap.dispose(); // Dispose of the pixmap after creating the texture
//
//// Create a NinePatch from the texture to handle resizing
//        NinePatch strokeNinePatch = new NinePatch(pixmapTexture, 2, 2, 2, 2); // Adjust the patch settings for the stroke size
//        Drawable strokeDrawable = new NinePatchDrawable(strokeNinePatch);
//
//// Create a ButtonStyle and assign the stroke drawable
//        Button.ButtonStyle strokeButtonStyle = new Button.ButtonStyle();
//        strokeButtonStyle.up = strokeDrawable; // Use the stroke drawable as the button's up state

        // Create a button style with no background or drawable
        Button.ButtonStyle invisibleButtonStyle = new Button.ButtonStyle();
        invisibleButtonStyle.up = null;   // No texture for the normal state
        invisibleButtonStyle.down = null; // No texture for the pressed state
        invisibleButtonStyle.checked = null; // No texture for the checked state

        Button back = new Button(invisibleButtonStyle);
        back.setSize(120,120);
        back.setPosition(stage.getWidth() / 2 - back.getWidth() / 2 - 10, 100);
        stage.addActor(bg);
        stage.addActor(background);
        stage.addActor(back);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                game.setScreen(new StartingMenu(game));
                dispose();
            }
        });
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.BLACK);
//        batch.begin();
//        batch.draw(prev_screen, stage.getWidth(), stage.getHeight());
//        batch.end();
        super.render(delta);
    }

}

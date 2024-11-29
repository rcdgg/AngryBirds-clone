package birds.angry.Screens;

import birds.angry.AngryBirds;
import birds.angry.GameScore;
import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadGame extends BaseScreen{
    Texture background;
    public LoadGame(Game game){
        super(game);
        background = Assets.loadgamebg;
        Button one = new Button(invisibleButtonStyle);
        one.setSize(5 * grid_size,2.5f * grid_size);
        one.setPosition(8 * grid_size, 6.5f * grid_size);
        Button two = new Button(invisibleButtonStyle);
        two.setSize(5 * grid_size,2.5f * grid_size);
        two.setPosition(one.getX() + one.getWidth() + 0.5f * grid_size, 6.5f * grid_size);
        Button three = new Button(invisibleButtonStyle);
        three.setSize(5 * grid_size,2.5f * grid_size);
        three.setPosition(two.getX() + two.getWidth() + 0.5f * grid_size, 6.5f * grid_size);

        stage.addActor(one);
        stage.addActor(two);
        stage.addActor(three);

        one.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                AngryBirds.save_slot = 1;
                //high scores load
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("assets/save_slot" + AngryBirds.save_slot + "/scores.ser"))){
                    GameScore gs =(GameScore) in.readObject();
                    AngryBirds.score = gs.high_scores;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                game.setScreen(new LevelSelect(game));
            }
        });
        two.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                AngryBirds.save_slot = 2;
                //high scores load
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("assets/save_slot" + AngryBirds.save_slot + "/scores.ser"))){
                    GameScore gs =(GameScore) in.readObject();
                    AngryBirds.score = gs.high_scores;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                game.setScreen(new LevelSelect(game));
            }
        });
        three.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                AngryBirds.save_slot = 3;
                //high scores load
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("assets/save_slot" + AngryBirds.save_slot + "/scores.ser"))){
                    GameScore gs =(GameScore) in.readObject();
                    AngryBirds.score = gs.high_scores;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                game.setScreen(new LevelSelect(game));
            }
        });
    }
    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.WHITE);
//        super.render(delta);
        batch.begin();
        batch.draw(background, 0, 0, 1600, 900);
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

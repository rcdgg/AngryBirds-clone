package birds.angry.Screens;

import birds.angry.AngryBirds;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class LoadGame extends BaseScreen{
    public LoadGame(Game game){
        super(game);
        TextButton one = new TextButton("save 1", skin);
        TextButton two = new TextButton("save 2", skin);
        TextButton three = new TextButton("save 3", skin);
        Table table = new Table();
        table.setFillParent(true);
        table.add(one, two, three);
        stage.addActor(table);

        one.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                AngryBirds.save_slot = 1;
                game.setScreen(new LevelSelect(game));
            }
        });
        two.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                AngryBirds.save_slot = 2;
                game.setScreen(new LevelSelect(game));
            }
        });
        three.addListener (new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                AngryBirds.save_slot = 3;
                game.setScreen(new LevelSelect(game));
            }
        });
    }
    @Override
    public void render(float delta){
        ScreenUtils.clear(Color.WHITE);
        super.render(delta);
    }
}

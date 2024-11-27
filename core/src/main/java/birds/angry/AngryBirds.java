package birds.angry;

import birds.angry.Screens.StartingMenu;
import birds.angry.GameSprites.Assets;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/* look into asset manager
* more backgrounds because multiple levels(organise the asset folder
* more birds
* battle pass
* */
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryBirds extends Game {
    private Game game;
    public static int save_slot = 1;
    public static ArrayList<Integer> score = new ArrayList<>();

    public AngryBirds(){
        game = this;
    }
    @Override
    public void create() {
        screen = new StartingMenu(game);
        screen.show();
        Assets.load();
    }


    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }
    @Override
    public void dispose() {
        super.dispose();
        screen.dispose();
    }
}

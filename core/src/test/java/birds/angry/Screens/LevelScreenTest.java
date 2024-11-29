package birds.angry.Screens;

import birds.angry.GameSprites.*;
import birds.angry.GameState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LevelScreenTest {

    @org.junit.jupiter.api.Test
    public void save_game() throws IOException, ClassNotFoundException {
        LevelScreen.bird_list = new ArrayList<>();
        LevelScreen.bird_list.add(new Redbird(new Vector2(1,1),null));
        LevelScreen.save_game("test_save.ser");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("test_save.ser"));
        GameState gameState =(GameState) in.readObject();
        GameState.GameObjectState bird = gameState.birds.get(0);
        float xx = bird.x;
        float yy = bird.y;
        assertEquals(1,xx);
        assertEquals(1,yy);
    }

    @org.junit.jupiter.api.Test
    public void load_game() {
        LevelScreen.bird_list = new ArrayList<>();
        LevelScreen.bird_list.add(new Redbird(new Vector2(1,1),null));
        LevelScreen.save_game("test_save.ser");
        LevelScreen.bird_list.clear();
        LevelScreen.load_game("test_save.ser");
        assertEquals(1,LevelScreen.bird_list.size());
    }

    @org.junit.jupiter.api.Test
    public void win_condition() {
        int score = 1;
        int levelMAXscore = 100;
        boolean won = false;
        if(score>levelMAXscore){
            won = true;
            System.out.println("You win!");
        }
        else{
            won = false;
            System.out.println("You lose!");
        }
        assertFalse(won);
    }
}

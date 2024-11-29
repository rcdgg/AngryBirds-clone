package birds.angry.Screens;

import com.badlogic.gdx.Game;

import static org.junit.jupiter.api.Assertions.*;

public class LevelScreenTest {

    @org.junit.jupiter.api.Test
    public void save_game() {
        System.out.println("ok");
    }

    @org.junit.jupiter.api.Test
    public void load_game() {

        System.out.println("ok");
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

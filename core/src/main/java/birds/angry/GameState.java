package birds.angry;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    public List<GameObjectState> birds, materials, pigs, birds_shot;
    public int score, high_score;
    public boolean clear;
    private static final long serialVersionUID = 1L; // Replace with correct UID
//    public float elapsedTime;

    public GameState(){
        birds = new ArrayList<>();
        materials = new ArrayList<>();
        pigs = new ArrayList<>();
        birds_shot = new ArrayList<>();
        score = 0;
        high_score = 0;
        clear = false;
    }

    public static class GameObjectState implements Serializable {
        public float x, y;          // Position
        public float vx, vy;        // Velocity
        public float angle;         // Rotation
        public float sx, sy;        // Size
        public int health;          // Health
        public String texturePath;  // Path to texture
        public String type;         // type of object
    }
}

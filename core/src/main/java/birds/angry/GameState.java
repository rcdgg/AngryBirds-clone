package birds.angry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    public List<GameObjectState> birds, materials, pigs;
    public int score;
    public int level;
//    public float elapsedTime;

    public GameState(){
        birds = new ArrayList<>();
        materials = new ArrayList<>();
        pigs = new ArrayList<>();
        score = 0;
        level = 0;
    }

    public static class GameObjectState implements Serializable {
        public float x, y;          // Position
        public float vx, vy;        // Velocity
        public float angle;         // Rotation
        public float sx, sy;        // Size
        public int health;          // Health
        public String texturePath;  // Path to texture
    }
}

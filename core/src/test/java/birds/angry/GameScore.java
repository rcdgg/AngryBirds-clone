package birds.angry;

import java.io.Serializable;
import java.util.ArrayList;

public class GameScore implements Serializable {
    public ArrayList<Integer> high_scores;
    private static final long serialVersionUID = 2L;
    public GameScore(){
        high_scores = new ArrayList<>(3);
    }
}

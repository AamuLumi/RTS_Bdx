package game;

import java.util.ArrayList;

import gameframework.core.GameDefaultImpl;
import gameframework.core.GameLevel;

public class Main {
    public static final int SPRITE_SIZE = 16;

    public static void main(String[] args) {
        GameDefaultImpl g = new GameDefaultImpl();
        ArrayList<GameLevel> levels = new ArrayList<>();

        levels.add(new GameLevelOne(g)); // only one level is available

        g.setLevels(levels);
        g.start();
    }

}

package game.framework.extended;

import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.OverlapProcessor;

public class UniqueGameUniverse extends GameUniverseRTS {

    private static UniqueGameUniverse instance;

    private UniqueGameUniverse(MoveBlockerChecker obs, OverlapProcessor col) {
        super(obs, col);
    }

    public static UniqueGameUniverse getInstance() {
        return instance;
    }

    public static void init(MoveBlockerChecker obs, OverlapProcessor col) {
        instance = new UniqueGameUniverse(obs, col);
    }
}

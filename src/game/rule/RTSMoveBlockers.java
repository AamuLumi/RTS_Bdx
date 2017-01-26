package game.rule;

import game.entity.MilitaryUnit;
import game.entity.Mountain;
import gameframework.moves_rules.IllegalMoveException;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;

public class RTSMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {

    public void moveBlockerRule(MilitaryUnit u, Mountain m) throws IllegalMoveException {
        throw new IllegalMoveException();
    }
}

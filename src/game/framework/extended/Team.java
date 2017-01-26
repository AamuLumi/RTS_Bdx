package game.framework.extended;

import soldier.core.UnitGroup;

public class Team extends UnitGroup {

    protected UnitFactory unitFactory;

    public Team(String name, UnitFactory f) {
        super(name);
        this.unitFactory = f;

        this.unitFactory.setTeam(this);
    }
}

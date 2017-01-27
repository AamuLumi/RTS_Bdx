package game.framework.extended;

import game.entity.MilitaryUnit;
import soldier.core.UnitGroup;

public class Team extends UnitGroup {

    protected UnitFactory unitFactory;

    public Team(String name, UnitFactory f) {
        super(name);
        this.unitFactory = f;

        this.unitFactory.setTeam(this);
    }
    
    public void removeUnit(MilitaryUnit m){
    	UniqueGameUniverse.getInstance().removeGameEntity(m);
    	
    	super.removeUnit(m.getCore());
    	
    	m.destruct();
    }
}

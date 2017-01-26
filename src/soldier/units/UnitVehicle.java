package soldier.units;

import soldier.core.BehaviorSoldierStd;
import soldier.core.BreakingRuleException;
import soldier.core.UnitRider;
import soldier.core.Weapon;

public class UnitVehicle extends UnitRider {

	public UnitVehicle(String soldierName) {
		super(soldierName, new BehaviorSoldierStd(120, 20));
	}

	/**
	 * A BikerMan can have at most one equipment
	 */
	@Override
	public void addEquipment(Weapon w) {
		if (nbWeapons() > 1)
			throw new BreakingRuleException();
		super.addEquipment(w);
	}
}
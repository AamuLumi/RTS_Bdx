/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universite Bordeaux.
 */
package soldier.core;

public abstract class UnitRider extends UnitSimple {

	public UnitRider(String name, BehaviorSoldier behavior) {
		super(name, behavior);
	}

	@Override
	public void accept(UnitVisitor v) {
		v.visit(this);
	}
}

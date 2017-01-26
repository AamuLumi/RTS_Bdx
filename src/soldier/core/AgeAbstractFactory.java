/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universite Bordeaux.
 */
package soldier.core;

public interface AgeAbstractFactory {
	Unit infantryUnit(String name);

	Unit riderUnit(String name);

	Weapon attackWeapon();

	Weapon defenseWeapon();
}

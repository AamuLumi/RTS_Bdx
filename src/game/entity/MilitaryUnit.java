package game.entity;

import game.tools.DestructAction;
import gameframework.core.GameEntity;
import soldier.core.UnitSimple;

public interface MilitaryUnit extends GameEntity{
	public UnitSimple getCore();
	public void destruct();
	public void setDestruct(DestructAction a);
}

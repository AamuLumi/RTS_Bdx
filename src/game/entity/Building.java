package game.entity;

import gameframework.core.GameEntity;
import gameframework.core.Overlappable;
import gameframework.moves_rules.MoveBlocker;

/**
 * Created by Jean on 19/01/2017.
 */
public interface Building extends Overlappable, GameEntity, MoveBlocker{
    public void doAction();
    public boolean isSelected();
    public void setSelectOption(boolean isSelected);
}

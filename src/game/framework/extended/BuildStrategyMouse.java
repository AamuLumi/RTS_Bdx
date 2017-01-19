package game.framework.extended;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.entity.Building;
import gameframework.core.GameMovable;
import gameframework.core.Movable;
import gameframework.moves_rules.MoveStrategy;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

/**
 * {@link MoveStrategy} which listens to the mouse and answers new
 * {@link SpeedVector speed vectors} based on what the user typed.
 */
public class BuildStrategyMouse extends MouseAdapter implements MoveStrategy {
    protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0, 0));

    private Rectangle boundingBox;
    private Building building;

    public BuildStrategyMouse(Building b) {
        building = b;
        boundingBox = b.getBoundingBox();
    }

    public SpeedVector getSpeedVector() {
        return speedVector;
    }

    public boolean isOnUnit(Point e) {
        Point unitPosition = building.getPosition();
        return e.getX() >= unitPosition.getX() + boundingBox.x && e.getY() >= unitPosition.getY() + boundingBox.y
            && e.getX() <= unitPosition.getX() + boundingBox.width
            && e.getY() <= unitPosition.getY() + boundingBox.height;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && isOnUnit(e.getPoint())) {
            building.doAction();
            building.setSelectOption(true);
        } else {
            building.setSelectOption(false);
        }
    }
}

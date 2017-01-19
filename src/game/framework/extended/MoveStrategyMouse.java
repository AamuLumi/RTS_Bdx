package game.framework.extended;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.entity.Building;
import game.entity.Factory;
import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Movable;
import gameframework.moves_rules.MoveStrategy;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

/**
 * {@link MoveStrategy} which listens to the mouse and answers new
 * {@link SpeedVector speed vectors} based on what the user typed.
 */
public class MoveStrategyMouse extends MouseAdapter implements MoveStrategy {
    protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0,
            0));

    private Rectangle boundingBox;
    private Movable unit;
    private boolean isSelected;
    private boolean isMovable;
    private Point destination;
    private Building building;

    public MoveStrategyMouse(GameMovable m){
        unit = m;
        isSelected = false;
        boundingBox = unit.getBoundingBox();
        isMovable = true;
    }

    public MoveStrategyMouse(Building b){
        building = b;
        boundingBox = building.getBoundingBox();
        isMovable = false;
    }

    public SpeedVector getSpeedVector() {
        setupVectorToGo(destination);
        return speedVector;
    }

    public boolean isOnUnit(Point e){
        Point unitPosition;
        if(isMovable) {
            unitPosition = unit.getPosition();
        } else {
            unitPosition = building.getPosition();
        }
        return e.getX() >= unitPosition.getX() + boundingBox.x &&
                e.getY() >= unitPosition.getY() + boundingBox.y &&
                e.getX() <= unitPosition.getX() + boundingBox.width &&
                e.getY() <= unitPosition.getY() + boundingBox.height;
    }

    public void setupVectorToGo(Point e){
        if (e == null) return;

        Point unitPosition = unit.getPosition();
        int x = 0, y = 0;


        if (e.getX() > unitPosition.getX() + boundingBox.getWidth()) {
            x = 1;
        } else if (e.getX() < unitPosition.getX() - boundingBox.getWidth()){
            x = -1;
        }

        if (e.getY() > unitPosition.getY() + boundingBox.getHeight()){
            y = 1;
        } else if (e.getY() < unitPosition.getY() - boundingBox.getHeight()){
            y = -1;
        }

        if (x == 0 && y == 0){
            destination = null;
        }

        speedVector.setDirection(new Point(x, y));
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(isMovable) {
            if (e.getButton() == MouseEvent.BUTTON1 && isOnUnit(e.getPoint())){
                this.isSelected = true;
            } else if (isSelected && e.getButton() == MouseEvent.BUTTON3){
                destination = e.getPoint();
            } else if (isSelected) {
                this.isSelected = false;
            }
        } else {
            if (e.getButton() == MouseEvent.BUTTON1 && isOnUnit(e.getPoint())){
                this.building.doAction();
            }
        }
    }
}

package game.entity;

import gameframework.core.*;

import java.awt.*;

public class Vehicle extends GameMovable implements Drawable, GameEntity,
    Overlappable {
    protected final SpriteManager spriteManager;
    public static final int RENDERING_SIZE = 16;
    protected boolean movable = true;
    protected boolean vulnerable = false;
    protected int vulnerableTimer = 0;

    public Vehicle(Canvas defaultCanvas) {
        spriteManager = new SpriteManagerDefaultImpl("images/militaryvehicle.gif",
            defaultCanvas, RENDERING_SIZE, 4);
        spriteManager.setTypes("idle", "right", "down", "up", "left");
    }

    public void draw(Graphics g) {
        String spriteType = "";
        Point tmp = getSpeedVector().getDirection();
        movable = true;

        if (tmp.getX() == 1) {
            spriteType += "right";
        } else if (tmp.getX() == -1) {
            spriteType += "left";
        } else if (tmp.getY() == 1) {
            spriteType += "down";
        } else if (tmp.getY() == -1) {
            spriteType += "up";
        } else {
            spriteType = "idle";
            spriteManager.reset();
            movable = false;
        }
        spriteManager.setType(spriteType);
        spriteManager.draw(g, getPosition());
    }

    @Override
    public void oneStepMoveAddedBehavior() {
        if (movable) {
            spriteManager.increment();
        }
    }

    public Rectangle getBoundingBox() {
        return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
    }
}

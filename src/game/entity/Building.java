package game.entity;

import java.awt.*;

/**
 * Created by Jean on 19/01/2017.
 */
public interface Building {
    public Rectangle getBoundingBox();
    public void doAction();
    public Point getPosition();
}

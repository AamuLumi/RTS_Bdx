package game.entity;

import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;

import java.awt.*;

public class Ground implements Drawable, GameEntity {
    protected static DrawableImage image = null;
    private int x, y;
    public static final int RENDERING_SIZE = 16;

    public Ground(Canvas defaultCanvas, int xx, int yy) {
        image = new DrawableImage("images/ground.gif", defaultCanvas);
        x = xx;
        y = yy;
    }

    public void draw(Graphics g) {
        g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
            null);
    }

    public Point getPosition() {
        return (new Point(x, y));
    }

    public Rectangle getBoundingBox() {
        return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
    }
}

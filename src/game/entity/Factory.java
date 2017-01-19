package game.entity;

import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;
import gameframework.moves_rules.MoveBlocker;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Factory implements Drawable, GameEntity, Building {
    protected static DrawableImage image = null;
    int x, y;
    public static final int RENDERING_SIZE = 16;

    public Factory(Canvas defaultCanvas, int xx, int yy) {
        image = new DrawableImage("images/factory.gif", defaultCanvas);
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
        return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
    }

    @Override
    public void doAction() {
        System.out.println("Building action");
    }
}

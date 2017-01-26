package game.entity;

import game.Main;
import game.framework.extended.UniqueGameUniverse;
import game.framework.extended.UnitFactory;
import game.tools.UnitBuildFunction;
import gameframework.core.Drawable;
import gameframework.core.DrawableImage;
import gameframework.core.GameEntity;
import gameframework.moves_rules.MoveBlocker;

import java.awt.*;

public class Garage implements Drawable, Building {
    protected DrawableImage image = null;
    protected static DrawableImage imageSelected = null;
    private int x, y;
    public static final int RENDERING_SIZE = 16;
    public static final int BUILD_TIME = 2000;
    private Runnable builder;
    private Thread builderThread;
    private boolean isSelected;

    public Garage(Canvas defaultCanvas, int xx, int yy, UnitFactory f, String imgPath) {
        image = new DrawableImage(imgPath, defaultCanvas);
        imageSelected = new DrawableImage("images/airport_selected.gif", defaultCanvas);
        x = xx;
        y = yy;
        
        isSelected = false;

        builder = () -> {
            try {
                Thread.sleep(BUILD_TIME);
                f.addVehicle(x / Main.SPRITE_SIZE + 1, y / Main.SPRITE_SIZE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        builderThread = new Thread(builder);
    }


    public void draw(Graphics g) {
        if (isSelected) {
            g.drawImage(imageSelected.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
                null);
        } else {
            g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
                null);
        }
    }

    public Point getPosition() {
        return (new Point(x, y));
    }

    public Rectangle getBoundingBox() {
        return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
    }

    @Override
    public void doAction() {
        System.out.println("Creating vehicle");
        if (!builderThread.isAlive()) {
            builderThread = new Thread(builder);
            builderThread.start();
        }
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelectOption(boolean isSelected) {
        this.isSelected = isSelected;
    }
}

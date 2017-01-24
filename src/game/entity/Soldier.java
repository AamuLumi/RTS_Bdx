package game.entity;

import gameframework.core.Drawable;
import gameframework.core.GameEntity;
import gameframework.core.GameMovable;
import gameframework.core.Overlappable;
import gameframework.core.SpriteManager;
import gameframework.core.SpriteManagerDefaultImpl;
import soldier.core.UnitSimple;
import soldier.units.UnitSoldier;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Soldier extends GameMovable implements Drawable, GameEntity, Overlappable {
	public static final int RENDERING_SIZE = 16;

	protected final SpriteManager spriteManager;

	protected boolean movable = true;
	protected boolean vulnerable = false;
	protected int vulnerableTimer = 0;
	protected UnitSimple core;

	public Soldier(Canvas defaultCanvas, String imgPath) {
		spriteManager = new SpriteManagerDefaultImpl(imgPath, defaultCanvas, RENDERING_SIZE, 4);
		spriteManager.setTypes("idle", "right", "down", "up", "left");
		
		core = new UnitSoldier("soldier");
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
	
	public UnitSimple getCore(){
		return this.core;
	}
}

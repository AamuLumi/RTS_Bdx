package game.framework.extended;

import java.awt.Canvas;
import java.awt.Point;

import game.Main;
import game.entity.Factory;
import game.entity.Soldier;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.GameUniverse;
import gameframework.core.GameUniverseDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.OverlapProcessor;

public class UniqueGameUniverse extends GameUniverseDefaultImpl{
	
	private static UniqueGameUniverse instance;
	private Canvas canvas;

	private UniqueGameUniverse(MoveBlockerChecker obs, OverlapProcessor col) {
		super(obs, col);
	}
	
	public static UniqueGameUniverse getInstance(){
		return instance;
	}
	
	public static void init(MoveBlockerChecker obs, OverlapProcessor col){
		instance = new UniqueGameUniverse(obs, col);
	}
	
	public void setupCanvas(Canvas c){
		this.canvas = c;
	}
	
	public synchronized void addSoldier(int x, int y){
		GameMovableDriverDefaultImpl soldierDriver = new GameMovableDriverDefaultImpl();
		Soldier currentSoldier = new Soldier(canvas);
		MoveStrategyMouse mouseStr = new MoveStrategyMouse(currentSoldier);
		
        soldierDriver.setStrategy(mouseStr);
        canvas.addMouseListener(mouseStr);

        currentSoldier.setDriver(soldierDriver);
        currentSoldier.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));
        
        this.addGameEntity(currentSoldier);
	}

	public void addFactory(int x, int y){
		Factory soldierFactory = new Factory(canvas, x*Main.SPRITE_SIZE, y*Main.SPRITE_SIZE);
        BuildStrategyMouse buildingStr = new BuildStrategyMouse(soldierFactory);
        
        canvas.addMouseListener(buildingStr);
        
        this.addGameEntity(soldierFactory);
	}
}

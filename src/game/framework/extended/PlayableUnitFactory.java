package game.framework.extended;

import java.awt.Canvas;
import java.awt.Point;

import game.Main;
import game.entity.Barrack;
import game.entity.Garage;
import game.entity.Soldier;
import game.entity.Vehicle;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.GameUniverse;

public class PlayableUnitFactory implements UnitFactory {

	private Canvas canvas;
	private GameUniverse gameUniverse;
	private Team team;

	public PlayableUnitFactory(GameUniverse g) {
		this.gameUniverse = g;
	}

	public void setupCanvas(Canvas c) {
		this.canvas = c;
	}

	public void setTeam(Team t) {
		this.team = t;
	}

	public synchronized void addSoldier(int x, int y) {
		GameMovableDriverDefaultImpl soldierDriver = new GameMovableDriverDefaultImpl();
		Soldier currentSoldier = new Soldier(canvas, "images/soldier.gif");

		currentSoldier.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

		MoveStrategyMouse mouseStr = new MoveStrategyMouse(currentSoldier);

		soldierDriver.setStrategy(mouseStr);
		canvas.addMouseListener(mouseStr);

		currentSoldier.setDriver(soldierDriver);

		team.addUnit(currentSoldier.getCore());

		gameUniverse.addGameEntity(currentSoldier);
	}

	public synchronized void addVehicle(int x, int y) {
		GameMovableDriverDefaultImpl vehicleDriver = new GameMovableDriverDefaultImpl();
		Vehicle currentVehicle = new Vehicle(canvas, "images/militaryvehicle.gif");
		MoveStrategyMouse mouseStr = new MoveStrategyMouse(currentVehicle);

		vehicleDriver.setStrategy(mouseStr);
		canvas.addMouseListener(mouseStr);

		currentVehicle.setDriver(vehicleDriver);
		currentVehicle.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

		team.addUnit(currentVehicle.getCore());

		gameUniverse.addGameEntity(currentVehicle);
	}

	public void addBarrack(int x, int y) {
		Barrack soldierBarrack = new Barrack(canvas, x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE, this,
				"images/factory.gif");
		BuildStrategyMouse buildingStr = new BuildStrategyMouse(soldierBarrack);

		canvas.addMouseListener(buildingStr);

		gameUniverse.addGameEntity(soldierBarrack);
	}

	public void addGarage(int x, int y) {
		Garage vehicleGarage = new Garage(canvas, x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE, this,
				"images/airport.gif");
		BuildStrategyMouse buildingStr = new BuildStrategyMouse(vehicleGarage);

		canvas.addMouseListener(buildingStr);

		gameUniverse.addGameEntity(vehicleGarage);
	}

}

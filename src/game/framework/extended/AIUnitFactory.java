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

public class AIUnitFactory implements UnitFactory {

	private Canvas canvas;
	private GameUniverse gameUniverse;
	private Team team;

	public AIUnitFactory(GameUniverse g) {
		this.gameUniverse = g;
	}

	public void setupCanvas(Canvas c) {
		this.canvas = c;
	}

	public void setTeam(Team t) {
		this.team = t;
	}

	public synchronized void addSoldier(int x, int y) {
		Soldier currentSoldier = new Soldier(canvas, "images/soldier_ai.gif");

		currentSoldier.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

		team.addUnit(currentSoldier.getCore());

		gameUniverse.addGameEntity(currentSoldier);
	}

	public synchronized void addVehicle(int x, int y) {
		Vehicle currentVehicle = new Vehicle(canvas, "images/militaryvehicle_ai.gif");

		currentVehicle.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

		team.addUnit(currentVehicle.getCore());

		gameUniverse.addGameEntity(currentVehicle);
	}

	public void addBarrack(int x, int y) {
		Barrack soldierBarrack = new Barrack(canvas, x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE, this,
				"images/factory_ai.gif");

		gameUniverse.addGameEntity(soldierBarrack);
	}

	public void addGarage(int x, int y) {
		Garage vehicleGarage = new Garage(canvas, x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE, this,
				"images/airport_ai.gif");

		gameUniverse.addGameEntity(vehicleGarage);
	}

}

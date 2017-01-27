package game.framework.extended;

import java.awt.Canvas;
import java.awt.Point;

import game.Main;
import game.entity.Barrack;
import game.entity.Garage;
import game.entity.Soldier;
import game.entity.Vehicle;
import gameframework.core.GameMovableDriverDefaultImpl;
import soldier.core.AgeAbstractFactory;

public class PlayableUnitFactory implements UnitFactory {

    private Canvas canvas;
    private GameUniverseRTS gameUniverse;
    private Team team;
    private AgeAbstractFactory age;

    public PlayableUnitFactory(GameUniverseRTS g, AgeAbstractFactory a) {
        this.gameUniverse = g;
        this.age = a;
    }

    public void setupCanvas(Canvas c) {
        this.canvas = c;
    }

    public void setTeam(Team t) {
        this.team = t;
    }

    @Override
    public void setAge(AgeAbstractFactory a) {
        this.age = a;
    }

    public synchronized void addSoldier(int x, int y) {
        GameMovableDriverDefaultImpl soldierDriver = new GameMovableDriverDefaultImpl();
        Soldier currentSoldier = new Soldier(canvas, "images/soldier.gif");
        currentSoldier.addEquipment(age.attackWeapon());

        currentSoldier.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

        MoveStrategyMouse mouseStr = new MoveStrategyMouse(currentSoldier);

        soldierDriver.setStrategy(mouseStr);
        soldierDriver.setmoveBlockerChecker(gameUniverse.getMoveBlockerChecker());
        canvas.addMouseListener(mouseStr);

        currentSoldier.setDriver(soldierDriver);
        currentSoldier.setDestruct(() -> canvas.removeMouseListener(mouseStr));

        team.addUnit(currentSoldier.getCore());

        gameUniverse.addGameEntity(currentSoldier);
    }

    public synchronized void addVehicle(int x, int y) {
        GameMovableDriverDefaultImpl vehicleDriver = new GameMovableDriverDefaultImpl();
        Vehicle currentVehicle = new Vehicle(canvas, "images/militaryvehicle.gif");
        currentVehicle.addEquipment(age.attackWeapon());
        currentVehicle.addEquipment(age.defenseWeapon());

        MoveStrategyMouse mouseStr = new MoveStrategyMouse(currentVehicle);

        vehicleDriver.setStrategy(mouseStr);
        vehicleDriver.setmoveBlockerChecker(gameUniverse.getMoveBlockerChecker());
        canvas.addMouseListener(mouseStr);

        currentVehicle.setDriver(vehicleDriver);
        currentVehicle.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));
        currentVehicle.setDestruct(() -> canvas.removeMouseListener(mouseStr));

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

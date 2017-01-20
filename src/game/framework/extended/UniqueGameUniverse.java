package game.framework.extended;

import java.awt.Canvas;
import java.awt.Point;

import game.Main;
import game.entity.Barrack;
import game.entity.Garage;
import game.entity.Soldier;
import game.entity.Vehicle;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.GameUniverseDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.OverlapProcessor;

public class UniqueGameUniverse extends GameUniverseDefaultImpl {

    private static UniqueGameUniverse instance;
    private Canvas canvas;

    private UniqueGameUniverse(MoveBlockerChecker obs, OverlapProcessor col) {
        super(obs, col);
    }

    public static UniqueGameUniverse getInstance() {
        return instance;
    }

    public static void init(MoveBlockerChecker obs, OverlapProcessor col) {
        instance = new UniqueGameUniverse(obs, col);
    }

    public void setupCanvas(Canvas c) {
        this.canvas = c;
    }

    public synchronized void addSoldier(int x, int y) {
        GameMovableDriverDefaultImpl soldierDriver = new GameMovableDriverDefaultImpl();
        Soldier currentSoldier = new Soldier(canvas);
        MoveStrategyMouse mouseStr = new MoveStrategyMouse(currentSoldier);

        soldierDriver.setStrategy(mouseStr);
        canvas.addMouseListener(mouseStr);

        currentSoldier.setDriver(soldierDriver);
        currentSoldier.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

        this.addGameEntity(currentSoldier);
    }

    public synchronized void addVehicle(int x, int y) {
        GameMovableDriverDefaultImpl vehicleDriver = new GameMovableDriverDefaultImpl();
        Vehicle currentVehicle = new Vehicle(canvas);
        MoveStrategyMouse mouseStr = new MoveStrategyMouse(currentVehicle);

        vehicleDriver.setStrategy(mouseStr);
        canvas.addMouseListener(mouseStr);

        currentVehicle.setDriver(vehicleDriver);
        currentVehicle.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

        this.addGameEntity(currentVehicle);
    }

    public void addBarrack(int x, int y) {
        Barrack soldierBarrack = new Barrack(canvas, x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE);
        BuildStrategyMouse buildingStr = new BuildStrategyMouse(soldierBarrack);

        canvas.addMouseListener(buildingStr);

        this.addGameEntity(soldierBarrack);
    }

    public void addGarage(int x, int y) {
        Garage vehicleGarage = new Garage(canvas, x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE);
        BuildStrategyMouse buildingStr = new BuildStrategyMouse(vehicleGarage);

        canvas.addMouseListener(buildingStr);

        this.addGameEntity(vehicleGarage);
    }
}

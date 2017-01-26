package game.framework.extended;

import game.Main;
import game.entity.Barrack;
import game.entity.Garage;
import game.entity.Soldier;
import game.entity.Vehicle;
import game.rule.AIMovableDriver;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.moves_rules.MoveStrategyRandom;
import soldier.core.AgeAbstractFactory;

import java.awt.*;

public class AIUnitFactory implements UnitFactory {

    private Canvas canvas;
    private GameUniverseRTS gameUniverse;
    private Team team;
    private AgeAbstractFactory age;

    public AIUnitFactory(GameUniverseRTS g, AgeAbstractFactory age) {
        this.gameUniverse = g;
        this.age = age;
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
        Soldier currentSoldier = new Soldier(canvas, "images/soldier_ai.gif");
        currentSoldier.addEquipment(age.attackWeapon());

        currentSoldier.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

        team.addUnit(currentSoldier.getCore());

        GameMovableDriverDefaultImpl aiDriver = new AIMovableDriver();
        MoveStrategyRandom ranStr = new MoveStrategyRandom();
        aiDriver.setStrategy(ranStr);
        aiDriver.setmoveBlockerChecker(gameUniverse.getMoveBlockerChecker());
        currentSoldier.setDriver(aiDriver);

        gameUniverse.addGameEntity(currentSoldier);
    }

    public synchronized void addVehicle(int x, int y) {
        Vehicle currentVehicle = new Vehicle(canvas, "images/militaryvehicle_ai.gif");
        currentVehicle.addEquipment(age.attackWeapon());

        currentVehicle.setPosition(new Point(x * Main.SPRITE_SIZE, y * Main.SPRITE_SIZE));

        team.addUnit(currentVehicle.getCore());

        GameMovableDriverDefaultImpl aiDriver = new AIMovableDriver();
        MoveStrategyRandom ranStr = new MoveStrategyRandom();
        aiDriver.setStrategy(ranStr);
        aiDriver.setmoveBlockerChecker(gameUniverse.getMoveBlockerChecker());
        currentVehicle.setDriver(aiDriver);

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

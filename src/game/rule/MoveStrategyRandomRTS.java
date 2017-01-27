package game.rule;

import gameframework.moves_rules.MoveStrategy;
import gameframework.moves_rules.SpeedVector;
import gameframework.moves_rules.SpeedVectorDefaultImpl;

import java.awt.*;
import java.util.Random;

public class MoveStrategyRandomRTS implements MoveStrategy {
    private int stepCounter;
    private int steps;
    private Point direction;

    public MoveStrategyRandomRTS(int steps) {
        this.stepCounter = 0;
        this.steps = steps;
        this.direction = new Point(0, 0);
    }

    SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0));
    static Random random = new Random();

    public SpeedVector getSpeedVector() {
        stepCounter++;
        if (stepCounter % steps == 0) {
            int diceMoveOrStop = random.nextInt(8);
            if (diceMoveOrStop == 0) {
                // Move unit
                int diceDirection = random.nextInt(4);
                switch (diceDirection) {
                    case 0:
                        direction = new Point(1, 0);
                        break;
                    case 1:
                        direction = new Point(-1, 0);
                        break;
                    case 2:
                        direction = new Point(0, -1);
                        break;
                    case 3:
                        direction = new Point(0, 1);
                        break;
                }
            } else {
                // Stop unit
                direction = new Point(0, 0);
            }
        }
        currentMove.setDirection(direction);
        return currentMove;
    }
}

package game.rule;

import gameframework.core.GameUniverse;
import gameframework.core.ObservableValue;
import gameframework.moves_rules.Overlap;
import gameframework.moves_rules.OverlapRulesApplierDefaultImpl;

import java.util.Vector;

import game.entity.MilitaryUnit;
import game.entity.Soldier;
import game.entity.Vehicle;
import game.framework.extended.Team;

public class GameOverlapRules extends OverlapRulesApplierDefaultImpl {
    protected GameUniverse universe;

    private final ObservableValue<Integer> score;
    private final ObservableValue<Integer> life;
    private final ObservableValue<Boolean> endOfGame;
    private Team playableTeam;
    private Team aiTeam;

    public GameOverlapRules(ObservableValue<Integer> life, ObservableValue<Integer> score,
                            ObservableValue<Boolean> endOfGame) {
        this.life = life;
        this.score = score;
        this.endOfGame = endOfGame;

        this.score.setValue(0);
        this.life.setValue(1);
    }

    public void setTeams(Team t1, Team t2) {
        this.playableTeam = t1;
        this.aiTeam = t2;
    }

    public void setUniverse(GameUniverse universe) {
        this.universe = universe;
    }

    public boolean hasDifferentTeam(MilitaryUnit m1, MilitaryUnit m2) {
        return (playableTeam.containsUnit(m1.getCore()) && aiTeam.containsUnit(m2.getCore())) ||
            (aiTeam.containsUnit(m1.getCore()) && aiTeam.containsUnit(m1.getCore()));
    }

    public void attack(MilitaryUnit m1, MilitaryUnit m2) {
        float m1Attack = m1.getCore().strike();
        float m2Attack = m2.getCore().strike();

        m1.getCore().parry(m2Attack);
        m2.getCore().parry(m1Attack);

        if (!m1.getCore().alive()) {
            playableTeam.removeUnit(m1);
            aiTeam.removeUnit(m1);
            System.out.println("Event: A unit died");
        }
        if (!m2.getCore().alive()) {
            playableTeam.removeUnit(m2);
            aiTeam.removeUnit(m2);
            System.out.println("Event: A unit died");
        }

        if (!(playableTeam.alive() && aiTeam.alive())) {
            if (playableTeam.alive()) {
                System.out.println("Game Over. You win!");
            } else {
                System.out.println("Game Over. You lose!");
                life.setValue(0);
            }
            endOfGame.setValue(true);
        }
    }

    @Override
    public void applyOverlapRules(Vector<Overlap> overlappables) {
        super.applyOverlapRules(overlappables);
    }

    public void overlapRule(Soldier m1, Soldier m2) {
        if (hasDifferentTeam(m1, m2)) {
            attack(m1, m2);
        }
    }

    public void overlapRule(Vehicle m1, Soldier m2) {
        if (hasDifferentTeam(m1, m2)) {
            attack(m1, m2);
        }
    }

    public void overlapRule(Vehicle m1, Vehicle m2) {
        if (hasDifferentTeam(m1, m2)) {
            attack(m1, m2);
        }
    }
}

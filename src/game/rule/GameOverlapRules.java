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
    private Team t1;
    private Team t2;

    public GameOverlapRules(ObservableValue<Integer> life, ObservableValue<Integer> score,
                            ObservableValue<Boolean> endOfGame) {
        this.life = life;
        this.score = score;
        this.endOfGame = endOfGame;
    }

    public void setTeams(Team t1, Team t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public void setUniverse(GameUniverse universe) {
        this.universe = universe;
    }

    public boolean hasDifferentTeam(MilitaryUnit m1, MilitaryUnit m2) {
        return (t1.containsUnit(m1.getCore()) && t2.containsUnit(m2.getCore())) ||
            (t2.containsUnit(m1.getCore()) && t2.containsUnit(m1.getCore()));
    }

    public void attack(MilitaryUnit m1, MilitaryUnit m2) {
        float m1Attack = m1.getCore().strike();
        float m2Attack = m2.getCore().strike();

        m1.getCore().parry(m2Attack);
        m2.getCore().parry(m1Attack);

        if (!m1.getCore().alive()) {
            t1.removeUnit(m1);
            t2.removeUnit(m1);
            System.out.println("Event : Une unité est décédée");
        }
        if (!m2.getCore().alive()) {
            t1.removeUnit(m2);
            t2.removeUnit(m2);
            System.out.println("Event : Une unité est décédée");
        }

        if (!(t1.alive() && t2.alive())) {
            System.out.println("The end ... " + (t1.alive() ? t1.getName() : t2.getName()) + " won.");
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

    private void checkTeamIsAlive() {

    }
}

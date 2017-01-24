package game.framework.extended;

import java.awt.Canvas;

public interface UnitFactory {
	public static boolean PLAYER = true;
	public static boolean AI = false;
	
	public void setupCanvas(Canvas c);
	public void setTeam(Team t);
	public void addSoldier(int x, int y);
	public void addVehicle(int x, int y);
	public void addBarrack(int x, int y);
	public void addGarage(int x, int y);
}

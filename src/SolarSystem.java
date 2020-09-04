import java.awt.Color;
import java.awt.Graphics;

public class SolarSystem 
{
	Planet planet;
	Sun sun;
	Moon moon;

	private static final double moveTime = 1800; //in seconds
	private static double totalTime = 0;
	
	public static double getTotalTime() {
		return totalTime;
	}

	public static void setTotalTime(double totalTime) {
		SolarSystem.totalTime = totalTime;
	}

	public static double getMovetime() {
		return moveTime;
	}

	public SolarSystem(Sun s, Planet p, Moon m)
	{
		sun = s;
		planet = p;
		moon = m;
	}
	
	public void run(boolean toRun, boolean planetOrbit, boolean moonOrbit, Graphics g)
	{
		g.setColor(Color.WHITE);
		
		if(planetOrbit == true)
		{
			planet.predictOrbit(g, moveTime);
		}
		
		if(moonOrbit == true)
		{
			moon.predictOrbit(g, moveTime);
		}
		
		
		if(toRun == false)
		{
			sun.draw(g);
			planet.draw(g);
			moon.draw(g);
		}
		else
		{
			planet.move(moveTime);
			moon.move(moveTime);
			sun.draw(g);
			planet.draw(g);
			moon.draw(g);
			totalTime = totalTime + moveTime;
		}
	}
}

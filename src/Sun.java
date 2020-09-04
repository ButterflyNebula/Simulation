import java.awt.Color;
import java.awt.Graphics;

public class Sun extends SpaceBody
{
	public static double theScale = 20;
	
	public Sun(double m , double r, Coordinate pos , Color c)
	{
		super(m, r, pos, c);
	}
	
	public void draw(Graphics g)
	{
		//Scale the radius
		double rad = getRadius() / Coordinate.scale;
		
		rad = rad * theScale;
		
		Draw.drawCircle(g, this.getPosition(), rad, this.getColor());
		
	}
}

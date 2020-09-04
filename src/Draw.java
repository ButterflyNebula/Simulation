import java.awt.Color;
import java.awt.Graphics;

public class Draw 
{
	public static void drawCircle(Graphics g, Coordinate center , double rad, Color c)
	{
		double x =  center.getX() - rad;
		double y =  center.getY() - rad;
		
		g.setColor(c);
		
		
		g.fillOval((int) x, (int) y, (int) (rad * 2), (int) (rad * 2));
		
	}
	
	
	//FOR GRAPHIC COORDINATES ONLY!!!!!
	public static  void connect(Graphics g, Coordinate x, Coordinate y)
	{
		g.drawLine((int) x.getX(), (int)x.getY(), (int) y.getX(), (int) y.getY()); 
	}
}

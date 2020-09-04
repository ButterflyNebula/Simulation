import java.awt.Color;
import java.awt.Graphics;

public abstract class SpaceBody 
{
	public static final double G = 6.67408 * Math.pow(10, -11);
	
	
	private double mass;
	private double radius;
	private Coordinate position;
	private Color color;
	
	public SpaceBody(double m , double r, Coordinate pos , Color c)
	{
		mass = m;
		radius = r;
		position = pos;
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public abstract void draw(Graphics g);
}

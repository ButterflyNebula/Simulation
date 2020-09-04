
public class Vector
{
	private double X;
	private double Y;
	
	public Vector(double x, double y)
	{
		X = x;
		Y = y;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}
	
	public String toString()
	{
		return "(" + X + ", " + Y + ")";
	}
}

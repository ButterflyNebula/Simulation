
public class Coordinate 
{
	public static final double scale = 600000;
	
	
	private double X;
	private double Y;
	
	public Coordinate(double x, double y)
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
	
	/**
	 * 
	 * CONVERSIONS
	 */
	//Solar to Graphic
	public static Coordinate solarToGraphic(Sun s, Coordinate c)
	{
		//Change distance by scale
		double x = c.getX()/scale;
		double y = c.getY()/scale;
		
		x = s.getPosition().X + x;
		y = s.getPosition().Y + y;
		
		Coordinate newCoordinate = new Coordinate (x,y);
		
		return newCoordinate;
	}
	
	//Takes moon relPos where the units is Kilometers
	public static Coordinate lunarToGraphic(Planet p , double moonZoom , Coordinate c)
	{
		double x = p.getPosition().getX();
		double y = p.getPosition().getY();
		
		double moonX = moonZoom * c.getX();
		double moonY = moonZoom * c.getY();//blow up the moon distance a lot
		
		//back to scaled size
		moonX = moonX /scale;
		moonY = moonY / scale;
		
		x = x + moonX;
		y = y + moonY;
		
		return new Coordinate (x,y);
	}
	
	//Changes from the planet-centric system to sun-centric
	public static Coordinate lunarToSolar(Planet p , Coordinate c)
	{
		double x = p.getRelPos().getX() + c.getX();
		double y = p.getRelPos().getY() + c.getY();
		
		return new Coordinate (x, y);
	}
	
	//Changing to Meters
	public static Coordinate kMToMeters(Coordinate km)
	{
		double mX =  km.getX() * Math.pow(10, 3);
		double mY = km.getY() * Math.pow(10, 3);
		
		return new Coordinate (mX, mY);
	}
	
	//Back to kMeters
	public static Coordinate metersToKm(Coordinate m)
	{
		double kmX =  m.getX() / Math.pow(10, 3);
		double kmY = m.getY() / Math.pow(10, 3);
		
		return new Coordinate (kmX, kmY);
	}
}

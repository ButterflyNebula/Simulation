import java.awt.Color;
import java.awt.Graphics;

public class Moon extends SpaceBody
{
	private Coordinate relPos;  //kilometers (this is the coordinate where the earth is (0,0)
	private Coordinate solarPos; //kilometers (this is the coordinate where the sun is (0,0)
	private Coordinate metersPos; //meters (relPos in meters)
	private Vector velocity; //meters per second
	private Vector acceleration; //meters per second squared
	private	Planet planet;
	
	public static final double theScale = 2000;
	
	public static final double posScale = 50;
	
	public Moon(Planet p, double radius , double mass, double apogeeDistance, double apogeeSpeed , Color c)
	{
		super(mass, radius, Coordinate.lunarToGraphic(p, posScale, new Coordinate(-apogeeDistance , 0)) , c);
		
		planet = p;
		
		relPos = new Coordinate(-apogeeDistance, 0);
		solarPos = Coordinate.lunarToSolar(planet, relPos);
		metersPos = Coordinate.kMToMeters(relPos);
		
		velocity = new Vector(0, apogeeSpeed);
		acceleration = findAcceleration();
		
	}
	
	//Getters and Setters
	public Coordinate getRelPos() 
	{
		return relPos;
	}

	public void setRelPos(Coordinate relPos) 
	{
		this.relPos = relPos;
	}

	public Coordinate getSolarPos() 
	{
		return solarPos;
	}

	public void setSolarPos(Coordinate solarPos) 
	{
		this.solarPos = solarPos;
	}

	public Coordinate getMetersPos() 
	{
		return metersPos;
	}

	public void setMetersPos(Coordinate metersPos) 
	{
		this.metersPos = metersPos;
	}

	public Vector getVelocity() 
	{
		return velocity;
	}

	public void setVelocity(Vector velocity) 
	{
		this.velocity = velocity;
	}

	public Vector getAcceleration() 
	{
		return acceleration;
	}

	public void setAcceleration(Vector acceleration) 
	{
		this.acceleration = acceleration;
	}

	public Planet getPlanet() 
	{
		return planet;
	}

	public void setPlanet(Planet planet) 
	{
		this.planet = planet;
	}

	//Distance to the Planet
	public double distanceToPlanet()
	{
		double square = Math.pow(metersPos.getX(), 2) + Math.pow(metersPos.getY(), 2);
		
		return Math.sqrt(square);
	}
	
	public double distanceToSun(Coordinate moonPos)
	{
		double square = Math.pow(moonPos.getX(), 2) + Math.pow(moonPos.getY(), 2);
		
		return Math.sqrt(square);
	}
	
	//Find the Acceleration
	public Vector findAcceleration()
	{
		double xAcc;
		
	//	double numerator = G * planet.getMass() * getMass() * (metersPos.getX());
		
		double numerator = G * planet.getMass()  * (metersPos.getX());

		
		double denominator = Math.pow(distanceToPlanet() , 3);
		
		xAcc = numerator / denominator;
		
		
		double yAcc;
	//	double numerator2 = G * planet.getMass() * getMass() * (metersPos.getY());
		
		double numerator2 = G * planet.getMass() * (metersPos.getY());
		
		double denominator2 = Math.pow(distanceToPlanet() , 3);
		
		yAcc = numerator2 / denominator2;
		
		Vector theAcceleration = new Vector(xAcc , yAcc);
		
		return theAcceleration;
	}
	
	    //Calculate New Position
		public Coordinate newPos(double moveTime)
		{
			double newPosX;
			double newPosY;
			
			newPosX = metersPos.getX() + (velocity.getX() * moveTime) + (0.5 * acceleration.getX() * Math.pow(moveTime, 2)); //Check this
			newPosY = metersPos.getY()- (velocity.getY() * moveTime) + (0.5 * acceleration.getY() * Math.pow(moveTime, 2));

			return new Coordinate(newPosX, newPosY);
		}
		
		
		//Calculate new velocity
		public Vector newVel(double moveTime)
		{
			double newVelX;
			double newVelY;
			
			newVelX = velocity.getX() - (acceleration.getX() * moveTime);
			newVelY = velocity.getY() + (acceleration.getY() * moveTime); 
			
			return new Vector(newVelX, newVelY);

		}
		
		//Move
		public void move(double moveTime)
		{
			metersPos = newPos(moveTime);
			velocity = newVel(moveTime);
			resetParameters();
		}
		
		
		//Reset Parameters
		public void resetParameters()
		{
			relPos = Coordinate.metersToKm(metersPos);
			solarPos = Coordinate.lunarToSolar(planet, relPos);
			this.setPosition(Coordinate.lunarToGraphic(planet, posScale, relPos));
			acceleration = findAcceleration();
		}
	
	
	public void draw(Graphics g)
	{
		//Get moon Graphic coordinates
		Coordinate pos = Coordinate.lunarToGraphic(planet, posScale, relPos);
		
		//scale moonSize
		double rad = getRadius() / Coordinate.scale;
		rad = rad * theScale;
				
		Draw.drawCircle(g, pos, rad, getColor());
	}
	
	
	public void predictOrbit(Graphics g, double moveTime)
	{
		double totalTime = 0;
		double targetTime = 2419200 * 1.05; //multiply by 1.01 to make sure it completes a full orbit
		
		int slots = (int) (targetTime / moveTime);
		
		Coordinate currentPos = metersPos;
		Vector currentVel = velocity;
		Coordinate endPos;
		Vector newVel;
		
		Coordinate [] points = new Coordinate[slots];
		points[0] = currentPos;
		
		
		//Get all the points
		for(int i = 1; i < points.length; i++)
		{
			//Calculate Acceleration at this point
			double xAcc;
			double numerator = G * planet.getMass() * (currentPos.getX());
			double denominator = Math.pow(distanceToSun(currentPos) , 3);
			xAcc = numerator / denominator;
			double yAcc;
			double numerator2 = G * planet.getMass() * (currentPos.getY());
			double denominator2 = Math.pow(distanceToSun(currentPos) , 3);
			yAcc = numerator2 / denominator2;
			Vector theAcceleration = new Vector(xAcc , yAcc);
			
			//Calculate the new Position
			double newPosX;
			double newPosY;
			newPosX = currentPos.getX() + (currentVel.getX() * moveTime) + (0.5 * theAcceleration.getX() * Math.pow(moveTime, 2)); 
			newPosY = currentPos.getY()- (currentVel.getY() * moveTime) + (0.5 * theAcceleration.getY() * Math.pow(moveTime, 2));
			endPos =  new Coordinate(newPosX, newPosY);
			
			//Calculate the new Velocity
			double newVelX;
			double newVelY;
			newVelX = currentVel.getX() - (theAcceleration.getX() * moveTime);
			newVelY = currentVel.getY() + (theAcceleration.getY() * moveTime); 
			newVel = new Vector(newVelX, newVelY);
			
			
			//Add the next position and shift
			points[i] = endPos;
			currentPos = endPos;
			currentVel = newVel;
		}
		
		//Convert the first coordinate
		points[0] = Coordinate.metersToKm(points[0]);
		points[0] = Coordinate.lunarToGraphic(planet, posScale, points[0]);
		
		//Convert all the points and draw
		for(int i = 1 ; i < points.length; i++)
		{
			points[i] = Coordinate.metersToKm(points[i]);
			points[i] = Coordinate.lunarToGraphic(planet, posScale,  points[i]);
			
			Draw.connect(g, points[i], points[i-1]);
		}
	}
}

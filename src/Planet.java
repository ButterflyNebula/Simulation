import java.awt.Color;
import java.awt.Graphics;

public class Planet extends SpaceBody
{
	private Coordinate relPos;  //kilometers
	private Coordinate metersPos; //meters
	private Vector velocity; //meters per second
	private Vector acceleration; //meters per second squared
	private Sun sun;
	
	public static final double theScale = 1000;
	
	//Constructor
	public Planet(Sun s, double radius , double mass, double aphelionDistance, double aphelionSpeed , Color c) 
	{
		super(mass, radius , Coordinate.solarToGraphic(s, new Coordinate(-aphelionDistance , 0)), c);
		
		//Position
		relPos = new Coordinate(-aphelionDistance, 0);
		metersPos = Coordinate.kMToMeters(relPos);
		
		//Velocity
		velocity = new Vector(0, aphelionSpeed);
		
		sun = s;
		
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

	public Sun getSun() 
	{
		return sun;
	}

	public void setSun(Sun sun) 
	{
		this.sun = sun;
	}
	
	

	//Distance to Sun
	/**
	 * GIVES THE DISTANCE FROM THE EARTH TO THE SUN IN METERS
	 */
	public double distanceToSun()
	{
		double square = Math.pow(metersPos.getX(), 2) + Math.pow(metersPos.getY(), 2);
		
		return Math.sqrt(square);
	}
	
	//Earth pos should be in meters
	public double distanceToSun(Coordinate earthPos)
	{
		double square = Math.pow(earthPos.getX(), 2) + Math.pow(earthPos.getY(), 2);
		
		return Math.sqrt(square);
	}
	
	//Calculate Acceleration
	/**
	 * GIVES ACCELERATION IN METERS PER SECOND SQUARED
	 * @return
	 */
	public Vector findAcceleration()
	{
		double xAcc;
		
		double numerator = G * sun.getMass() * (metersPos.getX());
		
		double denominator = Math.pow(distanceToSun() , 3);
		
		xAcc = numerator / denominator;
		
		
		double yAcc;
		double numerator2 = G * sun.getMass() * (metersPos.getY());
		
		double denominator2 = Math.pow(distanceToSun() , 3);
		
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
		newPosY = metersPos.getY() - (velocity.getY() * moveTime) + (0.5 * acceleration.getY() * Math.pow(moveTime, 2));

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
		this.setPosition(Coordinate.solarToGraphic(sun, relPos));
		acceleration = findAcceleration();
	}
	
	
	public void draw(Graphics g)
	{
		//Scale the radius
		double rad = getRadius() / Coordinate.scale;
				
		rad = rad * theScale;
		
		Draw.drawCircle(g, this.getPosition(), rad, this.getColor());
	}

	
	public void predictOrbit(Graphics g, double moveTime)
	{
		double totalTime = 0;
		double targetTime = 3.154 * Math.pow(10, 7) * 1.01; //multiply by 1.01 to make sure it completes a full orbit
		
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
			double numerator = G * sun.getMass() * (currentPos.getX());
			double denominator = Math.pow(distanceToSun(currentPos) , 3);
			xAcc = numerator / denominator;
			double yAcc;
			double numerator2 = G * sun.getMass() * (currentPos.getY());
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
		points[0] = Coordinate.solarToGraphic(sun, points[0]);
		
		//Convert all the points and draw
		for(int i = 1 ; i < points.length; i++)
		{
			points[i] = Coordinate.metersToKm(points[i]);
			points[i] = Coordinate.solarToGraphic(sun, points[i]);
			
			Draw.connect(g, points[i], points[i-1]);
		}
	}

}

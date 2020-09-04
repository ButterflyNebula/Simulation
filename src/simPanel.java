import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class simPanel extends JPanel implements ActionListener
{
	 boolean toRun = true;	
	 boolean moonOrbit = false;
	 boolean planetOrbit = true;
	 
	 
	 double sunOrgMass = 1.989 * Math.pow(10, 30);
	 double planetOrgMass = 5.972 * Math.pow(10, 24);
	 
	 Coordinate planetOrgPos = new Coordinate(-152100000 , 0);
	 Vector planetOrgVel = new Vector(0, 29300);
	 
	 Coordinate moonOrgPos = new Coordinate(-405000 , 0);
	 Vector moonOrgVel = new Vector(0, 959.583);
	
	 Sun sun = new Sun (sunOrgMass, 695508, new Coordinate(500, 350) , Color.ORANGE);

	 Planet planet = new Planet(sun, 6371 , planetOrgMass , 152100000 , 29300 , Color.CYAN);
	 
	 Moon moon = new Moon(planet, 1737, 7.34767309 * Math.pow(10, 22) , 405000 , 959.583 , Color.LIGHT_GRAY);
	
	 SolarSystem system = new SolarSystem(sun, planet , moon);

	
	Timer time = new Timer(0, this);
	
	
	public void actionPerformed (ActionEvent e)
	{
		repaint();
	}
	
	
	public void paintComponent (Graphics g)
	{		
		super.paintComponent(g);
		
		system.run(toRun, planetOrbit, moonOrbit , g);
		
		time.start();
	}
}

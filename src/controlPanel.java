import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class controlPanel extends JPanel
{
	simPanel simulation;
	JButton drawMoonOrbit = new JButton("Show Moon Orbit");
	JButton stop = new JButton("STOP");
	JButton drawPlanetOrbit = new JButton("Hide\nPlanet Orbit");
	JButton go = new JButton("GO");
	JButton changeSunMass = new JButton("Change Sun's Mass");
	JButton changeEarthMass = new JButton("Change Planet's Mass");
	JButton changeEarthVel = new JButton("Change Planet's Velocity");
	JButton reset = new JButton("Reset");


	public controlPanel(simPanel sim)
	{
		simulation = sim;
	}
	
	
	public void paintComponent (Graphics g)
	{		
		super.paintComponent(g);
		
		
		Font font1 = new Font("Serif", Font.BOLD, 24);
		g.setFont(font1);
		
	    Font font2 = g.getFont().deriveFont( 60.0f );
	    g.setFont( font2 );
	    g.setColor(Color.BLUE);
		g.drawString("Controls", 700, 70);
		
		
		//Stop Button
		stop.setBounds(25, 100, 100, 100);
		stop.setBackground(Color.YELLOW);
		
		stop.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    simulation.toRun = false;
		  }
		});
		
		
		//Show Moon Orbit
		drawMoonOrbit.setBounds(150, 100, 200, 100);
		drawMoonOrbit.setBackground(Color.MAGENTA);
		
		drawMoonOrbit.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  simulation.moonOrbit = !(simulation.moonOrbit);
			  if(simulation.moonOrbit == true)
			  {
				  drawMoonOrbit.setText("Hide Moon Orbit");
			  }
			  else
			  {
				  drawMoonOrbit.setText("Show Moon Orbit");
			  }
		  }
		  
		});
		
		
		
		//Show Planet Orbit
		drawPlanetOrbit.setBounds(375, 100, 200, 100);
		drawPlanetOrbit.setBackground(Color.MAGENTA);
		
		drawPlanetOrbit.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  simulation.planetOrbit = !(simulation.planetOrbit);
			  if(simulation.planetOrbit == true)
			  {
				  drawPlanetOrbit.setText("Hide\nPlanet Orbit");
			  }
			  else
			  {
				  drawPlanetOrbit.setText("Show\nPlanet Orbit");
			  }
		  }
		});
		
		
		//Change Sun Mass
		changeSunMass.setBounds(600, 100, 200, 100);
		changeSunMass.setBackground(Color.PINK);
		
		changeSunMass.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  String input = JOptionPane.showInputDialog(null, "Enter New Mass in kg", simulation.sun.getMass());
			  
			  double mass = Double.valueOf(input);
			  
			  simulation.sun.setMass(mass);
		  }
		});
		
		
		//Change Earth Mass
		changeEarthMass.setBounds(825, 100, 200, 100);
		changeEarthMass.setBackground(Color.PINK);
		
		changeEarthMass.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  String input = JOptionPane.showInputDialog(null, "Enter New Mass in kg", simulation.planet.getMass());
			  
			  double mass = Double.valueOf(input);
			  
			  simulation.planet.setMass(mass);
		  }
		});
		
		
		//Change Earth Velocity
		changeEarthVel.setBounds(1050, 100, 200, 100);
		changeEarthVel.setBackground(Color.PINK);
		
		changeEarthVel.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  String input1 = JOptionPane.showInputDialog(null, "Enter New X-Velocity in Meters Per Second", simulation.planet.getVelocity().getX());
			  
			  double xVel = Double.valueOf(input1);
			  
			  String input2 = JOptionPane.showInputDialog(null, "Enter New Y-Velocity in Meters Per Second", simulation.planet.getVelocity().getY());
			  
			  double yVel = Double.valueOf(input1);
			  
			  simulation.planet.getVelocity().setX(xVel);
			  simulation.planet.getVelocity().setY(yVel);

		  }
		});
		
		
		//Reset
		reset.setBounds(1275, 100, 100, 100);
		reset.setBackground(Color.CYAN);
		
		reset.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  
			  //Create and entirely new planet
			  Sun sun = new Sun (simulation.sunOrgMass, 695508, new Coordinate(500, 350) , Color.ORANGE);

			  Planet planet = new Planet(sun, 6371 , simulation.planetOrgMass , 152100000 , 29300 , Color.CYAN);
				 
			  Moon moon = new Moon(planet, 1737, 7.34767309 * Math.pow(10, 22) , 405000 , 959.583 , Color.LIGHT_GRAY);
			  
			  simulation.sun = sun;
			  simulation.planet = planet;
			  simulation.moon = moon;
			  simulation.system = new SolarSystem(sun, planet, moon);
			  
			  simulation.system.setTotalTime(0.0);
			  
			  simulation.moonOrbit = false;
			  simulation.planetOrbit = true;
		  }
		});
		
		go.setBounds(1400, 100, 100, 100);
		go.setBackground(new Color(23, 178, 74));
		
		go.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    simulation.toRun = true;
		  }
		});
		
		
		this.add(drawPlanetOrbit);
		this.add(drawMoonOrbit);
		this.add(reset);
		this.add(changeEarthVel);
		this.add(changeEarthMass);
		this.add(changeSunMass);
		this.add(stop);
		this.add(go);
		
	}
}
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class sidePanel extends JPanel implements ActionListener
{
	simPanel simulation;
	
	Timer time = new Timer(0, this);
	
	public sidePanel(simPanel sim)
	{
		simulation = sim;
	}
	
	public void actionPerformed (ActionEvent e)
	{
		repaint();
	}
	
	public void paintComponent (Graphics g)
	{		
		super.paintComponent(g);	
		
		Font font1 = new Font("Serif", Font.BOLD, 24);
		g.setFont(font1);
		
	    Font font2 = g.getFont().deriveFont( 60.0f );
	    g.setFont( font2 );
	    g.setColor(Color.BLUE);
		g.drawString("Information", 80, 90);
		
		Font font3 = g.getFont().deriveFont( 15.0f );
	    g.setFont( font3 );
	    
		g.drawString("Time: " + simulation.system.getTotalTime() / 3600 + " hours", 20, 175);

		g.drawString("Planet Current Position (in millions of kilometers):", 20, 225);
		g.drawString(simulation.planet.getRelPos().toString(), 20, 250);

		
		g.drawString("Planet Velocity (in meters per second): " , 20, 300);
		g.drawString(simulation.planet.getVelocity().toString(), 20, 325);

		g.drawString("Planet Acceleration (in meters per second squared): ", 20, 375);
		g.drawString(simulation.planet.getAcceleration().toString(), 20, 400);
		
		
		g.drawString("Moon Current Position (in millions of kilometers):", 20, 450);
		g.drawString(simulation.moon.getSolarPos().toString(), 20, 475);

		
		g.drawString("Moon Velocity (in meters per second): " , 20, 525);
		g.drawString(simulation.moon.getVelocity().toString(), 20, 550);

		g.drawString("Moon Acceleration (in meters per second squared): ", 20, 600);
		g.drawString(simulation.moon.getAcceleration().toString(), 20, 625);
		
		time.start();
	}
}
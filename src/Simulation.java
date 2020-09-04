import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;


public class Simulation extends JFrame
{
	private final JSplitPane topBottomSplit;  // split the window in top and bottom
    private final JSplitPane sideSplit;    // splits the panel side to side
    private final simPanel sim;
    private final sidePanel side;
    private final controlPanel control;

    public Simulation()
    {
    	//Initialize Everything
    	topBottomSplit = new JSplitPane();
    	sideSplit = new JSplitPane();
    	sim = new simPanel();
    	side = new sidePanel(sim);
    	control = new controlPanel(sim);
    	
    	
    	setPreferredSize(new Dimension(1550, 1000));
    	getContentPane().setLayout(new GridLayout());
    	getContentPane().add(topBottomSplit);
    	
    	
    	topBottomSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
    	sideSplit.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    	
    	topBottomSplit.setDividerLocation(700);
    	sideSplit.setDividerLocation(1000);
    	
    	topBottomSplit.setTopComponent(sideSplit);
    	topBottomSplit.setBottomComponent(control);
    	
    	sideSplit.setLeftComponent(sim);
    	sideSplit.setRightComponent(side);
    	
    	sim.setBackground(Color.BLACK);
    	side.setBackground(new Color(232, 209, 69));
    	control.setBackground(new Color(112, 17, 28));
    	
    	pack();
    }
    
    public static void main(String args[])
    {
    	Simulation s = new Simulation();
    	s.setVisible(true);
    	s.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

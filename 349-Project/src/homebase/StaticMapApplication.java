package homebase;

import java.awt.BorderLayout;
import java.awt.Dimension;
//Java libraries
import java.io.*;
import javax.swing.*;

//Multimedia libraries
import app.*;

//Application libraries
import gui.*;
import resources.Descriptors;

/**
* An application that displays Property information on a static map.
* 
* @author Prof. David Bernstein, James Madison University
* @version 1.0
*/
public class StaticMapApplication extends HomeBaseApplication
{
  private static final String MAIN_CAMPUS = "campus-map.png";
  private static final String EAST_CAMPUS = "east-map.png";
  private StaticCampusMap campusMap;
  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   */
  public StaticMapApplication(final String[] args) throws IOException
  {
    super(args);

    campusMap = new StaticCampusMap(WIDTH, HEIGHT, MAIN_CAMPUS);
  }
    
  /**
   * Get the GUI component that will be used to display the Property information.
   * 
   * @return The WeatherObserverPanel
   */
  @Override
  protected JComponent getGUIComponent()
  {
    return campusMap.getView();
  }
  
  /**
   * Create new window to hold the East Campus Map.
   * @throws IOException if something goes wrong
   */
  @Override
  protected void handleEast() throws IOException
  {
    StaticCampusMap eastMap = new StaticCampusMap(1495, 815, EAST_CAMPUS);
    JFrame frame = new JFrame(Descriptors.EAST);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setMinimumSize(new Dimension(1495, 815));
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setOpaque(true);
    
    initializeEastButtons(panel);
    
    JComponent component = eastMap.getView();
    panel.add(component);
    
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
    frame.setResizable(false);
  }
  
  /**
   * Construct and invoke  (in the event dispatch thread) 
   * an instance of this JApplication.
   * 
   * @param args The command line arguments
   */
  public static void main(final String[] args) throws IOException
  {
    JApplication app = new StaticMapApplication(args);
    invokeInEventDispatchThread(app);
  }
}

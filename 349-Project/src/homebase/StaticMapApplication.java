package homebase;

//Java libraries
import java.io.*;
import javax.swing.*;

//Multimedia libraries
import app.*;

//Application libraries
import gui.*;

/**
* An application that displays Property information on a static map.
* 
* @author Prof. David Bernstein, James Madison University
* @version 1.0
*/
public class StaticMapApplication extends HomeBaseApplication
{
  private StaticCampusMap campusMap;
  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   */
  public StaticMapApplication(final String[] args) throws IOException
  {
    super(args);
    
//    String grayWatermark, useWatermark;
//    if (args.length < 1) useWatermark = null;
//    else useWatermark = args[0];
//    
//    if (args.length < 2) grayWatermark = null;
//    else grayWatermark = args[1];
    
//    propertyMap = new StaticPropertyMap(useWatermark, grayWatermark, WIDTH, HEIGHT-60);
    campusMap = new StaticCampusMap(WIDTH, HEIGHT);
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
   * Get the PropertyObserver to inform of changes.
   * 
   * @return The PropertyObserver
   */
//  @Override
//  protected PropertyObserver getPropertyObserver()
//  {
//    return propertyMap;
//  }
  
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

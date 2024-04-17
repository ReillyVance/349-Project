package homebase;

import java.awt.Color;
import java.awt.Font;
//Java libraries
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//Multimedia libraries
import app.*;
import io.*;
import resources.Marker;
import resources.Descriptors;

/**
 * An application that displays Property information in a generic way. 
 * 
 * The way in which the information is displayed is determined by the
 * getGUIComponent() method in the concrete specialization.
 * 
 * @author Prod. David Bernstein, James Madison University
 * @version 1.0
 */
public abstract class HomeBaseApplication extends JApplication implements ActionListener
{
  public static final int WIDTH  = 1524;
  public static final int HEIGHT = 968;
  
  private static final Color INVIS = new Color(0 , 0, 0, 0);

  
  private JButton aboutButton, sscButton;
//  private JTextField fileField;
  private String aboutText, sscText;
  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   */
  public HomeBaseApplication(final String[] args)
  {
    super(args, WIDTH, HEIGHT);
    
    // TODO: MAKE READING FROM FILES BETTER
    ResourceFinder rf = ResourceFinder.createInstance(new Marker());
    InputStream    is = rf.findInputStream("about.txt");
    BufferedReader in = new BufferedReader(new InputStreamReader(is));
    
    String line;
    aboutText = "";
    sscText = "";
    try
    {
      while ((line = in.readLine()) != null)
      {
        aboutText += line + "\n";
      }
      is = rf.findInputStream("ssc.txt");
      in = new BufferedReader(new InputStreamReader(is));
      line = "";
      while ((line = in.readLine()) != null)
      {
        sscText += line + "\n";
      }
    }
    catch (IOException ioe)
    {
      aboutText = "CS349 Project";
      sscText = "SSC info";
    }
  }

  
  /**
   * Handle actionPerformed messages (required by ActionListener).
   * In particular, get the input, perform the requested conversion,
   * and display the result.
   * 
   * @param evt  The ActionEvent that generated the actionPerformed message
   */
  @Override
  public void actionPerformed(final ActionEvent evt)
  {
    String ac = evt.getActionCommand();
    
    if (ac.equalsIgnoreCase(Descriptors.ABOUT)) handleAbout();
    else if (ac.equalsIgnoreCase(Descriptors.SSC)) handleSsc();
  }
  
  /**
   * Handle the ABOUT button.
   */
  protected void handleAbout()
  {
    JOptionPane.showMessageDialog(getGUIComponent(), 
        aboutText, Descriptors.ABOUT, JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**
   * Handle the SSC button.
   */
  protected void handleSsc()
  {
    JOptionPane.showMessageDialog(getGUIComponent(), sscText, 
        Descriptors.SSC, JOptionPane.INFORMATION_MESSAGE);
  }
  /**
   * Handle the LOAD button.
   */
//  protected void handleLoad()
//  {
//    String fileName = fileField.getText();
//    PropertyReader in;
//    try
//    {
//      BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
//      if (fileName.endsWith("apartments")) in = new ApartmentReader(br);
//      else in = new HouseReader(br);
//
//      getPropertyObserver().reset();
//      in.addObserver(getPropertyObserver());
//      in.readAll();
//    }
//    catch (IOException ioe)
//    {
//      JOptionPane.showMessageDialog(getGUIComponent(), 
//          "There was a problem reading " + fileName,
//          "Error", JOptionPane.ERROR_MESSAGE);
//    }
//  }
  
  /**
   * Construct the GUI components to use to display the Property information.
   * 
   * @return The JComponent
   */
  protected abstract JComponent getGUIComponent();
  
  /**
   * Get the PropertyObserver to inform of changes.
   * 
   * @return The PropertyObserver
   */
//  protected abstract PropertyObserver getPropertyObserver();
  
  /**
   * Initialize this JApplication (required by JApplication).
   * Specifically, construct and layout the JFrame.
   */
  @Override
  public void init()
  {
    // Setup the content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.setLayout(null);

//    JLabel label = new JLabel("File: ");
//    label.setBounds(30, 30, 40, 30);
//    contentPane.add(label);
    
//    fileField = new JTextField();
//    fileField.setBounds(80, 30, 200, 30);
//    contentPane.add(fileField);
    
    aboutButton = new JButton(Descriptors.ABOUT);
    aboutButton.setBounds(WIDTH - 90, 20, 70, 30);
    aboutButton.addActionListener(this);
    contentPane.add(aboutButton);
    
    sscButton = new JButton(Descriptors.SSC);
    sscButton.setBounds(790, 360, 40, 40);
    sscButton.setOpaque(false);
    sscButton.setContentAreaFilled(false);
    sscButton.setBorderPainted(false);
    sscButton.setForeground(INVIS);
    sscButton.addActionListener(this);
    contentPane.add(sscButton);
    
    JComponent component = getGUIComponent();
    component.setBounds(0, 60, WIDTH, HEIGHT-60);
    contentPane.add(component);
  }
}

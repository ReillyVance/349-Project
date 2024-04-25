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

  
  private JButton aboutButton;
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
    
    switch (ac.toUpperCase())
    {
      case Descriptors.ABOUT:
        handleAbout();
        break;
      case Descriptors.BOOK:
        break;
      case Descriptors.CARRIER:
        break;
      case Descriptors.DHALL:
        break;
      case Descriptors.DUKES:
        break;
      case Descriptors.FORBES:
        break;
      case Descriptors.GRAPT:
        break;
      case Descriptors.SSC:
        handleSsc();
        break;
      case Descriptors.STADIUM:
        break;
      case Descriptors.QUAD:
        break;
      case Descriptors.UNION:
        break;
      case Descriptors.VILLAGE:
        break;
      default:
        JOptionPane.showMessageDialog(getGUIComponent(), 
          "There was a problem opening info pane",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
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
    
    initializeMainButtons(contentPane);
    
    JComponent component = getGUIComponent();
    component.setBounds(0, 60, WIDTH, HEIGHT-60);
    contentPane.add(component);
  }
  
  private void initializeMainButtons(final JPanel contentPane)
  { 
    JButton bookButton = new JButton(Descriptors.BOOK);
    bookButton.setBounds(847, 726, 40, 40);
    bookButton.setOpaque(false);
    bookButton.setContentAreaFilled(false);
    bookButton.setBorderPainted(false);
    bookButton.setForeground(INVIS);
    bookButton.addActionListener(this);
    contentPane.add(bookButton);
    
    JButton carrButton = new JButton(Descriptors.CARRIER);
    carrButton.setBounds(731, 493, 40, 40);
    carrButton.setOpaque(false);
    carrButton.setContentAreaFilled(false);
    carrButton.setBorderPainted(false);
    carrButton.setForeground(INVIS);
    carrButton.addActionListener(this);
    contentPane.add(carrButton);
    
    JButton dhallButton = new JButton(Descriptors.DHALL);
    dhallButton.setBounds(710, 635, 40, 40);
    dhallButton.setOpaque(false);
    dhallButton.setContentAreaFilled(false);
    dhallButton.setBorderPainted(false);
    dhallButton.setForeground(INVIS);
    dhallButton.addActionListener(this);
    contentPane.add(dhallButton);
    
    JButton dukeButton = new JButton(Descriptors.DUKES);
    dukeButton.setBounds(830, 500, 40, 40);
    dukeButton.setOpaque(false);
    dukeButton.setContentAreaFilled(false);
    dukeButton.setBorderPainted(false);
    dukeButton.setForeground(INVIS);    
    dukeButton.addActionListener(this);
    contentPane.add(dukeButton);
    
    JButton forbeButton = new JButton(Descriptors.FORBES);
    forbeButton.setBounds(382, 301, 40, 40);
    forbeButton.setOpaque(false);
    forbeButton.setContentAreaFilled(false);
    forbeButton.setBorderPainted(false);
    forbeButton.setForeground(INVIS);
    forbeButton.addActionListener(this);
    contentPane.add(forbeButton);
    
    JButton graptButton = new JButton(Descriptors.GRAPT);
    graptButton.setBounds(440, 90, 40, 40);
    graptButton.setOpaque(false);
    graptButton.setContentAreaFilled(false);
    graptButton.setBorderPainted(false);
    graptButton.setForeground(INVIS);
    graptButton.addActionListener(this);
    contentPane.add(graptButton);
    
    JButton sscButton = new JButton(Descriptors.SSC);
    sscButton.setBounds(790, 360, 40, 40);
    sscButton.setOpaque(false);
    sscButton.setContentAreaFilled(false);
    sscButton.setBorderPainted(false);
    sscButton.setForeground(INVIS);
    sscButton.addActionListener(this);
    contentPane.add(sscButton);
    
    JButton stadButton = new JButton(Descriptors.STADIUM);
    stadButton.setBounds(711, 834, 40, 40);
    stadButton.setOpaque(false);
    stadButton.setContentAreaFilled(false);
    stadButton.setBorderPainted(false);
    stadButton.setForeground(INVIS);
    stadButton.addActionListener(this);
    contentPane.add(stadButton);
    
    JButton quadButton = new JButton(Descriptors.QUAD);
    quadButton.setBounds(570, 490, 40, 40);
    quadButton.setOpaque(false);
    quadButton.setContentAreaFilled(false);
    quadButton.setBorderPainted(false);
    quadButton.setForeground(INVIS);
    quadButton.addActionListener(this);
    contentPane.add(quadButton);
    
    JButton unionButton = new JButton(Descriptors.UNION);
    unionButton.setBounds(842, 565, 40, 40);
    unionButton.setOpaque(false);
    unionButton.setContentAreaFilled(false);
    unionButton.setBorderPainted(false);
    unionButton.setForeground(INVIS);
    unionButton.addActionListener(this);
    contentPane.add(unionButton);
    
    JButton villButton = new JButton(Descriptors.VILLAGE);
    villButton.setBounds(1041, 778, 40, 40);
    villButton.setOpaque(false);
    villButton.setContentAreaFilled(false);
    villButton.setBorderPainted(false);
    villButton.setForeground(INVIS);
    villButton.addActionListener(this);
    contentPane.add(villButton);
  }
  
  private void initializeEastButtons(final JPanel contentPane)
  {
    
  }
}

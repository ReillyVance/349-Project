package homebase;

import java.awt.Color;
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
  
  private static final Color INVIS = new Color(0, 0, 0, 0);

  
  private JButton aboutButton;
  private String desc;
  
  /**
   * Explicit value constructor.
   * 
   * @param args   The command line arguments
   */
  public HomeBaseApplication(final String[] args)
  {
    super(args, WIDTH, HEIGHT);
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
      case Descriptors.EAST:
        try
        {
          handleEast();
        }
        catch (IOException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        break;
      case Descriptors.ABOUT:
        fetchDescription(Descriptors.ABOUT);
        handleWindow(desc, Descriptors.ABOUT);
        break;
      case Descriptors.BOOK:
        fetchDescription(Descriptors.BOOK);
        handleWindow(desc, Descriptors.BOOK);
        break;
      case Descriptors.CARRIER:
        fetchDescription(Descriptors.CARRIER);
        handleWindow(desc, Descriptors.CARRIER);
        break;
      case Descriptors.DHALL:
        fetchDescription(Descriptors.DHALL);
        handleWindow(desc, Descriptors.DHALL);
        break;
      case Descriptors.DUKES:
        fetchDescription(Descriptors.DUKES);
        handleWindow(desc, Descriptors.DUKES);
        break;
      case Descriptors.FORBES:
        fetchDescription(Descriptors.FORBES);
        handleWindow(desc, Descriptors.FORBES);
        break;
      case Descriptors.GRAPT:
        fetchDescription(Descriptors.GRAPT);
        handleWindow(desc, Descriptors.GRAPT);
        break;
      case Descriptors.QUAD:
        fetchDescription(Descriptors.QUAD);
        handleWindow(desc, Descriptors.QUAD);
        break;
      case Descriptors.SSC:
        fetchDescription(Descriptors.SSC);
        handleWindow(desc, Descriptors.SSC);
        break;
      case Descriptors.STADIUM:
        fetchDescription(Descriptors.STADIUM);
        handleWindow(desc, Descriptors.STADIUM);
        break;
      case Descriptors.UNION:
        fetchDescription(Descriptors.UNION);
        handleWindow(desc, Descriptors.UNION);
        break;
      case Descriptors.VILLAGE:
        fetchDescription(Descriptors.VILLAGE);
        handleWindow(desc, Descriptors.VILLAGE);
        break;
      // TODO: add east campus
      case Descriptors.CENTER:
        fetchDescription(Descriptors.CENTER);
        handleWindow(desc, Descriptors.CENTER);
        break;
      case Descriptors.EHALL:
        fetchDescription(Descriptors.EHALL);
        handleWindow(desc, Descriptors.EHALL);
        break;
      case Descriptors.FDORMS:
        fetchDescription(Descriptors.FDORMS);
        handleWindow(desc, Descriptors.FDORMS);
        break;
      case Descriptors.FESTI:
        fetchDescription(Descriptors.FESTI);
        handleWindow(desc, Descriptors.FESTI);
        break;
      case Descriptors.KING:
        fetchDescription(Descriptors.KING);
        handleWindow(desc, Descriptors.KING);
        break;
      case Descriptors.ROSE:
        fetchDescription(Descriptors.ROSE);
        handleWindow(desc, Descriptors.ROSE);
        break;
      case Descriptors.UREC:
        fetchDescription(Descriptors.UREC);
        handleWindow(desc, Descriptors.UREC);
        break;
      default:
        JOptionPane.showMessageDialog(getGUIComponent(), 
            "There was a problem opening info pane",
            ac, JOptionPane.ERROR_MESSAGE);
    }
  }
  
  protected abstract void handleEast() throws IOException;
  
  /**
   * Get description info based off tag.
   * @param descriptor tag
   * @return description of location
   */
  private String fetchDescription(final String descriptor)
  {
    ResourceFinder rf = ResourceFinder.createInstance(new Marker());
    InputStream    is = rf.findInputStream(descriptor + ".txt");
    BufferedReader in = new BufferedReader(new InputStreamReader(is));
    
    String line;
    desc = "";
    try
    {
      while ((line = in.readLine()) != null)
      {
        desc += line + "\n";
      }
    }
    catch (IOException ioe)
    {
      desc = descriptor;
    }
    return desc;
  }
  
  /**
   * Handle the description buttons.
   * @param description location description
   * @param descriptor location descriptor tag
   */
  protected void handleWindow(final String description, final String descriptor)
  {
    JOptionPane.showMessageDialog(getGUIComponent(), 
        description, descriptor, JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**
   * Construct the GUI components to use to display the Property information.
   * 
   * @return The JComponent
   */
  protected abstract JComponent getGUIComponent();
  
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

    JButton eastButton = new JButton(Descriptors.EAST);
    eastButton.setBounds(20, 20, 110, 30);
    eastButton.addActionListener(this);
    contentPane.add(eastButton);
    
    aboutButton = new JButton(Descriptors.ABOUT);
    aboutButton.setBounds(WIDTH - 90, 20, 70, 30);
    aboutButton.addActionListener(this);
    contentPane.add(aboutButton);
    
    initializeMainButtons(contentPane);
    
    JComponent component = getGUIComponent();
    component.setBounds(0, 60, WIDTH, HEIGHT-60);
    contentPane.add(component);
  }
  
  protected void initializeMainButtons(final JPanel contentPane)
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
  
  protected void initializeEastButtons(final JPanel contentPane)
  {
    JButton centerButton = new JButton(Descriptors.CENTER);
    centerButton.setBounds(1040, 530, 40, 40);
    centerButton.setOpaque(false);
    centerButton.setContentAreaFilled(false);
    centerButton.setBorderPainted(false);
    centerButton.setForeground(INVIS);
    centerButton.addActionListener(this);
    contentPane.add(centerButton);
    
    JButton ehallButton = new JButton(Descriptors.EHALL);
    ehallButton.setBounds(900, 490, 40, 40);
    ehallButton.setOpaque(false);
    ehallButton.setContentAreaFilled(false);
    ehallButton.setBorderPainted(false);
    ehallButton.setForeground(INVIS);
    ehallButton.addActionListener(this);
    contentPane.add(ehallButton);
    
    JButton fdormsButton = new JButton(Descriptors.FDORMS);
    fdormsButton.setBounds(855, 430, 40, 40);
    fdormsButton.setOpaque(false);
    fdormsButton.setContentAreaFilled(false);
    fdormsButton.setBorderPainted(false);
    fdormsButton.setForeground(INVIS);
    fdormsButton.addActionListener(this);
    contentPane.add(fdormsButton);
    
    JButton festiButton = new JButton(Descriptors.FESTI);
    festiButton.setBounds(1017, 275, 40, 40);
    festiButton.setOpaque(false);
    festiButton.setContentAreaFilled(false);
    festiButton.setBorderPainted(false);
    festiButton.setForeground(INVIS);
    festiButton.addActionListener(this);
    contentPane.add(festiButton);
    
    JButton kingButton = new JButton(Descriptors.KING);
    kingButton.setBounds(775, 125, 40, 40);
    kingButton.setOpaque(false);
    kingButton.setContentAreaFilled(false);
    kingButton.setBorderPainted(false);
    kingButton.setForeground(INVIS);
    kingButton.addActionListener(this);
    contentPane.add(kingButton);
    
    JButton roseButton = new JButton(Descriptors.ROSE);
    roseButton.setBounds(1167, 135, 40, 40);
    roseButton.setOpaque(false);
    roseButton.setContentAreaFilled(false);
    roseButton.setBorderPainted(false);
    roseButton.setForeground(INVIS);
    roseButton.addActionListener(this);
    contentPane.add(roseButton);
    
    JButton urecButton = new JButton(Descriptors.UREC);
    urecButton.setBounds(347, 215, 40, 40);
    urecButton.setOpaque(false);
    urecButton.setContentAreaFilled(false);
    urecButton.setBorderPainted(false);
    urecButton.setForeground(INVIS);
    urecButton.addActionListener(this);
    contentPane.add(urecButton);
  }
}

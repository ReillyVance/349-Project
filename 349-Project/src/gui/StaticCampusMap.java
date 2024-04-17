package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import campus.visual.StyleIconReader;
import io.ResourceFinder;
import visual.Visualization;
import visual.VisualizationView;
import visual.statik.sampled.ContentFactory;
import visual.statik.sampled.ImageFactory;
import resources.Marker;

public class StaticCampusMap extends Visualization
{
  private static final Color BACKGROUND_COLOR = new Color(204, 204, 255);
  
  private ResourceFinder jarFinder;
  private visual.statik.sampled.Content map;
  
  /**
   * Explicit Value Constructor.
   * @param width width of the component
   * @param height height of the component
   * @throws IOException if something goes wrong
   */
  public StaticCampusMap(final int width, final int height) throws IOException
  {
    super();
    
    jarFinder = ResourceFinder.createInstance(new Marker());
    StyleIconReader iconReader = new StyleIconReader(jarFinder);
    
    VisualizationView view = getView();
    view.setBounds(0, 0, width, height);
    view.setSize(width, height);
    view.setBackground(BACKGROUND_COLOR);
    
    ContentFactory contentFactory = new ContentFactory(jarFinder);
    ImageFactory imageFactory = new ImageFactory(jarFinder);
    BufferedImage image = imageFactory.createBufferedImage("campus-map.png");
    map = contentFactory.createContent(image, false);
    add(map);
  }
}

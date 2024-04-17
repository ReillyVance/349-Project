package campus.visual;

import java.awt.*;
import java.io.*;
import java.util.*;

// Multimedia Library
import io.*;
import visual.statik.sampled.*;

/**
 * A factory for creating Property style icons.
 *
 * @author Prof. David bernstein, James Madison University
 * @version 1.0
 */
public class StyleIconReader
{
  private static final String[] IMAGE_NAMES = {"campus-map"};

  private ResourceFinder finder;

  /**
   * Explicit Value Constructor.
   *
   * @param finder
   *          The ResourceFinder to use
   */
  public StyleIconReader(final ResourceFinder finder)
  {
    this.finder = finder;
  }

  /**
   * Read a collection of weather icons.
   *
   * @return A Map containing the icons
   */
  public Map<String, Image> read() throws IOException
  {
    Map<String, Image> images = new HashMap<String, Image>();
    ImageFactory imageFactory = new ImageFactory(finder);

    for (int i = 0; i < IMAGE_NAMES.length; i++)
    {
      Image image = imageFactory.createBufferedImage(IMAGE_NAMES[i] + ".png", 4);
      if (image != null)
        images.put(IMAGE_NAMES[i], image);
    }

    return images;
  }
}

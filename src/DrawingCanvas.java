/*
 *  DrawingCanvas
 *
 *  Simple class to act as a place to draw vectors.
 *
 *  Created by Sally Goldin, 23 June 2005
 *  Updated 30 December 2011
 */

import javax.swing.*;
import java.awt.*;

/**
 * Simple subclass of JPanel that allows us to set the size of
 * a drawing area, also to clear it.
 */
public class DrawingCanvas extends JPanel
{
    /* Preferred size */
   private Dimension desiredSize = null;
    
   /**
    * Constructor sets desired size.
    * @param width   Desired width
    * @param height  Desired height
    */
   public DrawingCanvas(int width, int height)
   {
       desiredSize = new Dimension(width,height);
   }
 
   /**
    * Override so that we can control the size.
    */
   public Dimension getPreferredSize()
   {
       return desiredSize;
   } 

   /**
    * Clear the panel to background color.
    */
   public void clear()
   {
	updateUI();
   }
}

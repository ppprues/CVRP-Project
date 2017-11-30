/**
 * FigureViewer
 *
 * Simple graphical application to display simple geometric figures
 *
 * Created by Sally Goldin, 10 September 2017
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class FigureViewer extends JFrame
{
    /* UI objects */
    private DrawingCanvas drawCanvas = null;

    /**
     * Constructor creates the User Interface.
     */
    public FigureViewer()
    {
        super("Figure Viewer");
        buildUI();
    }

    /**
     * Create the visible part of the user interface.
     */
    private void buildUI()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        drawCanvas = new DrawingCanvas(500, 500);
        drawCanvas.setBorder(new EtchedBorder());
        drawCanvas.setBackground(Color.white);
        mainPanel.add(drawCanvas, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Clear the drawing panel.
     */
    public void clear()
    {
        drawCanvas.clear();
    }

    /**
     * Return the graphics context associated with
     * the panel used for drawing.
     *
     * @return Graphics context
     */
    public Graphics2D getViewerGraphics()
    {
        return (Graphics2D) drawCanvas.getGraphics();
    }
}
/**
 *  Graph
 *
 *  Simple class of making UI.
 *
 *  4 November 2017
 */

import java.awt.*;

public class Graph
{
    /* Build UI */
    private static FigureViewer viewer = new FigureViewer();

    /* All colors to paint */
    private static Color colors[] = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};

    /**
     * Initialize text in canvas.
     */
    public static void initialize()
    {
        int offset = 20;
        viewer.pack();
        viewer.setVisible(true);

        Graphics2D graphics = viewer.getViewerGraphics();
        for (int i = 0; i < 4; i++)
        {
            graphics.setPaint(colors[i]);
            graphics.drawString("Truck " + (i + 1), 10, offset);
            offset = offset + 20;
        }
    }

    /**
     * Plot graph from input's gene.
     * @param gene input's gene
     */
    public static void plotGene(int[] gene)
    {
        int[][] nodes = DataReader.getNodes();
        int truckCount = 0;
        int nodeCount = 0;
        int previousNode = 0;
        int cursor = 0;
        int[][] truckPath = new int[4][10]; //path of trucks

        Graphics2D graphics = viewer.getViewerGraphics();
        graphics.setPaint(Color.BLACK);
        graphics.drawString("(50,50)", 250, 250);

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                truckPath[i][j] = gene[cursor];
                cursor++;
            }
        }

        while (truckCount < 4)
        {
            graphics.setPaint(colors[truckCount]);
            for (int i = 0; i < 10; i++)
            {
                if (truckPath[truckCount][i] != 0) // Node exists
                {
                    if (nodeCount == 0) // The first node
                    {
                        graphics.drawLine(250, 250, nodes[truckPath[truckCount][i]][0] * 5, nodes[truckPath[truckCount][i]][1] * 5);
                    }
                    else
                    {
                        graphics.drawLine(nodes[previousNode][0] * 5, nodes[previousNode][1] * 5, nodes[truckPath[truckCount][i]][0] * 5, nodes[truckPath[truckCount][i]][1] * 5);
                    }
                    graphics.drawString("(" + nodes[truckPath[truckCount][i]][0] + "," + nodes[truckPath[truckCount][i]][1] + ")", nodes[truckPath[truckCount][i]][0] * 5, nodes[truckPath[truckCount][i]][1] * 5);
                    previousNode = truckPath[truckCount][i];
                    nodeCount++;
                }
            }
            if (nodeCount != 0)
            {
                graphics.drawLine(nodes[previousNode][0] * 5, nodes[previousNode][1] * 5, 250, 250);
            }
            previousNode = 0;
            nodeCount = 0;
            truckCount++;
        }
    }
}
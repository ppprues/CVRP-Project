import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;

public class Graph
{
    private static FigureViewer viewer = new FigureViewer();

    public static void initialize()
    {
        viewer.pack();
        viewer.setVisible(true);
    }

    public static void plotGene(int[] gene)
    {
        double[][] nodes = DataReader.getNodes();
        int truckCount = 0;
        int nodeCount = 0;
        int previousNode = 0;
        int cursor = 0;
        int[][] truckPath = new int[4][10]; //path of trucks
        Color colors[] = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE};

        Graphics2D graphics = viewer.getViewerGraphics();
        graphics.setPaint(Color.BLACK);
        graphics.drawString("(50,50)", 250, 250);
        graphics.setPaint(colors[0]);

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
            graphics.setPaint(colors[truckCount + 1]);
            for (int i = 0; i < 10; i++)
            {
                if (truckPath[truckCount][i] != 0) // Node exists
                {
                    if (nodeCount == 0) // The first node
                    {
                        graphics.drawLine(250, 250, (int) nodes[truckPath[truckCount][i]][0] * 5, (int) nodes[truckPath[truckCount][i]][1] * 5);
                    }
                    else
                    {
                        graphics.drawLine((int) nodes[previousNode][0] * 5, (int) nodes[previousNode][1] * 5, (int) nodes[truckPath[truckCount][i]][0] * 5, (int) nodes[truckPath[truckCount][i]][1] * 5);
                    }
                    graphics.drawString("(" +(int)nodes[truckPath[truckCount][i]][0]+ ","+(int)nodes[truckPath[truckCount][i]][1]+")", (int) nodes[truckPath[truckCount][i]][0] * 5, (int) nodes[truckPath[truckCount][i]][1] * 5);
                    previousNode = truckPath[truckCount][i];
                    nodeCount++;
                }
            }
            graphics.drawLine((int) nodes[previousNode][0] * 5, (int) nodes[previousNode][1] * 5, 250, 250);
            previousNode = 0;
            nodeCount = 0;
            truckCount++;
        }
    }
}
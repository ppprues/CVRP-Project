/**
 *  DataReader
 *
 *  Read all data from text files to initialize.
 *
 *  4 November 2017
 */

public class DataReader extends TextFileReader
{
    /* All data */
    private static int[] demands = new int[11];
    private static int[][] nodes = new int[11][2];
    private static double[] productWeight = new double[5];
    private static double[][] truckLoad = new double[4][2];

    /* All text files */
    private static TextFileReader demandFile = new TextFileReader();
    private static TextFileReader nodeFile = new TextFileReader();
    private static TextFileReader productFile = new TextFileReader();
    private static TextFileReader truckFile = new TextFileReader();

    /**
     * Initialize all data from all files.
     */
    public static void initializeData()
    {
        demandFile.open("demand.txt");
        nodeFile.open("node.txt");
        productFile.open("product.txt");
        truckFile.open("truck.txt");

        calDemands();
        calNodes();
        calProductWeight();
        calTruckLoad();
    }

    /**
     * Get demands each line from demand.txt
     */
    private static void calDemands()
    {
        int i = 0;
        String curLine = demandFile.getNextLine();
        while (curLine != null)
        {
            demands[i + 1] = Integer.parseInt(curLine);
            i++;
            curLine = demandFile.getNextLine();
        }
    }

    /**
     * Get nodes each line from node.txt
     */
    private static void calNodes()
    {
        String curLine = nodeFile.getNextLine();
        int i = 0;

        while (curLine != null)
        {
            String fields[] = curLine.split(",");
            nodes[i + 1][0] = Integer.parseInt(fields[0]);
            nodes[i + 1][1] = Integer.parseInt(fields[1]);
            i++;
            curLine = nodeFile.getNextLine();
        }
    }

    /**
     * Get product weight each line from product.txt
     */
    private static void calProductWeight()
    {
        int i = 0;
        String curLine = productFile.getNextLine();
        while (curLine != null)
        {
            productWeight[i] = Double.parseDouble(curLine);
            i++;
            curLine = productFile.getNextLine();
        }
    }

    /**
     * Get truck load each line from truck.txt
     */
    private static void calTruckLoad()
    {
        int i = 0;
        String curLine = truckFile.getNextLine();
        while (curLine != null)
        {
            String fields[] = curLine.split(",");
            truckLoad[i][0] = Float.parseFloat(fields[0]);
            truckLoad[i][1] = Float.parseFloat(fields[1]);
            curLine = truckFile.getNextLine();
            i++;
        }
    }

    /**
     * Get demands data.
     * @return array of demands
     */
    public static int[] getDemands()
    {
        return demands;
    }

    /**
     * Get nodes data by x,y coordinates.
     * @return 2-dimension array of demands
     */
    public static int[][] getNodes()
    {
        return nodes;
    }

    /**
     * Get product weight data for each truck.
     * @return array of demands
     */
    public static double[] getProductWeight()
    {
        return productWeight;
    }

    /**
     * Get truck data by maximum load and cost per km.
     * @return 2-dimension array of demands
     */
    public static double[][] getTruckLoad()
    {
        return truckLoad;
    }
}

public class DataReader extends TextFileReader
{
    private static int[] demands = new int[16];
    private static double[][] nodes = new double[16][2];
    private static double[] productWeight = new double[5];
    private static double[][] truckLoad = new double[4][2];

    private static TextFileReader demandFile = new TextFileReader();
    private static TextFileReader nodeFile = new TextFileReader();
    private static TextFileReader productFile = new TextFileReader();
    private static TextFileReader truckFile = new TextFileReader();

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

    private static void calNodes()
    {
        String curLine = nodeFile.getNextLine();
        int i = 0;
        while (curLine != null)
        {
            String fields[] = curLine.split(",");
            nodes[i + 1][0] = Double.parseDouble(fields[0]);
            nodes[i + 1][1] = Double.parseDouble(fields[1]);

            curLine = nodeFile.getNextLine();
        }
    }

    private static void calProductWeight()
    {
        int i = 0;
        String curLine = productFile.getNextLine();
        while (curLine != null)
        {
            productWeight[i + 1] = Double.parseDouble(curLine);
            i++;
            curLine = productFile.getNextLine();
        }
    }

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

    public static int[] getDemands()
    {
        return demands;
    }

    public static double[][] getNodes()
    {
        return nodes;
    }

    public static double[] getProductWeight()
    {
        return productWeight;
    }

    public static double[][] getTruckLoad()
    {
        return truckLoad;
    }
}

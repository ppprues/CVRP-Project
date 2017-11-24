import java.util.ArrayList;

public class DataReader extends TextFileReader {
    private static double[][] nodes= new double[16][2];
    private static double[] demands= new double [16];
    private static double[][] truckLoad=  new double[4][2];
    private static double[] productWeight= new double[4];
    private static TextFileReader nodeFile;
    private static TextFileReader demandFile;
    private static TextFileReader productFile;
    private static TextFileReader truckFile;
    public static void initializeData()
    {
        nodeFile = new TextFileReader();
        nodeFile.open("node.txt");

        demandFile = new TextFileReader();
        demandFile.open("demand.txt");

        productFile = new TextFileReader();
        productFile.open("product.txt");

        truckFile = new TextFileReader();
        truckFile.open("truck.txt");

        calNodes();
        calDemands();
        calProductWeight();
        calTruckLoad();
    }

    public static double[][] getNodes()
    {
        return nodes;
    }
    public static double[] getDemands()
    {
        return demands;
    }
    public static double[][] getTruckLoad()
    {
        return truckLoad;
    }
    public static double[] getProductWeight()
    {
        return productWeight;
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

    private static void calProductWeight()
    {
        int i = 0;
        String curLine = productFile.getNextLine();
        while (curLine != null)
        {
            productWeight[i + 1] = Integer.parseInt(curLine);
            i++;
            curLine = productFile.getNextLine();
        }
    }
}

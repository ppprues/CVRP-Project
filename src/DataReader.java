import java.util.ArrayList;

public class DataReader extends TextFileReader {
    private double[][] nodes;
    private double[] demands;
    private double[][] truckLoad;
    private double[] productWeight;
    private TextFileReader nodeFile;
    private TextFileReader demandFile;
    private TextFileReader  productFile;
    private  TextFileReader truckFile;
    public DataReader()
    {

        nodeFile = new TextFileReader();
        nodeFile.open("node.txt");

        demandFile = new TextFileReader();
        demandFile.open("demand.txt");

        productFile = new TextFileReader();
        productFile.open("product.txt");

        truckFile = new TextFileReader();
        truckFile.open("truck.txt");

    }

    public double[][] getNodes()
    {

        String curLine=nodeFile.getNextLine();
        int i=0;
        while (curLine!=null)
        {
            String fields[] = curLine.split(",");
            nodes[i+1][0] = Integer.parseInt(fields[0]);
            nodes[i+1][1]= Integer.parseInt(fields[1]);

            curLine=nodeFile.getNextLine();
        }

        return nodes;
    }

    public double[] getDemands()
    {
        int i =0;
        String curLine = demandFile.getNextLine();
        while (curLine!=null)
        {
            demands[i+1] =  Integer.parseInt(curLine);
            i++;
            curLine= demandFile.getNextLine();
        }
        return  demands;
    }

    public double[][] getTruckLoad()
    {
        int i =0;
        String curLine = truckFile.getNextLine();
        while (curLine!=null)
        {
            String fields[] = curLine.split(",");
            truckLoad[i][0] = Float.parseFloat(fields[0]);
            truckLoad[i][1] = Float.parseFloat(fields[1]);
            curLine = truckFile.getNextLine();
            i++;
        }
        return truckLoad;
    }

    public double[] getProductWeight()
    {
        int i=0;
        String curLine = productFile.getNextLine();
        while (curLine!=null)
        {
            productWeight[i+1] = Integer.parseInt(curLine);
            i++;
            curLine = productFile.getNextLine();
        }
        return productWeight;
    }


}

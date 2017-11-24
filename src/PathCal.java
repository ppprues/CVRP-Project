public class PathCal
{
    private static double[][] nodes;
    private static int[] demands;
    private static double[][] truckLoad;
    private static double[] productWeight;

    public PathCal()
    {
        nodes = DataReader.getNodes();
        demands = DataReader.getDemands();
        truckLoad = DataReader.getTruckLoad();
        productWeight = DataReader.getProductWeight();
    }

    private static double calBetweenNodes(int startNode, int destination)
    {
        double result;
        double node1[] = nodes[startNode];
        double node2[] = nodes[destination];
        result = Math.sqrt((Math.pow(node2[0] - node1[0], 2) + Math.pow(node2[1] - node1[1], 2)));
        return result;
    }

    public static double distanceToOrigin(int destination)
    {
        double node1[] = nodes[destination];
        double result;
        result = Math.sqrt((Math.pow(node1[0] - 50, 2) + Math.pow(node1[1] - 50, 2)));
        return result;
    }

    public static double calGeneDistance(int[] solution)
    {
        double overallCost = 0;
        int cursor = 0;
        int i = 0;
        int[][] truckPath = new int[4][15]; //path of trucks
        int truckCount = 0;
        while (cursor < 60)    //seperate gene to each truck
        {
            while (i < 15)
            {
                truckPath[truckCount][i] = solution[cursor];
                cursor++;
                i++;
            }
            truckCount++;
        }
        truckCount = 0;
        while (truckCount < 4)
        {
            double overallDistance = 0;
            while (i < 15)    //calculate distance of each truck and calculate cost to overall cost
            {
                if (truckPath[truckCount][i] != 0)
                {
                    if (i == 0)
                    {
                        overallDistance += distanceToOrigin(truckPath[truckCount][0]);
                    }
                    else if (i < 14)
                    {
                        overallDistance += calBetweenNodes(truckPath[truckCount][i], truckPath[truckCount][i + 1]);
                    }
                    else
                    {
                        overallDistance += distanceToOrigin(truckPath[truckCount][14]);
                    }
                }
                i++;
            }
            overallCost += overallDistance * truckLoad[truckCount][1];
            truckCount++;
        }
        return overallCost;
    }
}
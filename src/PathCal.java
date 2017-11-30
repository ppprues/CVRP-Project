/**
 *  PathCal
 *
 *  Simple class of calculate path.
 *
 *  4 November 2017
 */

public class PathCal
{
    /* All data to get */
    private static int[] demands;
    private static int[][] nodes;
    private static double[] productWeight;
    private static double[][] truckLoad;

    public PathCal()
    {

    }

    /**
     * Initialize before calculation.
     */
    public static void initializeCalculator()
    {
        DataReader.initializeData();
        demands = DataReader.getDemands();
        nodes = DataReader.getNodes();
        productWeight = DataReader.getProductWeight();
        truckLoad = DataReader.getTruckLoad();
    }

    /**
     * Calculate distance between 2 nodes.
     * @param startNode start's node
     * @param destination end's node
     * @return distance in km
     */
    private static double calBetweenNodes(int startNode, int destination)
    {
        double result;
        int node1[] = nodes[startNode];
        int node2[] = nodes[destination];
        result = Math.sqrt(Math.pow(node1[0] - node2[0], 2) + Math.pow(node1[1] - node2[1], 2));
        return result;
    }

    /**
     * Calculate distance between origin point (50,50) to end point.
     * @param destination end's node
     * @return distance in km
     */
    public static double distanceToOrigin(int destination)
    {
        int node1[] = nodes[destination];
        double result;
        result = Math.sqrt(Math.pow(node1[0] - 50, 2) + Math.pow(node1[1] - 50, 2));
        return result;
    }

    /**
     * Calculate gene cost for one gene
     * @param solution input gene
     * @return cost in baht
     */
    public static double calGeneCost(int[] solution)
    {
        double distance = 0.0;
        double overallCost = 0.0;
        int nodeCount = 0;
        int previousNode = 0;
        int cursor = 0;
        int[][] truckPath = new int[4][10]; //path of trucks
        int truckCount = 0;

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                truckPath[i][j] = solution[cursor];
                cursor++;
            }
        }

        while (truckCount < 4)
        {
            for (int j = 0; j < 10; j++)
            {
                if (truckPath[truckCount][j] != 0) // Node exists
                {
                    if (nodeCount == 0) // The first node
                    {
                        distance += distanceToOrigin(truckPath[truckCount][j]);
                    }
                    else
                    {
                        distance += calBetweenNodes(previousNode, truckPath[truckCount][j]);
                    }
                    previousNode = truckPath[truckCount][j];
                    nodeCount++;
                }
            }
            overallCost += distance * truckLoad[truckCount][1];
            previousNode = 0;
            nodeCount = 0;
            truckCount++;
        }
        return overallCost;
    }
}
public class PathCal
{
    private static int[] demands;
    private static double[][] nodes;
    private static double[] productWeight;
    private static double[][] truckLoad;

    public PathCal()
    {

    }

    public static void initializeCalculator()
    {
        DataReader.initializeData();
        demands = DataReader.getDemands();
        nodes = DataReader.getNodes();
        productWeight = DataReader.getProductWeight();
        truckLoad = DataReader.getTruckLoad();
    }

    private static double calBetweenNodes(int startNode, int destination)
    {
        double result;
        double node1[] = nodes[startNode];
        double node2[] = nodes[destination];
        result = Math.sqrt(Math.pow(node1[0] - node2[0], 2) + Math.pow(node1[1] - node2[1], 2));
        return result;
    }

    public static double distanceToOrigin(int destination)
    {
        double node1[] = nodes[destination];
        double result;
        result = Math.sqrt(Math.pow(node1[0] - 50, 2) + Math.pow(node1[1] - 50, 2));
        return result;
    }

    public static double calGeneCost(int[] solution)
    {
        double distance = 0.0;
        double overallDistance = 0.0;
        double overallCost = 0.0;
        int nodeCount = 0;
        int previousNode = 0;
        int cursor = 0;
        int[][] truckPath = new int[4][15]; //path of trucks
        int truckCount = 0;

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                truckPath[i][j] = solution[cursor];
                cursor++;
            }
        }

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                System.out.print(truckPath[i][j] + "\t");
            }
            System.out.print("\n");
        }

        while (truckCount < 4)
        {
            for (int j = 0; j < 15; j++)
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
                    System.out.print(truckPath[truckCount][j] + " -> ");
                    previousNode = truckPath[truckCount][j];
                    nodeCount++;
                }
            }
            distance += distanceToOrigin(previousNode);
            overallDistance += distance;
            System.out.print("Truck " + truckCount + " Distance = " + distance + " Total distance = " + overallDistance +"\n");

            overallCost += distance * truckLoad[truckCount][1];
            distance = 0;
            previousNode = 0;
            nodeCount = 0;
            truckCount++;
        }

        System.out.println("Overall Distance = " + overallDistance);
        System.out.println("Overall Cost = " + overallCost);

        return  overallCost;


        /*while (cursor < 60)
        {
            int j = 0;
            while (j < 15)
            {
                if (solution[cursor] != 0)
                {
                    truckPath[truckCount][i] = solution[cursor];
                    i++;
                }
                cursor++;
                j++;
            }
            i = 0;
            truckCount++;
        }
        truckCount = 0;
        i = 0;
        while (truckCount < 4)
        {
            System.out.print("truck" + truckCount + " ");
            while (truckPath[truckCount][i] != 0)
            {
                System.out.print(truckPath[truckCount][i] + " ");
                i++;
            }
            truckCount++;
            i = 0;
        }
        truckCount = 0;
        i = 0;
        while (truckCount < 4)
        {
            double overallDistance = 0;     //of each car
            while (truckPath[truckCount][i] != 0)    //calculate distance of each truck and calculate cost to overall cost
            {
                if (i == 0)
                {
                    overallDistance += distanceToOrigin(truckPath[truckCount][0]);
                    distance += distanceToOrigin(truckPath[truckCount][0]);
                }
                else
                {
                    if (truckPath[truckCount][i + 1] != 0)
                    {
                        overallDistance += calBetweenNodes(truckPath[truckCount][i], truckPath[truckCount][i + 1]);
                        distance += calBetweenNodes(truckPath[truckCount][i], truckPath[truckCount][i + 1]);
                    }
                    else
                    {
                        overallDistance += distanceToOrigin(truckPath[truckCount][i]);
                        distance += distanceToOrigin(truckPath[truckCount][0]);
                    }
                }
                i++;
            }
            overallCost += overallDistance * truckLoad[truckCount][1];
            truckCount++;
        }
        System.out.println("\ntotaldistance " + distance);*/
    }
}
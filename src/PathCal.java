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
        result = Math.sqrt(Math.pow(node2[0] - node1[0], 2) + Math.pow(node2[1] - node1[1], 2));
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
        double distance=0;
        double overallCost = 0;
        int cursor = 0;
        int i = 0;
        int[][] truckPath = new int[4][15]; //path of trucks
        int truckCount = 0;
        double eachDistance = 0;
        int[] last = new int[4];
        int noTrucks = 0;

        while (cursor < 60)    //seperate gene to each truck
        {
            int j=0;
            while (j < 15)
            {
                if(solution[cursor]!=0)
                {
                    truckPath[truckCount][i] = solution[cursor];
                    last[truckCount]++;
                    i++;
                }
                cursor++;
                j++;
            }
            i=0;
            truckCount++;
        }
        noTrucks = truckCount;

        for(i=0;i<noTrucks;i++)
        {
            eachDistance = 0;
            for(int j = 0;j<last[i];j++)
            {
                if (j == 0)
                {
                    eachDistance = eachDistance+distanceToOrigin(truckPath[i][j]);
                    distance = eachDistance+distance;
                }

                else if ( j == last[i]-1 )
                {
                    eachDistance = eachDistance+distanceToOrigin(truckPath[i][j]);
                    distance = eachDistance+distance;
                }

                else
                {
                    eachDistance = eachDistance + calBetweenNodes(truckPath[i][j],truckPath[i][j+1]);
                    distance = eachDistance+distance;
                }

            }
            overallCost = overallCost+eachDistance*truckLoad[i][1];

        }

        //System.out.println("\ntotaldistance "+distance);
        //System.out.println("totalCOST : "+overallCost);
        return overallCost;
    }

}
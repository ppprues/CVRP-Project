import java.util.ArrayList;
import java.util.Random;

public class Location
{
    public static ArrayList<int[]> geneCollection = new ArrayList<>();
    private static int[] solution = new int[60];

    private static int[] getRandom()
    {
        int inputNode = 1;
        int[] randomSol = new int[60];
        Random random = new Random();

        while(inputNode <= 15)
        {
            int inputAddress = random.nextInt(59);
            if(randomSol[inputAddress] == 0)
            {
                randomSol[inputAddress] = inputNode;
                inputNode++;
            }
        }

        return randomSol;
    }

    public static void calGeneCollection(int numberGene)
    {
        for(int i = 0; i < numberGene; i++)
        {
            solution = getRandom();
            geneCollection.add(solution);
        }
    }


    public static void main(String[] args)
    {
        calGeneCollection(10);

        for (int i = 0; i < geneCollection.size(); i++)
        {
            for(int j = 0; j < geneCollection.get(i).length; j++)
            {
                System.out.print(geneCollection.get(i)[j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static int calBestSolution(ArrayList<int[]> geneColl)
    {
        double cost = 0;
        double minCost = 0;
        int indexVal = 0;
        for (int i=0;i<geneColl.size();i++)
        {
            if ( i == 0 )
            {
                cost = PathCal.calGeneCost(geneColl.get(i));
                minCost = cost;
                indexVal = i;
            }
            else
            {
                cost = PathCal.calGeneCost(geneColl.get(i));
                if ( cost < minCost )
                {
                    minCost = cost;
                    indexVal = i;
                }
            }

        }
        return indexVal;
    }



















































    /*private int maxLocation = 15;
    private int maxTruck = 4;
    private ArrayList<ArrayList<Integer>> geneCollection = new ArrayList<>();




    public int[] getRoute ()
    {
        //int[] routes = new int[60];
        ArrayList<Integer> routes = new ArrayList<Integer>();
        int bCheck = 0;
        while (bCheck == 0)
        {
            routes = getSetOfRoute();
            for (int i=0;i<60;i++)
            {
                if (routes[i] == )
            }
        }
        geneCollection.equals(routes);

    }


    private int[] getSetOfRoute ()
    {
        int[] assignment = new int[60];
        Random random = new Random();
        int bCheck = 0;
        double weightTruck1 = 0;
        double weightTruck2 = 0;
        double weightTruck3 = 0;
        double weightTruck4 = 0;
        int[] demands = DataReader.getDemands();
        double[][] capacities = DataReader.getTruckLoad();
        int overload = 1;

        while ( overload == 1)
        {
            for (int i=0;i<60;i++)
            {
                assignment[i] = 0;
            }

            for (int i=0;i<maxLocation;i++)
            {
                while ( bCheck == 0 )
                {
                    assignment[i] = random.nextInt(maxLocation);
                    for (int j=0;j<60;j++)
                    {
                        if ((assignment[i] == assignment[j] ) && (assignment[i] != 0))
                        {
                            bCheck = 0;
                        }
                        else
                        {
                            bCheck = 1;
                        }
                    }
                }
                bCheck = 0;
            }

            for (int i=0;i<maxLocation;i++)
            {
                weightTruck1 = weightTruck1+demands[assignment[i]];
            }
            if (weightTruck1 <= capacities[0][0])
            {
                overload = 0;
            }
        }
        overload = 1;

        while ( overload == 1)
        {
            for (int i=0;i<60;i++)
            {
                assignment[i] = 0;
            }

            for (int i=maxLocation;i<maxLocation*2;i++)
            {
                while ( bCheck == 0 )
                {
                    assignment[i] = random.nextInt(maxLocation);
                    for (int j=maxLocation;j<60;j++)
                    {
                        if ((assignment[i] == assignment[j] ) && (assignment[i] != 0))
                        {
                            bCheck = 0;
                        }
                        else
                        {
                            bCheck = 1;
                        }
                    }
                }
                bCheck = 0;
            }

            for (int i=maxLocation;i<maxLocation*2;i++)
            {
                weightTruck2 = weightTruck2+demands[assignment[i]];
            }
            if (weightTruck2 <= capacities[1][0])
            {
                overload = 0;
            }
        }
        overload = 1;

        while ( overload == 1)
        {
            for (int i=0;i<60;i++)
            {
                assignment[i] = 0;
            }

            for (int i=maxLocation*2;i<maxLocation*3;i++)
            {
                while ( bCheck == 0 )
                {
                    assignment[i] = random.nextInt(maxLocation);
                    for (int j=maxLocation*2;j<60;j++)
                    {
                        if ((assignment[i] == assignment[j] ) && (assignment[i] != 0))
                        {
                            bCheck = 0;
                        }
                        else
                        {
                            bCheck = 1;
                        }
                    }
                }
                bCheck = 0;
            }

            for (int i=maxLocation*2;i<60;i++)
            {
                weightTruck3 = weightTruck3+demands[assignment[i]];
            }
            if (weightTruck3 <= capacities[2][0])
            {
                overload = 0;
            }
        }
        overload = 1;


        while ( overload == 1)
        {
            for (int i=maxLocation*3;i<maxLocation*4;i++)
            {
                assignment[i] = 0;
            }

            for (int i=maxLocation*3;i<maxLocation*4;i++)
            {
                while ( bCheck == 0 )
                {
                    assignment[i] = random.nextInt(maxLocation);
                    for (int j=maxLocation*3;j<60;j++)
                    {
                        if ((assignment[i] == assignment[j] ) && (assignment[i] != 0))
                        {
                            bCheck = 0;
                        }
                        else
                        {
                            bCheck = 1;
                        }
                    }
                }
                bCheck = 0;
            }

            for (int i=maxLocation*3;i<maxLocation*4;i++)
            {
                weightTruck4 = weightTruck4+demands[assignment[i]];
            }
            if (weightTruck4 <= capacities[3][0])
            {
                overload = 0;
            }
        }
        overload = 1;


        return assignment;


    }*/
}

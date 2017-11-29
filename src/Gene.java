import java.util.ArrayList;
import java.util.Random;

public class Gene
{
    public static ArrayList<int[]> geneCollection = new ArrayList<>();
    private static int[] solution = new int[60];

    private static int[] getRandom()
    {
        int inputNode = 1;
        int[] randomSol = new int[40];
        int[] sumWeight = new int[4];
        Random random = new Random();
        boolean overLoad = true;
        while (overLoad == true)
        {
            while (inputNode <= 10)
            {

                int inputAddress = random.nextInt(39);
                if (randomSol[inputAddress] == 0)
                {
                    randomSol[inputAddress] = inputNode;
                    inputNode++;
                }
            }
            overLoad = checkOverLoad(randomSol);
        }


        return randomSol;
    }

    public static void calGeneCollection(int numberGene)
    {
        for (int i = 0; i < numberGene; i++)
        {
            solution = getRandom();
            geneCollection.add(solution);
        }
    }

    public static int calBestSolution(ArrayList<int[]> geneColl)
    {
        double cost = 0;
        double minCost = 0;
        int indexVal = 0;


        for (int i = 0; i < geneColl.size(); i++)
        {
            if (i == 0)
            {
                cost = PathCal.calGeneCost(geneColl.get(i));
                minCost = cost;
            }
            else
            {
                cost = PathCal.calGeneCost(geneColl.get(i));
                System.out.print("\n");
                if (cost < minCost)
                {
                    minCost = cost;
                    indexVal = i;
                }
            }

        }
        //System.out.println("FIRSTT = "+PathCal.calGeneCost(geneCollection.get(indexVal)));
        return indexVal;
    }

    public static void main(String[] args)
    {
        PathCal.initializeCalculator();
        calGeneCollection(10);
        /*for (int i = 0; i < geneCollection.size(); i++)
        {
            for (int j = 0; j < geneCollection.get(i).length; j++)
            {
                System.out.print(geneCollection.get(i)[j] + " ");
            }
            System.out.print("\n");
        }*/

        System.out.println(calBestSolution(geneCollection));
    }

    public static boolean checkOverLoad(int[] gene)
    {
        boolean overLoad = false;
        int[] sumWeight = new int[4];
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<10*i;j++)
            {
                sumWeight[i] = sumWeight[i]+DataReader.getDemands()[gene[j]];
            }
            if (sumWeight[i] > DataReader.getTruckLoad()[i][0])
            {
                overLoad = true;
                break;
            }
            else
            {
                overLoad = false;
            }
        }
        return overLoad;
    }
}
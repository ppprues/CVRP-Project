import java.util.ArrayList;
import java.util.Random;

public class Gene
{
    public static ArrayList<int[]> geneCollection = new ArrayList<>();
    private static int[] solution = new int[60];

    private static int[] getRandom()
    {
        int inputNode = 1;
        int[] randomSol = new int[60];
        Random random = new Random();
        while (inputNode <= 10)
        {
            int inputAddress = random.nextInt(59);
            if (randomSol[inputAddress] == 0)
            {
                randomSol[inputAddress] = inputNode;
                inputNode++;
            }
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
}
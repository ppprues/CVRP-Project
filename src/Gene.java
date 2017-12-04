/**
 * Gene
 *
 * Simple class of Genes and Gene collection.
 *
 * 4 November 2017
 */

import java.util.ArrayList;
import java.util.Random;

public class Gene
{
    /** Zero Generation */
    public static ArrayList<int[]> zeroGeneration = new ArrayList<>();

    /**
     * Calculate gene collection for number of times.
     *
     * @param numberGene number of gene in zero generation
     */
    public static void calGeneCollection(int numberGene)
    {
        int[] solution;
        for (int i = 0; i < numberGene; i++)
        {
            solution = getRandom();
            zeroGeneration.add(solution);
        }
    }

    /**
     * Calculate the best solution of gene collection.
     *
     * @param geneColl gene collection
     * @return index of the best solution in gene collection
     */
    public static int calBestSolution(ArrayList<int[]> geneColl)
    {
        double cost;
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

                if (cost < minCost)
                {
                    minCost = cost;
                    indexVal = i;
                }
            }
        }
        return indexVal;
    }

    /**
     * Random 1-15 numbers to array of 40 index.
     *
     * @return all random 1-15 numbers in 40 array
     */
    private static int[] getRandom()
    {
        boolean overLoad = false;
        int inputNode = 1;
        int[] randomSol = new int[32];

        Random random = new Random();

        while (!overLoad)
        {
            while (inputNode <= 8)
            {
                int inputAddress = random.nextInt(31);
                if (randomSol[inputAddress] == 0)
                {
                    randomSol[inputAddress] = inputNode;
                    inputNode++;
                }
            }
            overLoad = WeightConstraint.checkWeight(randomSol);
        }
        return randomSol;
    }

    /**
     * Main for testing
     */
    public static void main(String[] args)
    {
        PathCal.initializeCalculator();
        calGeneCollection(10);

        System.out.println(calBestSolution(zeroGeneration));
    }
}
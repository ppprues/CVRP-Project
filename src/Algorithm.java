/**
 * Algorithm
 *
 * Simple class of Genetic Algorithm.
 *
 * 4 November 2017
 */

import java.io.IOException;
import java.util.ArrayList;

public class Algorithm
{
    /**
     * Asks for a string and returns it as the value of the function
     *
     * @param prompt String to print, telling which coordinate
     * @return The string the user entered (maximum 100 chars long)
     */
    public static String getString(String prompt)
    {
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200];
        System.out.println(prompt);
        try
        {
            readBytes = System.in.read(buffer, 0, 200);
        }
        catch (IOException ioe)
        {
            System.out.println("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        int pos = inputString.indexOf("\n");
        if (pos > 0)
        {
            inputString = inputString.substring(0, pos);
        }
        return inputString;
    }

    /**
     * Main algorithm
     */
    public static void main(String[] args)
    {
        /* Default value */
        int noZeroGen = 5000;
        int noPopulation = 100;
        int maxDuplicate = 10;

        if (args.length == 0) // No program arguments
        {
            System.out.println("Use default configuration");
        }
        else if (args.length == 3)
        {
            try
            {
                // Number of first generation
                if (Integer.parseInt(args[0]) > 0)
                {
                    noZeroGen = Integer.parseInt(args[0]);
                    System.out.println("Use " + noZeroGen + " for number of zero generation");
                }
                else if (Integer.parseInt(args[0]) == 0)
                {
                    System.out.println("Use default value for number of zero generation");

                }
                else
                {
                    System.out.println(">>> Wrong format number of 1st argument");
                    System.exit(0);
                }

                // Number of population
                if (Integer.parseInt(args[1]) > 0)
                {
                    noPopulation = Integer.parseInt(args[1]);
                    System.out.println("Use " + noPopulation + " for number of population");
                }
                else if (Integer.parseInt(args[1]) == 0)
                {
                    System.out.println("Use default value for number of population");
                }
                else
                {
                    System.out.println(">>> Wrong format number of 2nd argument");
                    System.exit(0);
                }

                // Number of maximum duplicate
                if (Integer.parseInt(args[2]) > 0)
                {
                    noPopulation = Integer.parseInt(args[2]);
                    System.out.println("Use " + noPopulation + " for maximum duplicate");
                }
                else if (Integer.parseInt(args[2]) == 0)
                {
                    System.out.println("Use default value for maximum duplicate");
                }
                else
                {
                    System.out.println(">>> Wrong format number of 3rd argument");
                    System.exit(0);
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println(">>> Wrong format number");
                System.exit(0);
            }
        }
        else
        {
            System.out.println(">>> Wrong program arguments");
            System.exit(0);
        }

        String prompt = getString("Continue? (Y/N): ");
        if (prompt.equalsIgnoreCase("N"))
        {
            System.exit(1);
        }

        /* Initialize data from all text files */
        PathCal.initializeCalculator();

        /* Generate the generation 0 */
        Gene.calGeneCollection(noZeroGen);

        int duplicateResults = 0;   /* Duplicate results count */
        int geneCount;              /* Gene count */
        int generationCount = 1;    /* Generation count */
        int[] currentMinGene;       /* current min gene */
        int[] optimalSolution;      /* optimal solution */
        int[][] parent = new int[noPopulation][32]; /* Parent generation or previous generation */
        ArrayList<int[]> currentGeneration = new ArrayList<>(); /* Current generation */

        /* Select top best from generation 0 */
        for (geneCount = 0; geneCount < noPopulation; geneCount++)
        {
            parent[geneCount] = Gene.zeroGeneration.get(Gene.calBestSolution(Gene.zeroGeneration));
            Gene.zeroGeneration.remove(Gene.calBestSolution(Gene.zeroGeneration));
        }

        /* Select one best solution for optimal solution */
        optimalSolution = parent[0];

        /* Loop until find the best optimal solution for multiple times */
        while (true)
        {
            /* Add all parent generation to the new generation */
            for (geneCount = 0; geneCount < noPopulation; geneCount++)
            {
                currentGeneration.add(parent[geneCount]);
            }

            /* 3 Mutations for 3 times each */
            for (geneCount = 0; geneCount < noPopulation; geneCount++)
            {
                for (int i = 0; i < 3; i++)
                {
                    currentGeneration.add(Mutation.swapMutation(currentGeneration.get(geneCount)));
                    currentGeneration.add(Mutation.insertMutation(currentGeneration.get(geneCount)));
                    currentGeneration.add(Mutation.inversionMutation(currentGeneration.get(geneCount)));
                }
            }

            /* Select top best from current generation to new generation */
            for (geneCount = 0; geneCount < noPopulation; geneCount++)
            {
                parent[geneCount] = currentGeneration.get(Gene.calBestSolution(currentGeneration));
                currentGeneration.remove(Gene.calBestSolution(currentGeneration));
            }

            /* Current minimum gene */
            currentMinGene = parent[0];

            /* Clear current generation */
            currentGeneration.clear();

            /* Find the new optimal solution */
            if (currentMinGene == optimalSolution) //if best result is the same
            {
                duplicateResults++;
                if (duplicateResults >= maxDuplicate)
                {
                    break;
                }
            }
            else if (PathCal.calGeneCost(optimalSolution) > PathCal.calGeneCost(currentMinGene))
            {
                duplicateResults = 0;
                optimalSolution = currentMinGene;
            }
            else
            {
                duplicateResults = 0;
            }

            /* Print best solution for each generation */
            int cursor = 0;
            int truckCount = 0;
            int[][] truckPath = new int[4][8];
            System.out.println("Generation: " + generationCount);
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    truckPath[i][j] = optimalSolution[cursor];
                    cursor++;
                }
            }
            while (truckCount < 4)
            {
                System.out.print("Truck " + (truckCount + 1) + ": ");
                for (int i = 0; i < 8; i++)
                {
                    if (truckPath[truckCount][i] != 0)
                    {
                        System.out.print(truckPath[truckCount][i] + "->");
                    }
                }
                System.out.print("\n");
                truckCount++;

            }
            System.out.println("Overall Cost: " + PathCal.calGeneCost(optimalSolution));

            /* Show each generation */
            prompt = getString("Continue? (Y/N): ");
            if (prompt.equalsIgnoreCase("N"))
            {
                break;
            }
            generationCount++;
        }

        System.out.println("From " + (generationCount - 1) + " generations");
        System.out.println("Optimal Solution's Cost: " + PathCal.calGeneCost(optimalSolution));

        /* Plot optimal solution */
        Graph.initialize();
        Graph.plotGene(optimalSolution);
    }
}
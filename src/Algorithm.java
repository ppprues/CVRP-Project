import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;

public class Algorithm
{
    public static void main(String[] args)
    {
        PathCal.initializeCalculator();
        Gene.calGeneCollection(4000);
        int[] theBest;
        int[] bestGene;
        int[][] parent = new int[200][40];
        ArrayList<int[]> bestGeneCollection = new ArrayList<>();
        int same = 0;
        int i =1;

        for (int j=0;j<200;j++)
        {
            parent[j] = Gene.geneCollection.get(Gene.calBestSolution(Gene.geneCollection));
            Gene.geneCollection.remove(Gene.calBestSolution(Gene.geneCollection));
        }

        theBest = parent[0];

        while(true)
        {
            for(int j=0;j<200;j++)
            {
                bestGeneCollection.add(parent[j]);
            }

            System.out.println("Round : "+i);
            for(int j=0;j<200;j++)
            {
                for (int k=0;k<3;k++)
                {
                    bestGeneCollection.add(Mutation.swapMutation(bestGeneCollection.get(j)));
                    bestGeneCollection.add(Mutation.insertMutation(bestGeneCollection.get(j)));
                    bestGeneCollection.add(Mutation.inversionMutation(bestGeneCollection.get(j)));
                }
            }

            for(int j=0;j<200;j++)
            {
                parent[j] = bestGeneCollection.get(Gene.calBestSolution(bestGeneCollection));
                bestGeneCollection.remove(Gene.calBestSolution(bestGeneCollection));
            }

            bestGene = parent[0];
            bestGeneCollection.clear();

            //System.out.println("== CHECK ==");
            //System.out.println("==PARENT==: "+PathCal.calGeneCost(parent));
            //System.out.println("==BESTGENE==: "+PathCal.calGeneCost(bestGene));

            if(bestGene == theBest) //if best result is the same
            {
                same++;
                if(same > 20)
                {
                    break;
                }
            }
            else if (PathCal.calGeneCost(theBest) > PathCal.calGeneCost(bestGene))
            {
                same = 0;
                theBest = bestGene;
                System.out.println("== BETTER ==");
                System.out.println("== THE BEST ==: "+PathCal.calGeneCost(theBest));
                System.out.println("== BEST GENE ==: "+PathCal.calGeneCost(bestGene));
            }
            else
            {
                same = 0;
            }
            i++;
        }
<<<<<<< HEAD
        System.out.println("Best cost: "+PathCal.calGeneCost(parent));



        Graph.initialize();
        Graph.plotGene(bestGene);


=======
        System.out.println("Best cost: "+PathCal.calGeneCost(theBest));
>>>>>>> 583864aeb19784325b3fbd3f6e3c40b08f64f8c1
        //System.out.println("same : "+same);
    }
}
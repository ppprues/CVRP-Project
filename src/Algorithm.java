import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;

public class Algorithm
{
    public static void main(String[] args)
    {
        PathCal.initializeCalculator();
        Gene.calGeneCollection(10000);
        int[] parent = Gene.geneCollection.get(Gene.calBestSolution(Gene.geneCollection));
        int[] bestGene;
        ArrayList<int[]> bestGeneCollection = new ArrayList<>();
        int same = 0;
        int i =1;

        //System.out.println("+++ FIRST +++: "+PathCal.calGeneCost(parent));

        while(true)
        {
            System.out.println("Round : "+i);
            bestGeneCollection.add(parent);
            for (int j=0;j<3;j++)
            {
                bestGeneCollection.add(Mutation.swapMutation(parent));
                bestGeneCollection.add(Mutation.insertMutation(parent));
                bestGeneCollection.add(Mutation.inversionMutation(parent));
            }

            bestGene = bestGeneCollection.get(Gene.calBestSolution(bestGeneCollection));
            bestGeneCollection.clear();

            //System.out.println("== CHECK ==");
            //System.out.println("==PARENT==: "+PathCal.calGeneCost(parent));
            //System.out.println("==BESTGENE==: "+PathCal.calGeneCost(bestGene));

            if(bestGene == parent) //if best result is the same
            {
                same++;
                if(same > 10)
                {
                    break;
                }
            }
            else if (PathCal.calGeneCost(parent) > PathCal.calGeneCost(bestGene))
            {
                same = 0;
                parent = bestGene;
                System.out.println("== BETTER ==");
                System.out.println("== PARENT ==: "+PathCal.calGeneCost(parent));
                System.out.println("== BEST GENE ==: "+PathCal.calGeneCost(bestGene));
            }
            else
            {
                same = 0;
            }
            i++;
        }
        System.out.println("Best cost: "+PathCal.calGeneCost(parent));
        //System.out.println("same : "+same);
    }
}
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Algorithm
{
    public static void main(String[] args)
    {
        DataReader.initializeData();
        Gene.calGeneCollection(10);
        int[] parent = Gene.geneCollection.get(Gene.calBestSolution(Gene.geneCollection));
        int[] bestGene;
        ArrayList<int[]> bestGeneCollection = new ArrayList<>();
        int same = 0;


        while(true)
        {
            bestGeneCollection.add(parent);
            for (int j=1;j<3;j++)
            {
                bestGeneCollection.add(Mutation.swapMutation(parent));
                bestGeneCollection.add(Mutation.insertMutation(parent));
                bestGeneCollection.add(Mutation.inversionMutation(parent));
            }

            bestGene = bestGeneCollection.get(Gene.calBestSolution(bestGeneCollection));
            bestGeneCollection.clear();

            if(PathCal.calGeneCost(bestGene) == PathCal.calGeneCost(parent)) //if best result is the same
            {
                same++;
                if(same > 10)
                {
                    break;
                }
            }
            else if (PathCal.calGeneCost(parent) < PathCal.calGeneCost(bestGene))
            {
                parent = bestGene;
            }
        }
        System.out.println("Hello World");
    }
}
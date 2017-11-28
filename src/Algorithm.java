import java.lang.reflect.Array;
import java.util.ArrayList;

public class Algorithm
{
    public static void main(String[] args)
    {

        DataReader.initializeData();
        Location.calGeneCollection(10);
        int[] parent = Location.geneCollection.get(Location.calBestSolution(Location.geneCollection));
        int[] bestGene;
        ArrayList<int[]> bestGeneCollection = new ArrayList<>();
        int same = 0;

        bestGene = parent;

        while(true)
        {
            bestGeneCollection.add(parent);
            for (int j=1;j<3;j++)
            {
                bestGeneCollection.add(Mutation.swapMutation(parent));
                bestGeneCollection.add(Mutation.insertMutation(parent));
                bestGeneCollection.add(Mutation.inversionMutation(parent));
            }

            parent = bestGeneCollection.get(Location.calBestSolution(bestGeneCollection));
            bestGeneCollection.clear();

            if(PathCal.calGeneCost(bestGene) == PathCal.calGeneCost(parent)) //if best result is the same
            {
                same++;
                if(same > 10)//count same>10
                {
                    break;
                }
            }
            else if (PathCal.calGeneCost(bestGene) < PathCal.calGeneCost(parent))
            {
                bestGene = parent;
            }
        }
        System.out.println("Heeloo World");
    }
}

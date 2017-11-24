import java.lang.reflect.Array;
import java.util.ArrayList;

public class Algorithm
{
    public static void main(String[] args)
    {

        DataReader.initializeData();
        Location.calGeneCollection(10);
        int bestSol = 0;

        ArrayList<int[]> generation = new ArrayList<int[]>();

        //bestSol = Location.calBestSolution(Location.geneCollection);
        //generation.add(Location.geneCollection.get(bestSol));

        while(true)
        {
            generation.add(Mutation.swapMutation(generation.get(0)));
            generation.add(Mutation.insertMutation(generation.get(0)));
            generation.add(Mutation.inversionMutation(generation.get(0)));
            if(true) //if best result is the same
            {
                //countsame++;
                if(true)//count same>10
                {
                    break;
                    //break
                    //best result is optimal
                }
            }
        }
        //pick1

        //mutate

        System.out.println("Heeloo World");
    }
}

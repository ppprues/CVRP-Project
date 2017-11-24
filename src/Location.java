import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Random;

public class Location
{
    private int maxLocation = 15;
    private int maxTruck = 4;


    public int[] getSetOfRoute ()
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
                    for (int j=0;j<maxLocation;j++)
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
                    for (int j=maxLocation;j<maxLocation*2;j++)
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
                    for (int j=maxLocation*2;j<maxLocation*3;j++)
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

            for (int i=maxLocation*2;i<maxLocation*3;i++)
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
                    for (int j=maxLocation*3;j<maxLocation*4;j++)
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


    }
}

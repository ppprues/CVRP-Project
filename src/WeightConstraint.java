public class WeightConstraint {
    private static double[][] truckLoad=DataReader.getTruckLoad();
    private static double[] productWeight=DataReader.getProductWeight();
    private static int[] demands=DataReader.getDemands();
    public static boolean checkWeight(int[] solution)
    {
        int curTruckWeight=0;
        int i=0;
        int truckCount=0;
        while(truckCount<4)
        {
            while (i<60)
            {
                if(solution[i]!=0)
                {
                    curTruckWeight += productWeight[demands[solution[i]]];
                    if(curTruckWeight>truckLoad[truckCount][0])
                    {
                        return false;
                    }
                }
                i++;
            }
            curTruckWeight=0;
            truckCount++;
        }
        return true;
    }
}

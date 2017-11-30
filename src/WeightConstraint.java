public class WeightConstraint {
    private static double[][] truckLoad=DataReader.getTruckLoad();
    private static double[] productWeight=DataReader.getProductWeight();
    private static int[] demands=DataReader.getDemands();
    public static boolean checkWeight(int[] solution)
    {
        int curTruckWeight=0;
        int i=0;
        int truckCount=0;


        while (i<40)
        {
            if(solution[i]!=0)
            {
                curTruckWeight += productWeight[demands[solution[i]]];
            }
            if(curTruckWeight>truckLoad[truckCount][0])
            {
                System.out.println("truckloadcap "+truckCount+" "+truckLoad[truckCount][0]);
                System.out.println("curtruckweight"+curTruckWeight);
                return false;
            }
            if(i==10 ||i==20 ||i==30)
            {
                curTruckWeight=0;
                truckCount++;
            }
            i++;
        }


        return true;
    }
}

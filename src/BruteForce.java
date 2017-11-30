/**
 *  BruteForce
 *
 *  Simple class of BruteForce.
 *
 *  4 November 2017
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BruteForce extends TextFileReader
{
    private static int lineNumber = 0;
    private static int[] curRoute = new int[40];
    private static TextFileReader possibilities = new TextFileReader();
    private static final String FILENAME = "bruteforceanswer.txt";

    public static void readFile()
    {
        possibilities.open("solution.txt");
    }

    /**
     * Read each line
     * @return false if end of the file
     */
    public static boolean readLine()
    {
        String line = possibilities.getNextLine();
        if (line == null)
        {
            return false;
        }
        String fields[] = line.split(",");
        int cursor = 0;
        int i = 0;
        while (i < 13)
        {
            if (Integer.parseInt(fields[i]) != 0) //put into main route
            {

                curRoute[cursor] = Integer.parseInt(fields[i]);
                cursor++;
            }
            else //term zero until end truck
            {
                if (cursor % 10 == 0)
                {
                    curRoute[cursor] = 0;
                    cursor += 1;
                }
                while (cursor % 10 != 0)
                {
                    curRoute[cursor] = 0;
                    cursor++;
                }
            }
            i++;
        }
        while (cursor < 40)
        {
            curRoute[cursor] = 0;
            cursor++;
        }
        return true;
    }

    /**
     * Main for run program.
     * @param args
     */
    public static void main(String[] args)
    {
        double lowestCost = 9999999;
        double curCost = 0;
        int[] bestroute = new int[40];
        PathCal.initializeCalculator();
        int i = 0;
        BruteForce.readFile();

        while (BruteForce.readLine())
        {
            if (WeightConstraint.checkWeight(curRoute))
            {
                while (i < 40)
                {
                    if (i % 10 == 0)
                    {
                        System.out.println();
                    }
                    System.out.print(BruteForce.curRoute[i] + " ");
                    i++;
                }
                System.out.println();
                i = 0;
                curCost = PathCal.calGeneCost(curRoute);
                System.out.println("this costs " + curCost);
                if (bestroute == null)
                {
                    System.out.println("initialize best route");
                    bestroute = curRoute;
                }
                if (curCost < lowestCost)
                {
                    System.out.println("set new best cost");
                    lowestCost = curCost;
                    for (int k = 0; k < 40; k++)
                    {
                        bestroute[k] = curRoute[k];
                    }
                }
                System.out.println("line success" + lineNumber);
                lineNumber++;
            }
            else
            {
                lineNumber++;
                System.out.println("skipping line" + (lineNumber - 1) + " due to constraint");
            }
        }
        System.out.println("solution's answer = " + lowestCost);
        System.out.print("solution :");
        i = 0;
        while (i < 40)
        {
            System.out.print(bestroute[i] + " ");
            i++;
        }
        i = 0;

        /*write part*/
        BufferedWriter bw = null;
        FileWriter fw = null;
        try
        {
            String content = new String();
            while (i < 40)
            {
                content += bestroute[i];
                content += " ";
                i++;
            }
            content += "\n\n best cost = " + lowestCost;
            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            bw.write(content);

            System.out.println("Done");
        }
        catch (IOException e)
        {

            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bw != null)
                {
                    bw.close();
                }
                if (fw != null)
                {
                    fw.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        possibilities.close();
    }
}

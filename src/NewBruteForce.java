import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;


public class NewBruteForce extends TextFileReader
{
    public static int lineNumber=0;
    public static int[] curRoute = new int[40];
    private  static TextFileReader possibilities = new TextFileReader();
    private static final String FILENAME = "bruteforceanswer.txt";


    public static void readFile()
    {
        possibilities.open("solution.txt");
    }

    public static boolean readLine()
    {

        String line = possibilities.getNextLine();
        if(line==null)
        {
            System.out.println("false "+line);
            return false;
        }
        String fields[] = line.split(",");
        int cursor=0;
        int i=0;
        while(i<13 )
        {
            if(Integer.parseInt(fields[i])!=0)      //ao mai sai nai mainroute
            {

                curRoute[cursor]=Integer.parseInt(fields[i]);
                cursor++;
            }
            else                    //term zero until end truck
            {

                if(cursor%10==0)
                {
                    curRoute[cursor]=0;
                    cursor+=1;
                }
                while(cursor%10!=0)
                {

                    curRoute[cursor]=0;
                    cursor++;
                }

            }
            i++;
        }
        while(cursor<40)
        {
            curRoute[cursor]=0;
            cursor++;
        }

        return true;
    }

    public static void main(String[] args)
    {
        PathCal.initializeCalculator();
        int i=0;
        NewBruteForce.readFile();
        System.out.println("555");
        while(NewBruteForce.readLine()==true)
        {
            System.out.println("555");
            while (i < 40) {
                if(i%10==0)
                {
                    System.out.println();
                }
                System.out.print(NewBruteForce.curRoute[i] + " ");
                i++;
            }
            System.out.println();
            i=0;
            System.out.println("line success"+lineNumber);
            lineNumber++;

        }
    }

}

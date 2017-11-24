//This is a java program to perform all permutation of given list of numbers of a specific length

import java.util.Random;

import java.util.Scanner;


public class Combination
{

    static void permute(int[] a, int k)

    {

        if (k == a.length)

        {

            for (int i = 0; i < a.length; i++)

            {

                System.out.print(" [" + a[i] + "] ");

            }

            System.out.println();

        }

        else

        {

            for (int i = k; i < a.length; i++)

            {

                int temp = a[k];

                a[k] = a[i];

                a[i] = temp;


                permute(a, k + 1);


                temp = a[k];

                a[k] = a[i];

                a[i] = temp;

            }

        }

    }


    public static void main(String args[])

    {

        Random random = new Random();

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the length of list: ");

        int N = sc.nextInt();

        int[] sequence = new int[N];


        for (int i = 0; i < N; i++)
        {
            sequence[i] = i + 1;
        }


        System.out.println("The original sequence is: ");

        for (int i = 0; i < N; i++)

        {
            System.out.print(sequence[i] + " ");
        }


        System.out.println("\nThe permuted sequences are: ");

        permute(sequence, 0);


        sc.close();

    }

}
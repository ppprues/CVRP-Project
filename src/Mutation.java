/*
 *  A Simple Introduction to Genetic Algorithms
 *  Copyright (C) 2015 
 *  @author Dr Noureddin M. Sadawi (noureddin.sadawi@gmail.com)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it as you wish ...  
 *  
 *  I ask you only, as a professional courtesy, to cite my name, web page 
 *  and YouTube Channel!
 *  
 */

import java.util.Random;

public class Mutation
{
    private static Random random = new Random();
    /**
     * performs swap mutation on an array of ints
     *
     * @param parent the int array
     * @return array the mutated array
     */
    public static int[] swapMutation(int[] parent)
    {
        int[] array = parent.clone();
        int l = array.length;
        //get 2 random integers between 0 and size of array
        int r1 = random.nextInt(l);
        int r2 = random.nextInt(l);
        //to make sure the 2 numbers are different
        while (r1 == r2)
        {
            r2 = random.nextInt(l);
        }
        //swap array elements at those indices
        int temp = array[r1];
        array[r1] = array[r2];
        array[r2] = temp;
        if(WeightConstraint.checkWeight(array)==false)
        {
            System.out.println("constraint fault");
            array = swapMutation(parent);
        }
        return array;
    }

    /**
     * performs insert mutation on an array of ints
     *
     * @param parent the int array
     * @return array the mutated array
     */
    public static int[] insertMutation(int[] parent)
    {
        int[] array = parent.clone();
        int l = array.length;
        //get 2 random integers between 0 and size of array
        int r1 = random.nextInt(l);
        int r2 = random.nextInt(l);
        //to make sure the r1 < r2
        while (r1 >= r2)
        {
            r1 = random.nextInt(l);
            r2 = random.nextInt(l);
        }
        //this code moves element at r2 to just after r1
        //and shifts the elements after r1 by 1 place
        for (int i = r2 - 1; i > r1; i--)
        {
            int temp2 = array[i + 1];
            array[i + 1] = array[i];
            array[i] = temp2;
        }
        if(WeightConstraint.checkWeight(array)==false)
        {
            System.out.println("constraint fault");
            array=insertMutation(parent);
        }
        return array;

    }

    /**
     * performs inversion mutation on an array of ints
     *
     * @param parent the int array
     * @return array the mutated array
     */
    public static int[] inversionMutation(int[] parent)
    {
        int[] array = parent.clone();
        int l = array.length;
        for (int k = 0; k < 5; k++)
        {//repeat process 5 times
            //get 2 random integers between 0 and size of array
            int r1 = random.nextInt(l);
            int r2 = random.nextInt(l);
            //to make sure the r1 < r2
            while (r1 >= r2)
            {
                r1 = random.nextInt(l);
                r2 = random.nextInt(l);
            }
            //this code inverts (i.e. reverses) elements between r1..r2 inclusive
            int mid = r1 + ((r2 + 1) - r1) / 2;
            int endCount = r2;
            for (int i = r1; i < mid; i++)
            {
                int tmp = array[i];
                array[i] = array[endCount];
                array[endCount] = tmp;
                endCount--;
            }
        }
        if(WeightConstraint.checkWeight(array)==false)
        {
            System.out.println("constraint fault");
            array= inversionMutation(parent);
        }

        return array;
    }
}
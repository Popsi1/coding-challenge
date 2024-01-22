package com.seerbit.codingchallenge.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.seerbit.codingchallenge.algorithm.AlgorithmSolutions.*;

public class MainAlgorithmTestClass {
    public static void main(String[] args) {
        System.out.println("*********************Algorithm 1*************************");
        //Sample data for algorithm one two sum
        int[] array = {2, 7, 11, 15};
        int target1 = 9;
        int target2 = 26;
        int target3 = 10;
        System.out.println("The array supplied for all target is:  "+ Arrays.toString(array));
        System.out.println("For target1 as 9:   "+hasTwoSum(array, target1));
        System.out.println("For target2 as 26:  "+hasTwoSum(array, target2));
        System.out.println("For target3 as 10:  "+hasTwoSum(array, target3));
        System.out.println(" ");

        System.out.println("*********************Algorithm 2*************************");
        //Sample data for algorithm two  find indexes
        int[] array2 = {1, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 6};
        int target4 = 1;
        int target5 = 5;
        int target6 = 7;

        System.out.println("The array supplied for all target is:  "+ Arrays.toString(array2));
        System.out.println("For target4 as 1:  "+ Arrays.toString(findIndices(array2,target4)));
        System.out.println("For target5 as 5:  "+Arrays.toString(findIndices(array2,target5)));
        System.out.println("For target6 as 17: "+Arrays.toString(findIndices(array2,target6)));
        System.out.println(" ");

        System.out.println("*********************Algorithm 3*************************");
        //Sample data for algorithm three merge intervals
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));
        System.out.println("The supplied intervals are:  "+ intervals);
        System.out.println("The merged intervals are :   "+mergeIntervals(intervals));
        System.out.println("**********************************************");
    }
}

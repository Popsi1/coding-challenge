package com.seerbit.codingchallenge.algorithm;

import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmSolutions {
    //    Methods defined here
    //1. Two sum done in O(n) time complexity
    public static boolean hasTwoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }






    //2.  findIndices done in O(log n) time complexity
    public static int[] findIndices(int[] nums, int target) {
        int[] result = {-1, -1};

        // Find low index
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

            if (nums[mid] == target) {
                result[0] = mid;
            }
        }

        // Find high index
        low = 0;
        high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }

            if (nums[mid] == target) {
                result[1] = mid;
            }
        }

        return result;
    }


    //3.  merge intervals done in O(n log n) time complexity
    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }

        intervals.sort(Comparator.comparingInt(i -> i.start));

        int index = 0;
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(index);
            Interval next = intervals.get(i);

            if (next.start <= current.end) {
                current.end = Math.max(current.end, next.end);
            } else {
                intervals.set(++index, next);
            }
        }

        intervals.subList(index + 1, intervals.size()).clear();
        return intervals;
    }
}

@Data
class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

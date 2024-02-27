package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NonAdjacentSum {
    Map<Integer, Integer> memo = new HashMap<>();
    /*
        array containing numbers
        [2,5,12,7]
        Maximal sum of non-adjacent elements, means we can't take two numbers that are next to each other
        Answer 16 = 4 + 12
        [7, 5, 5, 12]
        Answer 19 = 7 + 12
     */

    public static void main(String[] args) {

    }

    public int sumAdjacent(List<Integer> numbers){
        /*
        Time O(2^n) Space O(n)
        We make a tree from the arrays, since we start with the first number we only have two options take the number in
        consideration or remove to take the next in consideration, either one or other (two options per n numbers)
                                    (2,4,5,12,7)
                                    /           \
                               (5,12,7)       (4,5,12,7)
            NOTE: left option does not have 4 because we cannot sum adjacent numbers
         */
        return sumAdjacentHelper(numbers, 0);
    }

    private int sumAdjacentHelper(List<Integer> numbers, int i) {
        //todo: base case
        if(i >= numbers.size()){
            return 0;
        }

        return Math.max(
                numbers.get(i) + sumAdjacentHelper(numbers, i+2),
                sumAdjacentHelper(numbers, i+1)
        );
    }

    public int sumAdjacentMemo(List<Integer> numbers){
        //Time O(n) Space(n)
        return sumAdjacentHelperMemo(numbers, 0);
    }

    private int sumAdjacentHelperMemo(List<Integer> numbers, int i) {
        //todo: base case
        if(i >= numbers.size()){
            return 0;
        }

        if(memo.containsKey(i)){
            return memo.get(i);
        }


        int result =  Math.max(
                numbers.get(i) + sumAdjacentHelper(numbers, i+2),
                sumAdjacentHelper(numbers, i+1)
        );

        memo.put(i, result);
        return result;
    }
}

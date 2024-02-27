package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class SummingSquares {
    Map<Integer, Double> memo = new HashMap<>();
    /*
        Perfect square take positive number and multiply it by itself
        1,4,9,16,25, ....
        input = 12
        4+4+4 Right answer because we are looking for the minimum number of perfect squares
        9+1+1+1
        Answer to return 3
     */

    public static void main(String[] args) {

    }

    public int sumSquares(int n){
        /*
            Time O(sqrt(n)^n) Space(n)
            Since the easy way to solve it's by doing recursion it will take a tree form
            The height of the tree it's n, this is because if I use only the "1" square perfect number to resolve it, max tree height would be 10
            In time complexity I use square root to determine the max positive number of square root would fit in that input.
            positive numbers: 1, 2, 3, 4, 5, ...
            square perfects:  1, 4, 9, 16, 25, ...
            If we take "10" as input and sqrt(10) ~ 3, it's logic to be that max positive number,
            because if we use 4 it would not be possible to use "16" to build the number "10"
            So sqrt(n) give us the base of the number of max children a node could have,
            but since it's a tree if we elevate that result (sqrt(n)) to ^n, it would give us the max number of nodes that will be in the tree
         */

        return (int) sumSquaresHelper(n);
    }

    public double sumSquaresHelper(int n){
        //todo: base case
        if(n == 0){
            return 0;
        }
        double minSquares = Double.POSITIVE_INFINITY;
        for(int i = 1; i<= Math.sqrt(n); i*=i){
            int square = i*i;
            int numSquares = 1+sumSquares(n - square);
            if(numSquares < minSquares){
                minSquares = numSquares;
            }
        }

        return minSquares;
    }

    public double sumSquaresMemo(int n){
        //Time O(n*sqrt(n)) Space(n)
        return sumSquaresHelperMemo(n);
    }

    public double sumSquaresHelperMemo(int n){
        //todo: base case
        if(n == 0){
            return 0;
        }

        if(memo.containsKey(n)){
            return memo.get(n);
        }

        double minSquares = Double.POSITIVE_INFINITY;
        for(int i = 1; i<= Math.sqrt(n); i*=i){
            int square = i*i;
            int numSquares = 1+sumSquares(n - square);
            if(numSquares < minSquares){
                minSquares = numSquares;
            }
        }

        double result = minSquares;
        memo.put(n, result);

        return result;
    }
}

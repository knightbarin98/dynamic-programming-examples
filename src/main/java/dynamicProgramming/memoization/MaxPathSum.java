package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxPathSum {
    Map<List<Integer>, Double> memo = new HashMap<>();
    /*
        Given a grid mxn
        Start at: top-right conner
        End at: bottom-right conner
        You only can move down or right
        ------------------------------------------
        |    1       |     3       |    12       |
        |            |             |             |
        ------------------------------------------
        |    5       |      6      |     2       |
        |            |             |             |
        -----------------------------------------
        max path sum 1+3+12+2 = 18
     */

    public static void main(String[] args) {
        MaxPathSum maxPathSum = new MaxPathSum();

    }

    public int max(List<List<Integer>> grid){
        return (int) maxHelper(0,0,grid);
    }

    public double maxHelper(int row, int col, List<List<Integer>> grid){
        if(row == grid.size() || col == grid.get(0).size()){
            //Since we are looking for max sum, we want something that doesn't mess with our sum
            //and also tell us that's invalid position
            return Double.NEGATIVE_INFINITY;
        }

        if(row == grid.size() -1 && col == grid.get(0).size() -1){
            return grid.get(row).get(col);
        }

        return grid.get(row).get(col) + Math.max(maxHelper(row + 1, col, grid), maxHelper(row, col+1, grid));
    }



    public int maxMemo(List<List<Integer>> grid){
        return (int) maxHelperMemo(0,0,grid);
    }

    public double maxHelperMemo(int row, int col, List<List<Integer>> grid){
        if(row == grid.size() || col == grid.get(0).size()){
            //Since we are looking for max sum, we want something that doesn't mess with our sum
            //and also tell us that's invalid position
            return Double.NEGATIVE_INFINITY;
        }

        if(row == grid.size() -1 && col == grid.get(0).size() -1){
            return grid.get(row).get(col);
        }

        List<Integer> pos = List.of(row, col);
        if(memo.containsKey(pos)){
            return memo.get(pos);
        }

        double result = grid.get(row).get(col) + Math.max(maxHelper(row + 1, col, grid), maxHelper(row, col+1, grid));

        memo.put(pos, result);
        return result;
    }
}

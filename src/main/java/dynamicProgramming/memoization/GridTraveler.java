package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridTraveler {
    Map<List<Integer>, Integer> memo = new HashMap<>();
    /*
        Given a grid mxn
        Start at: top-right conner
        End at: bottom-right conner
        You only can move down or right
        ---------------------------------------
        |    S       |            |           |
        |            |            |           |
        ---------------------------------------
        |           |            |     E      |
        |           |            |            |
        --------------------------------------
        1. right, right, down
        2.down, right, right
        3.right, down, right

        Possible ways 3 so result = 3

        So some grid would be occupied by walls so we can't travel through them
        ---------------------------------------
        |    S       |            |||||||||||||
        |            |            |||||||||||||
        ---------------------------------------
        |           |            |     E      |
        |           |            |            |
        --------------------------------------
        1.down, right, right
        2.right, down, right
        Possible ways 2 so result = 2
     */

    public static void main(String[] args) {
        GridTraveler gridTraveler = new GridTraveler();
    }

    public int countPaths(List<List<String>> grid){
        //Brute force
        //rows = r; cols = c;
        //Time O(2 ^ r+c) why? = Because it forms a binary tree
        //why it forms a binary tree? = I can only go right or down (that's why the base is 2) and the exponent is r+c
        // because I have to go down to the last row and I have to move right to last column, so for each move I will have only
        //leafs
        // Space (r+c) stack depth, height of tree

        return countPathsHelper(0,0,grid);
    }

    private int countPathsHelper(int r, int c, List<List<String>> grid){
        //Outbounds: invalid position
        if(r == grid.size() || c == grid.get(0).size()){
            //zero ways to travel in that position
            return 0;
        }

        //Invalid cell
        if(grid.get(r).get(c).equals("X")){
            return 0;
        }

        if(r == grid.size() -1 && c == grid.get(0).size() -1){
            return 1;
        }
        //going down or right
        return countPathsHelper(r+1,c, grid) + countPathsHelper(r, c+1, grid);
    }

    public int countPathsMemo(List<List<String>> grid){
        //Memoize solution dynamic programming
        //Time O(r*c), I will memoize positions like (0,0) (0,1), so if I have to memoize all the positions
        //I will have to go through all positions in the worst case scenario, that will be rows * cols in total
        //Space O(r*c)
        return countPathsHelperMemo(0,0,grid);
    }

    private int countPathsHelperMemo(int r, int c, List<List<String>> grid){
        //Outbounds: invalid position
        if(r == grid.size() || c == grid.get(0).size()){
            //zero ways to travel in that position
            return 0;
        }

        //Invalid cell
        if(grid.get(r).get(c).equals("X")){
            return 0;
        }

        if(r == grid.size() -1 && c == grid.get(0).size() -1){
            return 1;
        }

        List<Integer> pos = List.of(r,c);

        if(memo.containsKey(List.of(r,c))){
            return memo.get(List.of(r,c));
        }

        int result = countPathsHelperMemo(r+1,c, grid) + countPathsHelperMemo(r,c+1, grid);
        memo.put(pos, result);

        //going down or right
        return result;
    }
}

package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumPossible {
    Map<Integer, Boolean> memo = new HashMap<>();
    /*
    amount = 5; numbers = [1,2,3]
    true
    2+3
    3+1+1
    2+1+2
    1+1+1+1+1
    amount = 15, numbers[6,4,10]
    false
     */
    public static void main(String[] args) {
        SumPossible sumPossible = new SumPossible();
    }

    public boolean possible(int amount, List<Integer> numbers){
        //Time O(length of numbers ^ amount) Space O(amount)
        //todo base case
        if(amount == 0){
            return true;
        }
        if(amount < 0) {
            return false;
        }

        for(int num: numbers){
            int subAmount = amount - num;
            if(possible(subAmount, numbers)){
                return true;
            }
        }
        return false;
    }

    public boolean possiblememo(int amount, List<Integer> numbers){
        //Time O(length of numbers * amount) Space O(amount)
        //todo base case
        if(amount == 0){
            return true;
        }
        if(amount < 0) {
            return false;
        }

        if(memo.containsKey(amount)){
            return memo.get(amount);
        }

        for(int num: numbers){
            int subAmount = amount - num;
            if(possiblememo(subAmount, numbers)){
                memo.put(subAmount, true);
                return true;
            }
        }
        memo.put(amount,false);
        return false;
    }
}

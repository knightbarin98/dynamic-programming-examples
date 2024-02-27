package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingChange {
    Map<List<Integer>, Integer> memo = new HashMap<>();
    /*
    amount = 4
    coins = [1,2,3]
    Return the possible ways to create the amount given the coins in the array
    We can reuse a coin as much as we want
    1+1+1+1
    3+1
    2+2
    2+1+1
    Answer 4 different ways
     */

    public static void main(String[] args) {

    }

    public int countingChange(int amount, List<Integer> coins){
        return countingChangeHelper(amount,0, coins);
    }

    private int countingChangeHelper(int amount, int coinIdx, List<Integer> coins) {
        if(amount == 0){
            return 1;
        }
        if(coinIdx >= coins.size()){
            return 0;
        }
        int totalWays = 0;
        int value = coins.get(coinIdx);
        for(int qty = 0; qty * value <= amount; qty += 1){
            int subAmount = amount - (qty * value);
            totalWays += countingChangeHelper(subAmount, coinIdx + 1, coins);
        }
        return totalWays;
    }

    public int countingChangeMemo(int amount, List<Integer> coins){
        return countingChangeHelperMemo(amount,0, coins);
    }

    private int countingChangeHelperMemo(int amount, int coinIdx, List<Integer> coins) {
        if(amount == 0){
            return 1;
        }
        if(coinIdx >= coins.size()){
            return 0;
        }
        List<Integer> key = List.of(amount, coinIdx);
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int totalWays = 0;
        int value = coins.get(coinIdx);
        for(int qty = 0; qty * value <= amount; qty += 1){
            int subAmount = amount - (qty * value);
            totalWays += countingChangeHelper(subAmount, coinIdx + 1, coins);

        }
        //To no repeat answers
        memo.put(List.of(amount, coinIdx), totalWays);
        return totalWays;
    }
}

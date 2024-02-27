package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinChange {
    Map<Integer, Integer> memo = new HashMap<>();
    /*
    amount = 5; coins = [1,2,3]
    2+3
    3+1+1
    2+1+2
    1+1+1+1+1
    return the least min of coins in this case is 2 coins 2+3
    amount = 15, numbers[6,4,10]
    false
     */

    public static void main(String[] args) {
        MinChange minChange = new MinChange();
        System.out.println("Brute force solution Min coin amount = 5 coins = [1,2,3] : " +minChange.min(5, List.of(1,2,3)));
    }

    public int min(int amount, List<Integer> coins){
        //Time O(coins ^ amount ) Space O(amount)
        //todo: base case
        if(amount == 0){
            return 0;
        }

        if(amount < 0){
            return -1;
        }

        // min amount
        int minCoins = -1;
        //check for every coin
        for(int coin: coins){
            int subAmount = amount - coin;
            //this returns me the result for a single coin, so I need to save the result
            int resultEvaluatedCoin = min(subAmount, coins);
            //because it returns a value that could be evaluated
            if(resultEvaluatedCoin != -1){
                int numCoins = resultEvaluatedCoin + 1;
                if(numCoins < minCoins || minCoins == -1){
                    minCoins = numCoins;
                }
            }
        }

        return minCoins;
    }

    public int minmemo(int amount, List<Integer> coins){
        //Time O(coins * amount ) Space O(amount)
        //todo: base case
        if(amount == 0){
            return 0;
        }

        if(amount < 0){
            return -1;
        }

        if(memo.containsKey(amount)){
            return memo.get(amount);
        }

        // min amount
        int minCoins = -1;
        //check for every coin
        for(int coin: coins){
            int subAmount = amount - coin;
            //this returns me the result for a single coin, so I need to save the result
            int resultEvaluatedCoin = min(subAmount, coins);
            //because it returns a value that could be evaluated
            if(resultEvaluatedCoin != -1){
                int numCoins = resultEvaluatedCoin + 1;
                if(numCoins < minCoins || minCoins == -1){
                    minCoins = numCoins;
                }
            }
        }
        memo.put(amount, minCoins);
        return minCoins;
    }
}

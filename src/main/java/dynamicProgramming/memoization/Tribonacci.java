package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class Tribonacci {
    Map<Integer, Long> memo = new HashMap<>();
    /*
                   n  : 0 1 2 3 4 5 6 7 8
        Tribonacci(n) : 0 0 1 1 2 4 7 13 24

        trib(0) -> 0
        trib(1) -> 0
        trib(2) -> 1        trib(n) = trib(n-1) + trib(n-2) + trib(n-3)
     */
    public static void main(String[] args) {
        Tribonacci tribonacci = new Tribonacci();
        System.out.println("Tribonacci no memo for n = 6: " + tribonacci.trib(6));
        System.out.println("Tribonacci no memo for n = 8: " + tribonacci.trib(8));
        System.out.println("Tribonacci memo for n = 6: " + tribonacci.tribmemo(6));
        System.out.println("Tribonacci memo for n = 50: " + tribonacci.tribmemo(50));
    }

    public long trib(int n){
        if(n == 0 || n == 1) return 0;
        if(n == 2) return 1;
        return trib(n-1) + trib(n-2) + trib(n-3);
    }

    public long tribmemo(int n){
        if(n == 0 || n == 1) return 0;
        if(n == 2) return 1;
        if(memo.containsKey(n)) return memo.get(n);
        long result = tribmemo(n-1) + tribmemo(n-2) + tribmemo(n-3);
        memo.put(n, result);
        return result;
    }
}

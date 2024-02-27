package dynamicProgramming.memoization;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    Map<Integer, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println("Fibonacci no memo for n = 6: " + fibonacci.fib(6));
        System.out.println("Fibonacci no memo for n = 8: " + fibonacci.fib(8));
        System.out.println("Fibonacci memo for n = 6: " + fibonacci.fibmemo(6));
        System.out.println("Fibonacci memo for n = 50: " + fibonacci.fibmemo(50));
    }

    public long fib(int n){
        //Time O(n²) Space O(n)
        if(n == 0 || n == 1) return n;
        return fib(n-1) + fib(n-2);
    }

    public long fibmemo(int n){
        if(memo.containsKey(n)) return memo.get(n);
        if(n == 0 || n == 1) return n;
        long num = fibmemo(n-1) + fibmemo(n-2);
        memo.put(n,num);
        return num;
    }
}

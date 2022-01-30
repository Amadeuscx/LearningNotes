package com.amadeus.dp;



// 假设有25分、20分、5分、1分的硬币，现要找给客户41分的零钱，如何办到硬币个数最少？
public class CoinChange {

    public static void main(String[] args) {
        System.out.println(coins2(1));
    }


    //递推
    static int coins2(int n) {
        if (n < 1) return -1;

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int min = dp[i - 1];
            if (i >= 5) min = Math.min(dp[i - 5], min);
            if (i >= 20) min = Math.min(dp[i - 20], min);
            if (i >= 25) min = Math.min(dp[i - 25], min);
            dp[i] = min + 1;
        }
        return dp[n];
    }



    //记忆化搜索
    static int coins1(int n) {
        if (n < 1) {return -1;}
        int[] dp = new int[n + 1];

        int[] faces = {1, 5, 20, 25};

        for (int face : faces) {
            if (n < face) break;
            dp[face] = 1;
        }
        return coins1(n, dp);
    }

    static int coins1(int n, int[] dp) {
        if (n < 1) {return Integer.MAX_VALUE;}
        if (dp[n] == 0) {
            int min1 = Math.min(coins1(n - 25, dp), coins1(n - 20, dp));
            int min2 = Math.min(coins1(n - 5, dp), coins1(n - 1, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }



    //暴力递归
    static int coins(int n) {
       if (n < 1) {return Integer.MAX_VALUE;}
       if (n == 1 || n == 5 || n == 20 || n == 25) {return 1;}

       int min1 = Math.min(coins(n - 1), coins(n - 5));
       int min2 = Math.min(coins(n - 20), coins(n - 25));

       return Math.min(min1, min2) + 1;
    }










}

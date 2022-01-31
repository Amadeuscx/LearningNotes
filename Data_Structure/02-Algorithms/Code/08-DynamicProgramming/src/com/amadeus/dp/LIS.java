package com.amadeus.dp;

//给定一个无序的整数序列，求出它最长上升子序列的长度（要求严格上升）
//比如 [10, 2, 2, 5, 1, 7, 101, 18] 的最长上升子序列是 [2, 5, 7, 101]、[2, 5, 7, 18]，长度是 4
public class LIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 7, 101, 1, 18}));
    }


    static int lengthOfLIS(int[] arry) {
        if (arry == null || arry.length == 0) {return 0;}
        int[] dp = new int[arry.length];
        int max = dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arry[j] >= arry[i]) {continue;}
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

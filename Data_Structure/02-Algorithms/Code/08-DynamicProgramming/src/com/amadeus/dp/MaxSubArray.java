package com.amadeus.dp;
//给定一个长度为 n 的整数序列，求它的最大连续子序列和
//比如 –2、1、–3、4、–1、2、1、–5、4 的最大连续子序列和是 4 + (–1) + 2 + 1 = 6
public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray1(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    static int maxSubArray1(int[] array) {
        if (array == null || array.length == 0) {return 0;}

        int dp = array[0];
        int max = dp;
        for (int i = 1; i < array.length; i++) {
            if (dp > 0) {
                dp += array[i];
            }else {
                dp = array[i];
            }
            max = Math.max(dp, max);
        }
        return max;
    }

    static int maxSubArray(int[] array) {
        if (array == null || array.length == 0) {return 0;}

        int[] dp = new int[array.length];
        dp[0] = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = array[i] + dp[i - 1];
            }else {
                dp[i] = array[i];
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }

}

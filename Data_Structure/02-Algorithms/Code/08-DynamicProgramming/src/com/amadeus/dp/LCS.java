package com.amadeus.dp;

//求两个序列的最长公共子序列长度
//        [1, 3, 5, 9, 10] 和 [1, 4, 9, 10] 的最长公共子序列是 [1, 9, 10]，长度为 3
//        ABCBDAB 和 BDCABA 的最长公共子序列长度是 4，可能是
//        ABCBDAB 和 BDCABA > BDAB
//        ABCBDAB 和 BDCABA > BDAB
//        ABCBDAB 和 BDCABA > BCAB
//        ABCBDAB 和 BDCABA > BCBA

public class LCS {



    static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null) return 0;
        char[] arrs1 = s1.toCharArray();
        if (arrs1.length == 0) return 0;
        char[] arrs2 = s2.toCharArray();
        if (arrs2.length == 0) return 0;

        char[] rowsChars = arrs1, colsChars = arrs2;
        if (arrs1.length < arrs2.length) {
            colsChars = arrs1;
            rowsChars = arrs2;
        }
        int[] dp = new int[colsChars.length + 1];
        for (int i = 1; i <= rowsChars.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colsChars.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowsChars[i - 1] == colsChars[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colsChars.length];
    }


    static int lcs2(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) return 0;
        if (arr2 == null || arr2.length == 0) return 0;
        int[] dp = new int[arr2.length + 1];
        for (int i = 1; i <= arr1.length; i++) {
            int cur = 0;
            for (int j = 1; j <= arr2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[arr2.length];
    }

    static int lcs1(int[] arr1, int arr2[]) {
        if (arr1 == null || arr1.length == 0) return 0;
        if (arr2 == null || arr2.length == 0) return 0;

        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 0; i < arr1.length + 1; i++) {
            for (int j = 0; j < arr2.length + 1; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i][j - 1]);
                }
            }
        }
        return dp[arr1.length][arr2.length];
    }

    static int lcs(int[] arr1, int arr2[]) {
        if (arr1 == null || arr1.length == 0) return 0;
        if (arr2 == null || arr2.length == 0) return 0;

            return lcs(arr1, arr1.length - 1, arr2, arr2.length - 1);

    }

    private static int lcs(int[] arr1, int i, int[] arr2, int j) {
            if (i == 0 || j == 0)  return 0;
            if (arr1[i] == arr2[j]) {
                return lcs(arr1, i - 1, arr2, j - 1) + 1;
            }

            return Math.max(lcs(arr1, i - 1, arr2, j),
                lcs(arr1, i, arr2, j - 1));
    }
}

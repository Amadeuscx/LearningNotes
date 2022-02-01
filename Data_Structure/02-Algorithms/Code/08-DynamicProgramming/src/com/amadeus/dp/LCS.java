package com.amadeus.dp;

//求两个序列的最长公共子序列长度
//        [1, 3, 5, 9, 10] 和 [1, 4, 9, 10] 的最长公共子序列是 [1, 9, 10]，长度为 3
//        ABCBDAB 和 BDCABA 的最长公共子序列长度是 4，可能是
//        ABCBDAB 和 BDCABA > BDAB
//        ABCBDAB 和 BDCABA > BDAB
//        ABCBDAB 和 BDCABA > BCAB
//        ABCBDAB 和 BDCABA > BCBA

public class LCS {



//    static int longestCommonSubsequence(String s1, String s2) {
//        if (s1 == null || s2 == null) return 0;
//        char[] arrs1 = s1.toCharArray();
//        if (arrs1.length == 0) return 0;
//        char[] arrs2 = s2.toCharArray();
//        if (arrs2.length == 0) return 0;
//
//
//    }

        static int lcs(int[] arr1, int arr2[]) {
            if (arr1 == null || arr2 == null) return 0;
            if (arr1.length == 0 || arr2 .length == 0) return 0;

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

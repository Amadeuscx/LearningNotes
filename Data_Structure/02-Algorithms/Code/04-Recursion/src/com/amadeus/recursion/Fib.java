package com.amadeus.recursion;

//求第n个fib数
public class Fib {
    //纯递归
    public int fib(int n) {
        if (n <= 2) {return 1;}
        return fib(n - 1) + fib(n - 2);
    }
    //使用数组
    public int fib1(int n) {
        if (n <= 2) {return 1;}
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;
        return fib1(n, arr);
    }
    private int fib1(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = fib1(n - 1, arr) + fib1(n - 2, arr);
        }
        return arr[n];
    }
}

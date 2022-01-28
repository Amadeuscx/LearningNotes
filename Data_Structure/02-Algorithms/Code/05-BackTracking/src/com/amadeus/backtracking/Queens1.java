package com.amadeus.backtracking;

public class Queens1 {


    public static void main(String[] args) {
        new Queens1().placeQueens(8);
    }




    boolean[] cols;
    boolean[] leftTop;
    boolean[] rightTop;
    int[] queens;
    int ways;

    void placeQueens(int n) {
        if (n < 1) {return;}
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    private void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (cols[col]) {continue;}
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) {continue;}
            int rtIndex = row + col;
            if (rightTop[rtIndex]) {continue;}

            queens[row] = col;
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
            place(row + 1);
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }
    }

    void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (queens[row] == col) {
                    System.out.print("1  ");
                } else {
                    System.out.print("0  ");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }
}


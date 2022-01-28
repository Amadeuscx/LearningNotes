package com.amadeus.backtracking;

public class Queens {
    public static void main(String[] args) {
        new Queens().placeQueens(4);
    }
    int[] cols;
    int ways;

    void placeQueens(int n) {
        if (n < 1) {return;}

        cols = new int[n];
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
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col || row - i == Math.abs(col - cols[i])) {
                //System.out.println("[" + row + "][" + col + "]=false");
                return false;
            }
        }
        //System.out.println("[" + row + "][" + col + "]=true");
        return true;
    }

    void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print("1  ");
                } else {
                    System.out.print("0  ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }






}


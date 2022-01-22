package com.amadeus.unionfind;

public class UF_QU_rank extends UF_QuickUnion {
    private int[] ranks;

    public UF_QU_rank(int capacity) {
        super(capacity);

        ranks = new int[capacity];
        for (int i = 0; i <capacity ; i++) {
            ranks[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) return;

        if (ranks[r1] < ranks[r2]) {
            parents[r1] = r2;
        }else if (ranks[r2] < ranks[r1]) {
            parents[r2] = r1;
        }else {
            parents[r2] = r1;
            ranks[r1]++;
        }
    }
}

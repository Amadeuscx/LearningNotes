package com.amadeus.unionfind;

public class UF_QU_size extends UF_QuickUnion {
    private int[] sizes;

    public UF_QU_size(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) return;

        if (sizes[r1] < sizes[r2]) {
            parents[r1] = r2;
            sizes[r2] += sizes[r1];
        }else {
            parents[r2] = r1;
            sizes[r1] += sizes[r2];
        }
    }
}

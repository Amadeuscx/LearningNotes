package com.amadeus.unionfind;

public class UF_QuickUnion extends UnionFind {
    public UF_QuickUnion(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    @Override
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) return;

        parents[r1] = r2;
    }


}

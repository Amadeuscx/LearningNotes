package com.amadeus.unionfind;

public class UF_QU_R_PathSpliting extends UF_QU_rank {
    public UF_QU_R_PathSpliting(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}

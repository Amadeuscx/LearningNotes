package com.amadeus.unionfind;

public class UF_QU_R_PathCompression extends UF_QU_rank {
    public UF_QU_R_PathCompression(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}

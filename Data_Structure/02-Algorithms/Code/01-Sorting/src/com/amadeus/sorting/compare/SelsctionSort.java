package com.amadeus.sorting.compare;

import com.amadeus.sorting.Sort;

public class SelsctionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int max = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(max, begin) < 0) {
                    max = begin;
                }
                if (max != end) {
                    swap(max, end);
                }
            }
        }
    }
}

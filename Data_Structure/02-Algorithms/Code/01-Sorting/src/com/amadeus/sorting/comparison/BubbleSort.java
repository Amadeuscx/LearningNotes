package com.amadeus.sorting.comparison;

import com.amadeus.sorting.Sort;

public class BubbleSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    //最优版：当后半部分有序后就不再遍历后半部
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int sortedIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
                end = sortedIndex;
            }
        }
    }

//    基础版：一定会比n^2次
//    protected void sort() {
//        for (int end = array.length - 1; end > 0; end--) {
//            for (int begin = 1; begin <= end; begin++) {
//                if (cmp(begin, begin - 1) < 0) {
//                    swap(begin, begin - 1);
//                }
//            }
//        }
//    }



//    优化版：当有序后停止冒泡
//    protected void sort() {
//        for (int end = array.length - 1; end > 0; end--) {
//            boolean sorted = true;
//            for (int begin = 1; begin <= end; begin++) {
//                if (cmp(begin, begin - 1) < 0) {
//                    swap(begin, begin - 1);
//                    sorted = false;
//                }
//                if (sorted) {break;}
//            }
//        }
//    }





}

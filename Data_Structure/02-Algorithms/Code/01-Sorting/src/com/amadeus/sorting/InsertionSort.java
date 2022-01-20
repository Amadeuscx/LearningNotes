package com.amadeus.sorting;

public class InsertionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    //最优版：利用二分查找来检索插入位置
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int dest = search(begin);
            T element = array[begin];
            for (int i = begin; i > dest; i--) {
                array[i] = array[i - 1];
            }
            array[dest] = element;
        }
    }

    private int search(int index) {
        int begin = 0;
        int end = index;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0){
                end = mid;
            }else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    //优化版：提前保存待插入的元素，减少了交换次数
//    protected void sort() {
//      //老师写的
//        for (int begin = 1; begin < array.length; begin++) {
//            int cur = begin;
//            T element = array[cur];
//            while (cur > 0 && cmp(element, array[cur - 1]) < 0) {
//                array[cur] = array[cur - 1];
//                cur--;
//            }
//            array[cur] = element;
//        }
//
//        //我写的
////        for (int begin = 1; begin < array.length; begin++) {
////            int cur = begin - 1;
////            while (cur >= 0 && cmp(begin, cur) < 0) {
////                cur--;
////            }
////            T element = array[begin]
////            for (int i = begin - 1; i > cur; i--) {
////                array[i + 1] = array[i];
////            }
////            array[cur + 1] = element;
////        }
//
//    }



    //基础版
//    protected void sort() {
//        for (int begin = 1; begin < array.length; begin++) {
//            int cur = begin;
//            while (cur > 0 && cmp(cur, cur - 1) < 0) {
//                swap(cur, cur - 1);
//                cur--;
//            }
//        }
//    }

}

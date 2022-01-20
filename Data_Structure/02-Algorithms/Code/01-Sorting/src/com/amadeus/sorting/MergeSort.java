package com.amadeus.sorting;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    //复制左半边数组
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) {return;}

        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }


    /**
     * 合并方法
     * li 复制数组指针， le 复制数组大小
     * ri 右半数组指针， re 数组结尾
     * ai array数组指针
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        for (int i = 0; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        while (li < le) { //当遍历完复制数组时，循环结束。
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            }else {
                array[ai++] = leftArray[li++];
            }
        }
    }


}

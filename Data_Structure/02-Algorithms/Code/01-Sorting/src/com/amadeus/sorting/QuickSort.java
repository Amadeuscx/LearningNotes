package com.amadeus.sorting;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if ((end - begin) < 2) {return;}

        int mid = pivotIndex(begin, end);

        sort(begin, mid);
        sort(mid + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        // 随机选择一个元素跟begin位置进行交换,使轴点更加随机。
        swap(begin, begin + (int)(Math.random() * (end - begin)));
        end--;
        T pivot = array[begin];
        while (begin < end) {

            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) {
                    end--;
                }else {
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                if (cmp(array[begin], pivot) < 0) {
                    begin++;
                }else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        array[begin] = pivot;
        return begin;
    }
}

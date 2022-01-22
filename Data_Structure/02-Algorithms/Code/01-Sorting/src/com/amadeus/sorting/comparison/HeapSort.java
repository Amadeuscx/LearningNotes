package com.amadeus.sorting.comparison;

import com.amadeus.sorting.Sort;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int heapSize;
    @Override
    protected void sort() {
        heapSize = array.length;
        //原地建堆
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, --heapSize);

            // 对0位置进行siftDown（恢复堆的性质）
            siftDown(0);
        }

    }

    private void siftDown(int index) {
        T element = array[index];

        int half = heapSize >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];

            int rightIndex = childIndex + 1;
            if (rightIndex < half &&
                cmp(childIndex, rightIndex) < 0) {
                child = array[childIndex = rightIndex];
            }

            if (cmp(element, child) < 0) {
                array[index] = child;
                index = childIndex;
            }
        }
        array[index] = element;
    }
}

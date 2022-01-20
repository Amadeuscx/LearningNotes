package com.amadeus.sorting;

import java.text.DecimalFormat;
import java.util.Arrays;

public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>>{
    protected T[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(T[] array) {
        if (array == null && array.length < 2) {return;}

        this.array = array;
        sort();
    }

    protected abstract void sort();


    /*
     * 返回值等于0，代表 array[index1] == array[index2]
     * 返回值小于0，代表 array[index1] < array[index2]
     * 返回值大于0，代表 array[index1] > array[index2]
     */
    protected int cmp(int index1, int index2) {
//        rangeCheck(index1);
//        rangeCheck(index2);
        cmpCount++;
        return array[index1].compareTo(array[index2]);
    }

    protected int cmp(T e1, T e2) {
        cmpCount++;
        return e1.compareTo(e2);
    }

    protected void swap(int index1, int index2) {
//        rangeCheck(index1);
//        rangeCheck(index2);
//        if (index1 == index2) {return;}
        swapCount++;
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
    @Override
    public int compareTo(Sort<T> o) {
        int result = (int)(time - o.time);
        if (result != 0) return result;

        result = cmpCount - o.cmpCount;
        if (result != 0) return result;

        return swapCount - o.swapCount;
    }



    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }

    private boolean isStable() {
        return true;
    }
}

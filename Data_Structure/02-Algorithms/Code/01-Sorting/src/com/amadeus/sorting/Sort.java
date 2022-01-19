package com.amadeus.sorting;

public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>>{
    protected T[] array;
    private int cmpCount;
    private int swapCount;

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
        rangeCheck(index1);
        rangeCheck(index2);
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
    public int compareTo(Sort<T> t) {
        return 0;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= array.length) {
            throw new  ArrayIndexOutOfBoundsException("your index = " + index + ",but size = " + array.length);
        }
    }
}

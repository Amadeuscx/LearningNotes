package com.amadeus.sorting;

public class CountingSort extends Sort<Integer> {

    @Override
    protected void sort() {
        int max = array[0];
        int min = array[0];
        for (int i = 1; i <array.length ; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        int[] counts = new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            counts[array[i] - min]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] newArray = new int[array.length];

        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i] - min]] = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }
}


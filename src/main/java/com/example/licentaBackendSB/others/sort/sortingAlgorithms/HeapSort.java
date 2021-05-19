package com.example.licentaBackendSB.others.sort.sortingAlgorithms;

import com.example.licentaBackendSB.entities.Student;
import java.util.List;

public class HeapSort {

    static void swap(List<Student> array, int pos1, int pos2)
    {
        Student temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
    }

    static void heapify(List<Student> array, int length, int i) {
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        int largest = i;

        // if the left child is larger than parent
        if (leftChild < length && array.get(leftChild).getMedie() < array.get(largest).getMedie()) {
            largest = leftChild;
        }

        // if the right child is larger than parent
        if (rightChild < length && array.get(rightChild).getMedie() < array.get(largest).getMedie()) {
            largest = rightChild;
        }

        // if a swap needs to occur
        if (largest != i)
        {
            swap(array, i, largest);
            heapify(array, length, largest);
        }
    }

    public static void heapSort(List<Student> array) {
        if (array.size() == 0) return;

        // Building the heap
        int length = array.size();
        // we're going from the first non-leaf to the root
        for (int i = length / 2-1; i >= 0; i--)
            heapify(array, length, i);

        for (int i = length-1; i >= 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

}

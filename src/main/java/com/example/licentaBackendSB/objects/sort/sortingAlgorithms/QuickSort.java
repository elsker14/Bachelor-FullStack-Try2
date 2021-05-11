package com.example.licentaBackendSB.objects.sort.sortingAlgorithms;

import com.example.licentaBackendSB.objects.student.Student;
import java.util.List;

public class QuickSort {

    static void swap(List<Student> array, int pos1, int pos2)
    {
        Student temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
    }

    static int partition(List<Student> array, int begin, int end)
    {
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++)
        {
            if (array.get(i).getMedie() > array.get(pivot).getMedie())
            {
                swap(array, counter, i);
                counter++;
            }
        }
        swap(array, pivot, counter);
        return counter;
    }

    public static void quickSort(List<Student> array, int begin, int end)
    {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot-1);
        quickSort(array, pivot+1, end);
    }
}

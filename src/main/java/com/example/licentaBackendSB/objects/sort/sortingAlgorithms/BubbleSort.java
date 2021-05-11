package com.example.licentaBackendSB.objects.sort.sortingAlgorithms;

import com.example.licentaBackendSB.objects.student.Student;
import java.util.List;

public class BubbleSort {

    static void swap(List<Student> array, int pos1, int pos2)
    {
        Student temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
    }

    public static void bubbleSort(List<Student> array)
    {
        boolean sorted = false;
        int temp;

        while(!sorted)
        {
            sorted = true;
            for (int i = 0; i < array.size() - 1; i++)
            {
                if (array.get(i).getMedie() < array.get(i+1).getMedie())
                {
                    swap(array, i, i + 1);
                    sorted = false;
                }
            }
        }
    }

}

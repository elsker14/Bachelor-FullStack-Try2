package com.example.licentaBackendSB.objects.sort.sortingAlgorithms;

import com.example.licentaBackendSB.objects.student.Student;
import java.util.List;

public class InsertionSort {

    public static void insertionSort(List<Student> array)
    {
        for (int i = 1; i < array.size(); i++)
        {
            Student current = array.get(i);
            int j = i - 1;

            while(j >= 0 && current.getMedie() > array.get(j).getMedie())
            {
                array.set(j+1, array.get(j));
                j--;
            }
            // at this point we've exited, so j is either -1
            // or it's at the first element where current >= a[j]
            array.set(j+1, current);
        }
    }

}

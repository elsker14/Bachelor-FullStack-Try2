package com.example.licentaBackendSB.others.sort.sortingAlgorithms;

import com.example.licentaBackendSB.entities.Student;
import java.util.List;

public class SelectionSort {

    static void swap(List<Student> array, int pos1, int pos2)
    {
        Student temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
    }

    public static void selectionSort(List<Student> array)
    {
        for (int i = 0; i < array.size(); i++)
        {
            Student min = array.get(i);
            int minId = i;
            for (int j = i+1; j < array.size(); j++)
            {
                if (array.get(j).getMedie() > min.getMedie())
                {
                    min = array.get(j);
                    minId = j;
                }
            }
            // swapping
//            int temp = array[i];
//            array[i] = min;
//            array[minId] = temp;
            swap(array, i, minId);
        }
    }

}

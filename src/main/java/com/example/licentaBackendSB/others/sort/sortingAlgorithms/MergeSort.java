package com.example.licentaBackendSB.others.sort.sortingAlgorithms;

import com.example.licentaBackendSB.entities.Student;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    static void merge(List<Student> array, int left, int mid, int right) {
        // calculating lengths
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // creating temporary subarrays
        List <Student> leftArray = new ArrayList<>(lengthLeft);
        List <Student> rightArray = new ArrayList<>(lengthRight);

        // copying our sorted subarrays into temporaries
        for (int i = 0; i < lengthLeft; i++)
            leftArray.add(i, array.get(left+i));
        for (int i = 0; i < lengthRight; i++)
            rightArray.add(i, array.get(mid+i+1));

        // iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // copying from leftArray and rightArray back into array
        for (int i = left; i < right + 1; i++) {
            // if there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray.get(leftIndex).getMedie() > rightArray.get(rightIndex).getMedie())
                {
                    array.set(i, leftArray.get(leftIndex));
                    leftIndex++;
                }
                else
                {
                    array.set(i, rightArray.get(rightIndex));
                    rightIndex++;
                }
            }
            // if all the elements have been copied from rightArray, copy the rest of leftArray
            else if (leftIndex < lengthLeft)
            {
                array.set(i, leftArray.get(leftIndex));
                leftIndex++;
            }
            // if all the elements have been copied from leftArray, copy the rest of rightArray
            else if (rightIndex < lengthRight)
            {
                array.set(i, rightArray.get(rightIndex));
                rightIndex++;
            }
        }
    }

    public static void mergeSort(List <Student> array, int left, int right) {
        if (right <= left) return;
        int mid = (left+right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, mid, right);
    }

}
